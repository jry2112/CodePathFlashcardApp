package com.example.jadasflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private boolean isAnswerShowing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView flashcardQuestion = findViewById(R.id.flashcard_question);
        TextView flashcardAnswer = findViewById(R.id.flashcard_answer);
        TextView correctAnswer = findViewById(R.id.correct_answer);
        TextView wrongAnswer1 = findViewById(R.id.wrong_answer_1);
        TextView wrongAnswer2 = findViewById(R.id.wrong_answer_2);
        ImageView showAnswersIcon = findViewById(R.id.toggle_choices_visibility);
        ImageView addCardIcon = findViewById(R.id.add_new_card);
        ImageView editCardIcon = findViewById(R.id.edit_card);


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
                    "Card successfully created.",
                    Snackbar.LENGTH_SHORT)
                    .show();
        }
    }
}