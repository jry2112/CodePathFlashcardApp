package com.example.jadasflashcardapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    // Holds list of Flashcards
    List<Flashcard> allFlashcards;
    private int currentCardDisplayedIndex = 0;
    private boolean isAnswerShowing;
    private TextView flashcardQuestion;
    private TextView flashcardAnswer;
    private TextView correctAnswer;
    private TextView wrongAnswer1;
    private TextView wrongAnswer2;
    private ImageView showAnswersIcon;
    private ImageView addCardIcon;
    private ImageView editCardIcon;
    private ImageView nextCardIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initializes database
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();
        // Read from the database when the app is launched
        // If the list is not empty, display saved flashcard
        if(allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.correct_answer)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.wrong_answer_1)).setText(allFlashcards.get(0).getWrongAnswer1());
            ((TextView) findViewById(R.id.wrong_answer_2)).setText(allFlashcards.get(0).getWrongAnswer2());
        }

            flashcardQuestion = findViewById(R.id.flashcard_question);
            flashcardAnswer = findViewById(R.id.flashcard_answer);
            correctAnswer = findViewById(R.id.correct_answer);
            wrongAnswer1 = findViewById(R.id.wrong_answer_1);
            wrongAnswer2 = findViewById(R.id.wrong_answer_2);
            showAnswersIcon = findViewById(R.id.toggle_choices_visibility);
            addCardIcon = findViewById(R.id.add_new_card);
            editCardIcon = findViewById(R.id.edit_card);
            nextCardIcon = findViewById(R.id.next_card);


        // User can tap the question text to hide question and show answer
        flashcardQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide question
                flashcardQuestion.setVisibility(View.INVISIBLE);
                // Show answer
                flashcardAnswer.setVisibility(View.VISIBLE);

            }

        });

        // User can tap the answer text to hide answer and show question
        flashcardAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide answer
                flashcardAnswer.setVisibility(View.INVISIBLE);
                // Show question
                flashcardQuestion.setVisibility(View.VISIBLE);
            }

        });

        // User selected answer
        // Wrong
        wrongAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change wrong answer background to red and correct to green
                wrongAnswer1.setBackgroundColor(getResources().getColor(R.color.fuzzyRed,
                        null));
                correctAnswer.setBackgroundColor(getResources().getColor(R.color.oceanGreen,
                        null));
            }

        });
        // Wrong
        wrongAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change wrong answer background to red and correct to green
                wrongAnswer2.setBackgroundColor(getResources().getColor(R.color.fuzzyRed,
                        null));
                correctAnswer.setBackgroundColor(getResources().getColor(R.color.oceanGreen,
                        null));
            }

        });
        // Correct
        correctAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change correct answer background to green
                correctAnswer.setBackgroundColor(getResources().getColor(R.color.oceanGreen,
                        null));
            }

        });
        // Toggle show answer icon based on click
        showAnswersIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnswerShowing){
                    showAnswersIcon.setImageResource(R.drawable.care_up_thin);
                    correctAnswer.setVisibility(View.VISIBLE);
                    wrongAnswer1.setVisibility(View.VISIBLE);
                    wrongAnswer2.setVisibility(View.VISIBLE);
                }
                else{
                    showAnswersIcon.setImageResource(R.drawable.care_down_thin);
                    correctAnswer.setVisibility(View.INVISIBLE);
                    wrongAnswer1.setVisibility(View.INVISIBLE);
                    wrongAnswer2.setVisibility(View.INVISIBLE);
                }
                isAnswerShowing = !isAnswerShowing;
            }

        });
        // Create new card
        addCardIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        // Edit current card
        editCardIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get currently displayed question and answer
                // Pass to AddCardActivity
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                intent.putExtra("question", flashcardQuestion.getText().toString());
                intent.putExtra("answer", flashcardAnswer.getText().toString());
                intent.putExtra("wrongAnswer1", wrongAnswer1.getText().toString());
                intent.putExtra("wrongAnswer2", wrongAnswer2.getText().toString());
                MainActivity.this.startActivityForResult(intent, 100);

            }
        });

    // Click next icon to view next card
        nextCardIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // don't try to go to next card if you have no cards to begin with
                if (allFlashcards.size() == 0)
                    return;
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex ++;
                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last index
                if (currentCardDisplayedIndex >= allFlashcards.size()){
                    currentCardDisplayedIndex = 0;
                }
                // set the question and answer TextViews with data from the database
                allFlashcards = flashcardDatabase.getAllCards();
                Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);

                flashcardQuestion.setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                flashcardAnswer.setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                correctAnswer.setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                wrongAnswer1.setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                wrongAnswer2.setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
            }
        });
    }
    // Save and display newly created card
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) { //code for creating a new card
            String question = data.getExtras().getString("questionText");
            String answer = data.getExtras().getString("answerText");
            String wrongAnswer1 = data.getExtras().getString("wrongAnswerText1");
            String wrongAnswer2 = data.getExtras().getString("wrongAnswerText2");
            // Update question and answer on flashcard
            ((TextView) findViewById(R.id.flashcard_question)).setText(question);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(answer);
            ((TextView) findViewById(R.id.correct_answer)).setText(answer);
            ((TextView) findViewById(R.id.wrong_answer_1)).setText(wrongAnswer1);
            ((TextView) findViewById(R.id.wrong_answer_2)).setText(wrongAnswer2);
            // Notify success
            Snackbar.make(findViewById(R.id.flashcard_question),
                    "Card successfully created.", Snackbar.LENGTH_SHORT).show();
            // Add to database
            flashcardDatabase.insertCard(new Flashcard(question, answer, wrongAnswer1, wrongAnswer2));
            allFlashcards = flashcardDatabase.getAllCards();    // Updates list of cards
        }
    }
}