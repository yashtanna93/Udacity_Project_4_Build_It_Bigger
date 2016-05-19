package com.yash.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by yashtanna93 on 5/18/16.
 */
public class AsyncTest extends AndroidTestCase {
    private final CountDownLatch mCountDownLatch = new CountDownLatch(1);
    private String mJoke;
    public void testVerifyNonEmptyResponse() {
        new EndpointsAsyncTask(new EndpointsAsyncTask.AsyncResponse() {
            @Override
            public void jokeFetched(String output) {
                mJoke = output;
                mCountDownLatch.countDown();
            }
        }).execute();
        try {
            mCountDownLatch.await(20, TimeUnit.SECONDS);
            assertNotNull("We have a joke: ", mJoke);
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }
    }
}
