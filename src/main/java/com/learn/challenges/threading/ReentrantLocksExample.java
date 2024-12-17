package com.learn.challenges.threading;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLocksExample {
    public static void main(String[] args) {
        PrintingQueue printingQueue = new PrintingQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new PrintingJob(printingQueue));
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}

class PrintingQueue {

    ReentrantLock myLock = new ReentrantLock();

    public void startPrintingJob(Object document) {
        myLock.lock();
        try {
            System.out.println("Currently printing " + document + " and thread name "
                    + Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("The job of thread is completed " +
                    Thread.currentThread().getName());
            myLock.unlock();
        }
    }
}

class PrintingJob implements Runnable {

    PrintingQueue printingQueue;

    public PrintingJob(PrintingQueue printingQueue) {
        this.printingQueue = printingQueue;
    }

    @Override
    public void run() {
        System.out.println("Starting the printing job for the thread "
                + Thread.currentThread().getName());
        printingQueue.startPrintingJob(new Object());
    }
}