package com.yash.gradle.builditbigger;

import android.test.AndroidTestCase;

/**
 * Created by yashtanna93 on 5/18/16.
 */
public class AsyncTest extends AndroidTestCase {
    public void testVerifyNonEmptyResponse() {
        new EndpointsAsyncTask(new EndpointsAsyncTask.AsyncResponse() {
            @Override
            public void jokeFetched(String output) {
                assertNotNull("We have a joke: ", output);
            }
        }).execute(getContext());
    }
}
