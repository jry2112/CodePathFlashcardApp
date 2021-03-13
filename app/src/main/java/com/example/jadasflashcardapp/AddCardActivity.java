package com.example.jadasflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        ImageView cancelNewCard = findViewById(R.id.cancel_new_card);
        ImageView saveNewCard = findViewById(R.id.save_new_card);
        EditText questionField = findViewById(R.id.addQuestionField);
        EditText answerField = findViewById(R.id.addAnswerField);
        EditText wrongAnswerField1 = findViewById(R.id.addWrongAnswerField1);
        EditText wrongAnswerField2 = findViewById(R.id.addWrongAnswerField2);
        String question = getIntent().getStringExtra("question"); // Current question
        String answer = getIntent().getStringExtra("answer"); // Current answer
        String wrongAnswer1 = getIntent().getStringExtra("wrongAnswer1");
        String wrongAnswer2 = getIntent().getStringExtra("wrongAnswer2");
        String errorMessage = "ERROR: Please enter a question and an answer.";

        // Set edit text to current question and answer if edit icon is pressed
        questionField.setText(question);
        answerField.setText(answer);
        wrongAnswerField1.setText(wrongAnswer1);
        wrongAnswerField2.setText(wrongAnswer2);

        // User can cancel making a new card
        cancelNewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // User can save question and answer entered in a new card
        saveNewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = ((EditText) findViewById(R.id.addQuestionField)).getText().toString();
                String answer = ((EditText) findViewById(R.id.addAnswerField)).getText().toString();
                String wrongAnswer1 = ((EditText) findViewById(R.id.addWrongAnswerField1)).getText().toString();
                String wrongAnswer2 = ((EditText) findViewById(R.id.addWrongAnswerField2)).getText().toString();
                // If question or answer are blank, display error
                if (question.matches("") || answer.matches("") ) {
                    Toast toast = Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT);
                    toast.show();
                }
                // Otherwise, store data
                else {
                    Intent data = new Intent();  //new Intent to store data
                    data.putExtra("questionText", question);
                    data.putExtra("answerText", answer);
                    data.putExtra("wrongAnswerText1", wrongAnswer1);
                    data.putExtra("wrongAnswerText2", wrongAnswer2);
                    setResult(RESULT_OK, data);
                    finish();
                }

            }
        });
    }
}