package com.learn.challenges.threading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class ReentrantLocksComplex {

    static class BookingSystem {

        private int tickets = 15;
        private final Lock lock = new ReentrantLock(true);

        public void bookTicket (String threadName) throws InterruptedException {
            while (true) {
                //lock.lock();
                if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
                    try {
                        if (tickets > 0) {
                            Thread.sleep(50);
                            tickets --;
                            System.out.println(threadName +
                                    " - Thread is booking the ticket; tickets left " + tickets);
                        } else {
                            System.out.println("Tickets are sold out. "
                                    + threadName + " could not book.");
                            break;
                        }
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    finally {
                        lock.unlock();
                    }
                } else {
                    try {
                        Thread.sleep(50);
                        System.out.println(threadName +
                                " - Thread is waiting to acquire the lock");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        BookingSystem book = new BookingSystem();

        for (int i = 1; i <= 10; i ++) {
            new Thread(() -> {
                try {
                    book.bookTicket(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

    }
}



