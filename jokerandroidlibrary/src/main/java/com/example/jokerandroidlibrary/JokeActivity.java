package com.example.jokerandroidlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    private TextView textView;
    public static final String API_KEY = "JOKE_API_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(getIntent().getStringExtra(API_KEY));
    }
}
