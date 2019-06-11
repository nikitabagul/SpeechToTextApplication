package com.example.speechtotextapplication;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
TextView voiceInput,speakbtn;
final int REQ_CODE_SR = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        voiceInput = findViewById(R.id.textView);
        speakbtn = findViewById(R.id.btnSpeak);
        speakbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for calling inbuilt speech recogniser

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); //implit intent
                //to invoke language support and language form
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                //Give local support speech recogniser
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                //It's my recogniser
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Bol Halke Halke");

                startActivityForResult(intent,REQ_CODE_SR);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case REQ_CODE_SR:
                if (resultCode == RESULT_OK && null!=data)
                {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);//storing the result in array of string
                    voiceInput.setText(result.get(0));//setting the 1st "BOLO" text as the spoken words form the 0th index

                }
                break;

        }
    }
}
