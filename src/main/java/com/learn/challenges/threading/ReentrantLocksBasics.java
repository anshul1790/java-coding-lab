package com.learn.challenges.threading;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLocksBasics {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        // This definition is written in shortcut way due to functional interfaces
        Runnable task = counter::increment;

        /*
        Runnable task = () -> {
            counter.increment();
            counter.increment();
        };*/

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join(); //this will make sure that main thread waits for t1 and t2 to finish first
        t2.join();

        System.out.println("Final count is " + counter.getCount());

    }
}


class Counter {

    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            System.out.println("Current thread holding the lock is "
                    + Thread.currentThread().getName());
            count ++;
        } finally {
            lock.unlock();
        }
    }

    // locking the get method as well so that right count
    // is returned when multiple threads want to access the count
    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}