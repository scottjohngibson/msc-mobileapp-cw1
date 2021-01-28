package com.example.fibonacciseq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.fibonacci_seq.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {

        Intent intent = new Intent(this, SecondScreen.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();


        if(Integer.parseInt(message) < 16)
        {
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }

    }
}