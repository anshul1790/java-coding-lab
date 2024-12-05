package com.learn.challenges.leet;


import java.time.Instant;

class TokenBucketRateLimiter {
    int maxCapacity;
    int refillRate;
    int tokens;
    Instant lastRefillTime;

    TokenBucketRateLimiter(int tokens, int refillRate) {
        this.maxCapacity = tokens;
        this.tokens = tokens;
        this.refillRate = refillRate; // considering this is PER SECOND only
        this.lastRefillTime = Instant.now();
    }


    public boolean allowRequest() {
        Instant tokenRequestedAt = Instant.now();
        int elapsedTime = (int) (tokenRequestedAt.getEpochSecond() - lastRefillTime.getEpochSecond());
        elapsedTime = Math.max(elapsedTime, 0);
        int tokensToAdd = elapsedTime * refillRate;
        this.tokens = Math.min(tokens + tokensToAdd , maxCapacity);
        System.out.println("Elapsed time is " + elapsedTime +
                " current tokens at allow request is " + this.tokens);
        if (this.tokens > 0) {
            tokens --;
            System.out.println("Consumed token successfully, remaining tokens " + this.tokens);
            lastRefillTime = lastRefillTime.plusSeconds(tokens / refillRate);
            return true;
        } else {
            System.out.println("No token left");
            return false;
        }
    }

}

public class TokenBucketRateLimiterMain {
    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(10, 5);
        for (int i = 0; i < 50; i ++) {
            System.out.println("Requesting token availability for request:" + i);
            rateLimiter.allowRequest();
            Thread.sleep(500);
        }
    }
}
