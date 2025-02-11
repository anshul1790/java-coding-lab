package com.learn.challenges.ratelimiters;

/*
Traits of tokenBucket limiter
1. maxCapacity
    - Maximum amount of tokens a bucket can hold
    - Max tokens a bucket can hold is 100 Tokens
    - This will be used to update the capacity of the
        bucket during incoming requests
2. refillRate
    - Rate at which number of tokens are added to bucket in per unit time
    - Example 10 tokens are added in 1 Second
    - Refill rate is Number of Tokens to Add / Time Unit
    - Example if 100 tokens are added in 5 minutes, then refill rate in seconds =
        = 100 / 5 * 60 = 0.33 tokens are added per second
        = OR 1 token added per 3 seconds
3. currentTokens set to maxCapacity initially
    - This will help in managing the tokens at hand to allow the incoming requests
    - If the currentTokens size is greater than zero, request is allowed else not
4. Instant currentTime
    - This will be used to calculate the elapsedTime
    - elapsedTime = currentTime - newRequestTime
    - Only if the elapsedTime > TimeUnit (0) in our case then the request is allowed
Bonus:
    - the tokensToAdd = refillRate * elapsedTime
        > this states that how many windows have passed till now
        > Suppose that last refillTime was 100 secs and new request came in at 110 seconds
        > When the refill rate is 2 tokens per second, then it means that
        > We need to add tokens for all time windows of per second
        > Thus we calculate that 110 - 100 = 10 seconds have passed
        > And for 10 seconds, we need to add 20 tokens.

    - last refill time doesn't use static value of currentTime to be last refill time
        > Rather we can calculate that how many seconds have passed till the current refill
        > this can be calculated using totalTokensAdded / refill rate
        > Meaning that if we have added 20 tokens, then we calculated that with refill rate of 2 tokens PS
        > It means that we took 10 seconds to add those 20 tokens, hence new refill time will be
        > 100 + 10 seconds, and that's when our request came in actually.
*/

import java.time.Instant;
import java.util.Optional;

public class TokenBucketRateLimiter implements IRateLimiter {

    private final int maxCapacity;
    private final int refillRate;
    private int currentTokens;
    private final long timeUnitInSeconds = 0L;
    private Instant lastRefillTime;

    public TokenBucketRateLimiter(int maxCapacity, int refillRate) {
        this.maxCapacity = maxCapacity;
        this.refillRate = refillRate;
        this.currentTokens = maxCapacity;
        this.lastRefillTime = Instant.now();

    }

    private void refillTokens() {
        Instant currentTime = Instant.now();
        long elapsedTime = currentTime.getEpochSecond() - this.lastRefillTime.getEpochSecond();
        if (elapsedTime > timeUnitInSeconds) {
            int tokensToAdd = (int) (refillRate * elapsedTime);
            this.currentTokens = Math.min(maxCapacity, currentTokens + tokensToAdd);
            System.out.printf("Time elapsed is %d and current" +
                    "token count is %d \n", elapsedTime, currentTokens);
            this.lastRefillTime = lastRefillTime.plusSeconds(
                    currentTokens / refillRate);
        }
    }


    @Override
    public boolean allowRequest() {
        refillTokens();
        if (this.currentTokens > 0) {
            this.currentTokens--;
            return true;
        } else {
            return false;
        }
    }
}
