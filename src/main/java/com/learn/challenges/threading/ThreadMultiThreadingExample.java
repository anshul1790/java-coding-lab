package com.learn.challenges.threading;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadMultiThreadingExample {

    public static void main(String[] args) {
        CountDown countDown = new CountDown();
        new Thread(new CountDownThread(countDown)).start();
        new Thread(new CountDownThread(countDown)).start();
    }
}

class CountDownThread implements Runnable {

    CountDown countDown;
    public CountDownThread(CountDown countDown) {
        this.countDown = countDown;
    }

    @Override
    public void run() {
        /*countDown.startCountDown();
        countDown.startCountDownStackVariable();*/
    }
}

class CountDown {

     /*
     SharedCounter is instance variable which is shared between the threads.
     - both the variables are changing the instance variable
     - the instance variables are stored/allocated are at heap
     - it means both thread will have shared "sharedCounter" variable value
     - To control the access of heap variable once at a time
     - we can have synchronized block or synchronized method
     - This is like intrinsic lock and block the code to be used by one
     thread only until this thread releases it
     */
    private int sharedCounter;

    // synchronized -> Method level thread lock to block access
    // to the another thread until the current thread release the lock.
    public void startCountDown() {
        synchronized (this){ // another way
            for (sharedCounter = 0; sharedCounter < 10; sharedCounter ++) {
                System.out.println("Processing using " +
                        Thread.currentThread().getName() + ": Shared counter val = " + sharedCounter);
            }
        }
    }
    /*
    - Here the sharedCounter variable is created inside own thread stack.
    - In example above the
     */


    public void startCountDownStackVariable() {
        int localCounter; // stack variable
        for (localCounter = 0; localCounter < 10; localCounter ++) {
            System.out.println("Processing using " +
                    Thread.currentThread().getName() + ": Shared counter val = " + localCounter);
        }
    }

}