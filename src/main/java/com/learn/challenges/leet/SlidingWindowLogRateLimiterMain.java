package com.learn.challenges.leet;


import java.time.Duration;
import java.time.Instant;
import java.util.*;

class SlidingWindowLogRateLimiter {
    int tokens, windowInSecs;
    Deque<Instant> logSet;
    SlidingWindowLogRateLimiter (int tokens, int windowInSecs) {
        this.tokens = tokens;
        this.windowInSecs = windowInSecs;
        logSet = new LinkedList<>();
    }

    boolean allowRequest() {
        Instant curTime = Instant.now();
        logSet.removeIf(log -> Duration.between(log, curTime).getSeconds() > windowInSecs);
        if (logSet.size() < tokens) {
            logSet.offer(curTime);
            return true;
        }
        return false;
    }
}

public class SlidingWindowLogRateLimiterMain {

    public static void main(String[] args) throws InterruptedException {
        SlidingWindowLogRateLimiter rateLimiter = new SlidingWindowLogRateLimiter(
                2, 10);
        for (int i = 0; i < 100; i++) {
            boolean allowed = rateLimiter.allowRequest();
            System.out.println("RequestId " + i + " Allowed = " + allowed);
            Thread.sleep(500);;
        }
    }

}
