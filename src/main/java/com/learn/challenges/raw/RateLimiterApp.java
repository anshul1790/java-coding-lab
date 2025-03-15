package com.learn.challenges.raw;

import com.learn.challenges.ratelimiters.RateLimiterService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiterApp {


    // build the rule for given client and a specific endpoint
    // using combination of client id and endpoint
    static class RateLimitRule {
        private final String clientId;
        private final String endpoint;
        private final int limit;
        private final long windowTimeInMillis;

        public RateLimitRule(String clientId, String endpoint, int limit, long windowTimeInMillis) {
            this.clientId = clientId;
            this.endpoint = endpoint;
            this.limit = limit;
            this.windowTimeInMillis = windowTimeInMillis;
        }

        public String getClientId() {
            return clientId;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public int getLimit() {
            return limit;
        }

        public long getWindowTimeInMillis() {
            return windowTimeInMillis;
        }
    }

    // Simple API request that knows about endpoint being called
    // and client ID that is making a call
    static class ApiRequest {
        String clientId;
        String endpoint;
    }

    // Simple client identifier builder using clint id and endpoint
    static class ClientIdentifier {
        public static String buildIdentifier(String clientId, String endpoint) {
            return clientId + ":" + endpoint;
        }
    }

    // Cache of rules that contains name of key and Rate Limit Rule
    // example clientId+Get -> "and it's associated rule"
    static class RuleCache {
        private ConcurrentHashMap<String, RateLimitRule> ruleCache
                = new ConcurrentHashMap<>();

        public void updateRule(String key, RateLimitRule rateLimitRule) {
            ruleCache.put(key, rateLimitRule);
        }

        public RateLimitRule getRule(String key) {
            return ruleCache.get(key);
        }
    }

    // the rule retriever class fetches the rule on timely basis from DB
    // This is for now updating the rules in every 1 min
    // fetch from db and update the rule cache for a given client
    static class RuleRetriever implements Runnable {

        private RuleCache ruleCache;

        public RuleRetriever(RuleCache ruleCache) {
            this.ruleCache = ruleCache;
        }

        /* In a real system, query the DB to get up-to-date rules.
            Here, we simulate by inserting a static rule.
         */
        @Override
        public void run() {
            RateLimitRule rule = new RateLimitRule(
                    "clientA", "/api/messages", 100, TimeUnit.MINUTES.toMillis(1));
            String key = ClientIdentifier.buildIdentifier(rule.getClientId(), rule.getEndpoint());
            ruleCache.updateRule(key, rule);
            System.out.println("Rules updated for key: " + key);
        }
    }

    // Basic rate limit algo that calculate number of requests allowed in a given window
    static class SimpleRateLimiter {
        private int limit;
        private long windowInMillis;
        private AtomicInteger counter = new AtomicInteger();
        private volatile long windowStart;

        public SimpleRateLimiter(int limit, long windowInMillis) {
            this.limit = limit;
            this.windowInMillis = windowInMillis;
            this.windowStart = System.currentTimeMillis();
        }

        // Synchronized to ensure thread safety when resetting the window.
        public synchronized boolean allowRequest() {
            long now = System.currentTimeMillis();
            // Reset the window.
            if (now - windowStart > windowInMillis) {
                windowStart = now;
                counter.set(0);
            }
            if (counter.incrementAndGet() <= limit) {
                return true;
            }
            return false;
        }

        // Rate limit Service that we will use
        // This will help us know that for a given Rate limiter algo
        // for a given client id, the request should be allowed or not
        // This service will keep a cache of all different Algo for a given client
        static class RateLimitService {

            private Map<String, SimpleRateLimiter> limiterMap = new ConcurrentHashMap<>();
            private RuleCache ruleCache;

            public RateLimitService(RuleCache ruleCache) {
                this.ruleCache = ruleCache;
            }

            public boolean checkRequest(ApiRequest request) {
                String clientKey = ClientIdentifier.buildIdentifier(
                        request.clientId, request.endpoint
                );
                RateLimitRule rateLimitRule = ruleCache.getRule(clientKey);
                if (rateLimitRule == null) {
                    // If no rule is defined, allow the request by default.
                    return true;
                }

                limiterMap.computeIfAbsent(clientKey, k -> new SimpleRateLimiter(
                        rateLimitRule.getLimit(), rateLimitRule.getWindowTimeInMillis()
                ));
                SimpleRateLimiter rateLimiter = limiterMap.get(clientKey);
                return rateLimiter.allowRequest();
            }
        }

        public static void main(String[] args) {
            // Create the in-memory cache and rate limiter service.
            RuleCache ruleCache = new RuleCache();
            RateLimitService rateLimiterService = new RateLimitService(ruleCache);

            // Start the RulesRetriever to update our rules cache every minute.
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(new RuleRetriever(ruleCache), 0, 1, TimeUnit.MINUTES);

            // Create a simulated API request.
            ApiRequest request = new ApiRequest();
            request.clientId = "clientA";
            request.endpoint = "/api/messages";

            // Simulate 105 requests (the limit is 100 requests per minute).
            for (int i = 1; i <= 105; i++) {
                boolean allowed = rateLimiterService.checkRequest(request);
                if (allowed) {
                    System.out.println("Request " + i + " allowed.");
                } else {
                    System.out.println("Request " + i + " rate-limited.");
                }
            }

            executor.shutdown();
        }

    }

}
