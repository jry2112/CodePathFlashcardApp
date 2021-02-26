package com.example.jadasflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView flashcardQuestion = findViewById(R.id.flashcard_question);
        TextView flashcardAnswer = findViewById(R.id.flashcard_answer);
        TextView correctAnswer = findViewById(R.id.correct_answer);
        TextView wrongAnswer1 = findViewById(R.id.wrong_answer_1);
        TextView wrongAnswer2 = findViewById(R.id.wrong_answer_2);
        ImageView showAnswers = findViewById(R.id.toggle_choices_visibility_down);
        ImageView hideAnswers = findViewById(R.id.toggle_choices_visibility_up);

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

        // User can tap the up triangle to hide answers
        hideAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide answers
                correctAnswer.setVisibility(View.INVISIBLE);
                wrongAnswer1.setVisibility(View.INVISIBLE);
                wrongAnswer2.setVisibility(View.INVISIBLE);
                // Show down triangle
                showAnswers.setVisibility(View.VISIBLE);
                // Hide up triangle
                hideAnswers.setVisibility(View.INVISIBLE);

            }

        });

        // User can tap the down triangle to show answers
        showAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide answers
                correctAnswer.setVisibility(View.VISIBLE);
                wrongAnswer1.setVisibility(View.VISIBLE);
                wrongAnswer2.setVisibility(View.VISIBLE);
                // Show up triangle
                hideAnswers.setVisibility(View.VISIBLE);
                // Hide down triangle
                showAnswers.setVisibility(View.INVISIBLE);

            }

        });
    }
}