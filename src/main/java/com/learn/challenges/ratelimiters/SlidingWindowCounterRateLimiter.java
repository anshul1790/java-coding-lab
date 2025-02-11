package com.learn.challenges.ratelimiters;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/*
1. timeWindow defines the window in which max requests are allowed
    // example in 1 min / 60 seconds -> 100 requests are allowed
2. requests defines the total requests allowed in a timeWindow

Algo
1. Create smaller time bucket whenever a request comes.
2. Add the requests that arrives in same time bucket
3. When request comes, check window, keep only those time buckets which are within timeWindow
    example if timeWindow unit = 5, and there are requests in time window like 2, 3, 4, 5, 6
    and next request comes at 10, then all time windows before 5 will be gone,
    and newer time window will be 5, 6, 10
4. Count the number of total requests in timeWindow
    if total requests size is less than allowed requests then allow request and
       add new time to existing time bucket or new time bucket.
    else pass and reject request.
*/
public class SlidingWindowCounterRateLimiter implements IRateLimiter {

    int timeWindow;
    int allowedRequests;
    Map<Long, Integer> timeBucket;

    public SlidingWindowCounterRateLimiter(int timeWindow, int allowedRequests) {
        this.timeWindow = timeWindow;
        this.allowedRequests = allowedRequests;
        this.timeBucket = new HashMap<>();
    }

    @Override
    public boolean allowRequest() {
        long currentTime = Instant.now().getEpochSecond();
        timeBucket.keySet().removeIf(time -> (currentTime - time) > timeWindow);
        int totalRequests = 0;
        for (Integer requests : timeBucket.values()) {
            totalRequests += requests;
        }
        if (totalRequests < allowedRequests) {
            //timeBucket.merge(currentTime, 1, (o1, o2) -> o1 + o2);
            timeBucket.merge(currentTime, 1, new BiFunction<>() {
                @Override
                public Integer apply(Integer integer1, Integer integer2) {
                    return integer1 + integer2;
                }
            });
            return true;
        }
        return false;
    }
}
