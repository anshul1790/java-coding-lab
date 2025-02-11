package com.learn.challenges.ratelimiters;

/*
    Example of DI where service can use different rate limiters
    without going in-depth of each rate limiter code.
*/

import com.learn.challenges.ratelimiters.others.TokenBucketRateLimiter429;

import java.util.Optional;

public class RateLimiterService {

    private final IRateLimiter rateLimiter;

    public RateLimiterService(IRateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    public boolean processRequest() {
        return rateLimiter.allowRequest();
    }

    public static void main(String[] args) {
        /*IRateLimiter tokenBucketRateLimiter = new TokenBucketRateLimiter(
                1, 1);
        RateLimiterService rateLimiterService = new RateLimiterService(
                tokenBucketRateLimiter);*/
        //IRateLimiter rateLimiter = new LeakyBucketRateLimiter(5, 1);
        IRateLimiter rateLimiter = new SlidingWindowCounterRateLimiter(5, 1);
        RateLimiterService rateLimiterService = new RateLimiterService(
                rateLimiter
        );
        int totalRequests = 20;
        for (int i = 0; i < totalRequests; i++) {
            try {
                Thread.sleep(200);
                System.out.printf("Processing request and status is %s \n",
                        rateLimiterService.processRequest());
            } catch (InterruptedException ie) {
                System.out.printf("Exception occurred %s \n", ie.getMessage());
            }
        }
    }
}
