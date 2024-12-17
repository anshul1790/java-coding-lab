package com.learn.challenges.threading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource {

    String resourceName;
    private final Lock lock = new ReentrantLock();

    public Resource(String resourceName) {
        this.resourceName = resourceName;
    }

    public boolean getLock() {
        return lock.tryLock();
        /*
        lock.lock(); // using this put the lock in deadlock condition
        System.out.println("The lock is acquired by " + Thread.currentThread().getName() +
                " for the resource " + this.getResourceName());*/
        //return true;
    }

    public void releaseLock() {
        lock.unlock();
    }

    public String getResourceName() {
        return resourceName;
    }

}


public class ReentrantLocksIntermediate {

    public static void main(String[] args) throws InterruptedException {

        Resource resource1 = new Resource("Resource 1");
        Resource resource2 = new Resource("Resource 2");

        Thread t1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        if (resource1.getLock()) {
                            try {
                                Thread.sleep(100);
                                if (resource2.getLock()) {
                                    try {
                                        System.out.println("Task 1 locked both resources");
                                    } finally {
                                        resource2.releaseLock();
                                    }
                                }
                            } catch (InterruptedException ie) {
                                Thread.currentThread().interrupt();
                            }
                            finally {
                                resource1.releaseLock();
                            }
                        } else {
                            System.out.println("Task 1 could not acquire the Resource 1");
                        }
                    }
                }
        , "First Thread");

        Thread t2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        if (resource2.getLock()) {
                            try {
                                Thread.sleep(100);
                                if (resource1.getLock()) {
                                    try {
                                        System.out.println("Task 2 locked both resources");
                                    } finally {
                                        resource1.releaseLock();
                                    }
                                }
                            } catch (InterruptedException ie) {
                                Thread.currentThread().interrupt();
                            }
                            finally {
                                resource2.releaseLock();
                            }
                        } else {
                            System.out.println("Task 2 could not acquire the Resource 2");
                        }
                    }
                }
        , "Second Thread");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}

