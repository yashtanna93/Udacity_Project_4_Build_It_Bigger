package com.yash.gradle.builditbigger.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jokerandroidlibrary.JokeActivity;
import com.yash.gradle.builditbigger.EndpointsAsyncTask;
import com.yash.gradle.builditbigger.R;

public class MainActivity extends AppCompatActivity {


    private LayoutInflater inflater;
    private ViewGroup container;
    LinearLayout progressBarHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater = LayoutInflater.from(this);
        container = (ViewGroup) findViewById(android.R.id.content);
        inflater.inflate(R.layout.progress_layout, container);
        progressBarHolder = (LinearLayout) findViewById(R.id.progress_layout);
    }

    public void tellJoke(View view) {
        progressBarHolder.setVisibility(View.VISIBLE);
        EndpointsAsyncTask task = new EndpointsAsyncTask(new EndpointsAsyncTask.AsyncResponse() {
            @Override
            public void jokeFetched(String output) {
                Toast.makeText(getApplicationContext(), output, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), JokeActivity.class);
                intent.putExtra("JOKE_API_KEY", output);
                startActivity(intent);
                progressBarHolder.setVisibility(View.GONE);
            }
        });
        task.execute();
//        new EndpointsAsyncTask(new EndpointsAsyncTask.AsyncResponse() {
//            @Override
//            public void jokeFetched(String joke) {
//                Toast.makeText(getApplicationContext(), joke, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), JokeActivity.class);
//                intent.putExtra("JOKE_API_KEY", joke);
//                startActivity(intent);
//            }
//        }).execute(this);
//        joke = jockerJava.randomJoke();
//        Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, JokeActivity.class);
//        intent.putExtra("JOKE_API_KEY", joke);
//        startActivity(intent);
    }
}
