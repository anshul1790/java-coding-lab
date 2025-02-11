package com.learn.challenges.ratelimiters;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class LeakyBucketRateLimiter implements IRateLimiter {

    public static class Request {
        int id;
        public Request(int id) {
            this.id = id;
        }
    }

    int capacity;
    int leakRate;
    boolean running;
    int requestInc;
    Queue<Request> requestQueue;

    public LeakyBucketRateLimiter(int capacity, int leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.requestQueue = new LinkedBlockingQueue<>(capacity);
        this.running = true;
        new Thread(this::leakRequests).start();
    }

    public boolean addRequestToQueue(Request request) {
        boolean isAddedToQueue = requestQueue.offer(request);
        if (isAddedToQueue) {
            System.out.println("Request accepted " + request.id);
            return true;
        } else {
            System.out.println("Request rejected " + request.id);
            return false;
        }
    }

    public void processRequest() {
        Request processed = requestQueue.poll();
        if (processed != null) {
            System.out.printf("The request is processed successfully %d \n", processed.id);
        } else {
            System.out.println("There is nothing to process, seems like queue is empty"
                    +  requestQueue.size());
        }
    }

    public void leakRequests() {
        while (running) {
            try {
                processRequest();
                Thread.sleep(leakRate * 1000);
            } catch(InterruptedException ie) {
                System.out.println("Exception " + ie);
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void stopLeaking() {
        running = false; // Stop the leaking thread
    }

    @Override
    public boolean allowRequest() {
        Request r = new Request(++requestInc);
        return addRequestToQueue(r);
    }
}
