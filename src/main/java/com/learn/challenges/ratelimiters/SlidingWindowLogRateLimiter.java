package com.learn.challenges.ratelimiters;

import com.sun.source.tree.WhileLoopTree;

import java.time.Duration;
import java.time.Instant;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class SlidingWindowLogRateLimiter implements IRateLimiter {

    int window;
    int capacity;
    Queue<Instant> logQueue;

    public SlidingWindowLogRateLimiter(int window, int capacity) {
        this.window = window;
        this.capacity = capacity;
        logQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public boolean allowRequest() {
        Instant currentReqTime = Instant.now();
        logQueue.removeIf(r -> Duration.between(r, currentReqTime).getSeconds() > window);
        if (logQueue.size() < capacity) {
            logQueue.offer(currentReqTime);
            return true;
        }
        return false;
    }
}
