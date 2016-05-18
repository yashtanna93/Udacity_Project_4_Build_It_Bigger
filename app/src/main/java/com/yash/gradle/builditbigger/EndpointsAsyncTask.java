package com.yash.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jokerandroidlibrary.JokeActivity;
import com.example.yashtanna93.myjokeapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by yashtanna93 on 5/17/16.
 */
class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    LinearLayout progressBarHolder;
//    public AsyncResponse response;

//    public interface AsyncResponse {
//        void jokeFetched(String output);
//    }
//
//    public EndpointsAsyncTask(AsyncResponse response) {
//        this.response = response;
//    }

    public EndpointsAsyncTask(Context context, LinearLayout progressBarHolder) {
        this.context = context;
        this.progressBarHolder = progressBarHolder;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBarHolder.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("https://build-it-bigger-1314.appspot.com/_ah/api/");
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(true);
//                        }
//                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            String joke = myApiService.tellJoke().execute().getData();
            return joke;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        Log.v("JOKE API", result);
//        response.jokeFetched(result);
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra("JOKE_API_KEY", result);
        context.startActivity(intent);
        progressBarHolder.setVisibility(View.GONE);
    }
}
