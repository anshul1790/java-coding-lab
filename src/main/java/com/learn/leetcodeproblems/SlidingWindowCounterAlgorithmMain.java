package com.learn.leetcodeproblems;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SlidingWindowCounterRateLimiter {
    int tokens;
    int windowSize;
    Map<Long, Integer> timeBuckets;

    public SlidingWindowCounterRateLimiter(int tokens, int windowSize) {
        this.tokens = tokens;
        this.windowSize = windowSize;
        timeBuckets = new HashMap<>();
    }

    public void allowRequest () {
        Long currentTimeSecond = Instant.now().getEpochSecond();
        timeBuckets.keySet().removeIf(timeInBucket -> (currentTimeSecond - timeInBucket) > 0);
        int totalRequests = 0;
        for (Map.Entry<Long, Integer> key : timeBuckets.entrySet()) {
            totalRequests = totalRequests + key.getValue();
        }
        // totalRequests = timeBuckets.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("Current requests at " + currentTimeSecond +
                " Time Bucket is " + timeBuckets + " and total requests in them is " + totalRequests);
        if (totalRequests <= tokens) {
            System.out.println("Request is allowed");
            timeBuckets.merge(currentTimeSecond, 1, Integer::sum);
        } else {
            System.out.println("Request is not allowed");
        }
    }

}

public class SlidingWindowCounterAlgorithmMain {
    public static void main(String[] args) throws InterruptedException {
        SlidingWindowCounterRateLimiter rateLimiter = new SlidingWindowCounterRateLimiter(
                2, 10
        );
        for (int i = 0; i < 20; i ++) {
            rateLimiter.allowRequest();
            Thread.sleep(100);
        }

    }
}
