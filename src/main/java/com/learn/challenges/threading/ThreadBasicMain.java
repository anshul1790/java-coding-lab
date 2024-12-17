package com.learn.challenges.threading;

public class ThreadBasicMain {

    static class MyThreadClass extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println("MyThread Class thread has started "
                        + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class MyRunnableThreadClass implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println("MyRunnable thread has started "
                        + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void main(String[] args) {
        MyThreadClass thread1 = new MyThreadClass();
        thread1.start();

        MyRunnableThreadClass myRunnableThreadClass = new MyRunnableThreadClass();
        Thread thread2 = new Thread(myRunnableThreadClass);
        thread2.start();

        // another way of starting the thread
        new Thread(new MyRunnableThreadClass()).start();

        // implementing the thread within the class itself
        new Thread(() -> {
            try {
                thread1.join(2000);
                Thread.sleep(1000);
                System.out.println("My Internal class thread has started "
                        + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        System.out.println("This is the main thread " +
                Thread.currentThread().getName());
    }
}

