package com.yash.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

//    final JockerJava jockerJava = new JockerJava();
//    String joke;
//    private EndpointsAsyncTask mAsyncTask;

    private LayoutInflater inflater;
    private ViewGroup container;
    private LinearLayout progressBarHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater = LayoutInflater.from(this);
        container = (ViewGroup) findViewById(android.R.id.content);
        inflater.inflate(R.layout.progress_layout, container);
        progressBarHolder = (LinearLayout) findViewById(R.id.progress_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        EndpointsAsyncTask task = new EndpointsAsyncTask(this, progressBarHolder);
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
