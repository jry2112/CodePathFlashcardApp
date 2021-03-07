package com.example.jadasflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.w3c.dom.Text;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        ImageView cancelNewCard = findViewById(R.id.cancel_new_card);
        ImageView saveNewCard = findViewById(R.id.save_new_card);

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
                Intent data = new Intent();  //new Intent to store data
                data.putExtra("questionText", question);

                data.putExtra("answerText", answer);
                setResult(RESULT_OK, data);
                finish();

            }
        });
    }
}