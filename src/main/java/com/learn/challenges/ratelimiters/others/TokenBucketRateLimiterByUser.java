package com.learn.challenges.ratelimiters.others;

import com.learn.challenges.ratelimiters.IRateLimiter;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class TokenBucketRateLimiterByUser {

    static class TokenBucket implements IRateLimiter {
        int maxCapacity;
        int refillRate;
        int currentCapacity;
        Instant lastRefillTime;

        public TokenBucket(int maxCapacity, int refillRate) {
            this.maxCapacity = maxCapacity;
            this.refillRate = refillRate; // assuming that tokens are added every 1 seconds
            this.currentCapacity = maxCapacity;
            this.lastRefillTime = Instant.now();
        }

        public void refillTokens() {
            Instant currentReqTime = Instant.now();
            int elapsedTime = (int) (lastRefillTime.getEpochSecond()
                    - currentReqTime.getEpochSecond());
            int tokensToRefill = elapsedTime * (refillRate / 1); // 1 denotes time unit in secs here.
            if (tokensToRefill > 0) {
                this.currentCapacity = Math.min(maxCapacity, currentCapacity + tokensToRefill);
                lastRefillTime = lastRefillTime.plusSeconds(tokensToRefill / refillRate);
            }
        }

        @Override
        public boolean allowRequest() {
            refillTokens();
            if (this.currentCapacity > 0) {
                this.currentCapacity --;
                return true;
            } else {
                return false;
            }
        }

    }

    static class TokenBucketRateLimiterByUserId {

        static final int DEFAULT_MAX_CAPACITY = 2;
        static final int DEFAULT_REFILL_RATE = 1; // 1 token per unit time seconds
        Map<String, TokenBucket> tokenBucketRateLimiterMap;
        int maxCapacity;
        int refillRate;

        public TokenBucketRateLimiterByUserId() {
            this(DEFAULT_MAX_CAPACITY, DEFAULT_REFILL_RATE);
        }

        public TokenBucketRateLimiterByUserId(int maxCapacity, int refillRate) {
            this.maxCapacity = maxCapacity;
            this.refillRate = refillRate;
            tokenBucketRateLimiterMap = new HashMap<>();
        }

        public boolean processRequestByUserId(String userId) {
            TokenBucket tokens = tokenBucketRateLimiterMap.computeIfAbsent(
                    userId, k -> new TokenBucket(maxCapacity, refillRate));
            return tokens.allowRequest();
        }

    }

    public static void main(String[] args) {

    }
}
