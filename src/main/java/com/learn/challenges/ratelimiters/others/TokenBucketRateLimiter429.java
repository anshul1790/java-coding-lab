package com.learn.challenges.ratelimiters.others;

/*

*/

import com.learn.challenges.ratelimiters.IRateLimiter;

import java.time.Duration;
import java.time.Instant;

public class TokenBucketRateLimiter429 implements IRateLimiter {

    private final int maxCapacity;
    private final int refillRate;
    private int currentTokens;
    private final long timeUnitInSeconds = 0L;
    private Instant lastRefillTime;

    public TokenBucketRateLimiter429(int maxCapacity, int refillRate) {
        this.maxCapacity = maxCapacity;
        this.refillRate = refillRate;
        this.currentTokens = maxCapacity;
        this.lastRefillTime = Instant.now();

    }

    private void refillTokens() {
        Instant currentTime = Instant.now();
        long elapsedTime = currentTime.getEpochSecond() - this.lastRefillTime.getEpochSecond();
        int tokensToAdd = (int) (refillRate * elapsedTime);
        if (tokensToAdd > 0) {
            this.currentTokens = Math.min(maxCapacity, currentTokens + tokensToAdd);
            System.out.printf("Time elapsed is %d and current " +
                    "token count is %d \n", elapsedTime, currentTokens);
            this.lastRefillTime = lastRefillTime.plusSeconds(
                    currentTokens / refillRate);
        }
    }

    public boolean allowRequest() {
        refillTokens();
        if (this.currentTokens > 0) {
            this.currentTokens--;
            return true;
        } else {
            long lastRefillTimeRateMillis = lastRefillTime.toEpochMilli()
                    + (1000 / refillRate);
            long currentTimeMillis = Instant.now().toEpochMilli();
            System.out.printf("Too many request, should retry next request in %d millis\n",
                    Math.toIntExact(lastRefillTimeRateMillis - currentTimeMillis));
            return false;
        }
    }

}
