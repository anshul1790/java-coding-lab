package com.learn.challenges.basicsothers;

public class Base58Encoder {

    static class SnowflakeIdGenerator {
        private final long epoch; // Custom epoch
        private final long nodeIdBits = 10L; // Max 1024 nodes
        private final long sequenceBits = 12L; // Sequence within a node

        private final long nodeIdShift = sequenceBits;
        private final long timestampShift = sequenceBits + nodeIdBits;
        private final long maxSequence = ~(-1L << sequenceBits);

        private long lastTimestamp = -1L;
        private long sequence = 0L;
        private final long nodeId;

        public SnowflakeIdGenerator(long epoch, long nodeId) {
            if (nodeId >= (1 << nodeIdBits) || nodeId < 0) {
                throw new IllegalArgumentException("Node Id is out of range");
            }
            this.epoch = epoch;
            this.nodeId = nodeId;
        }

        public synchronized long nextId() {
            long timestamp = System.currentTimeMillis() - epoch;

            if (timestamp == lastTimestamp) {
                sequence = (sequence + 1) & maxSequence;
                if (sequence == 0) {
                    timestamp = waitForNextMillisecond(lastTimestamp);
                }
            } else {
                sequence = 0;
            }

            lastTimestamp = timestamp;

            return (timestamp << timestampShift) | (nodeId << nodeIdShift) | sequence;
        }

        private long waitForNextMillisecond(long lastTimestamp) {
            long timestamp;
            do {
                timestamp = System.currentTimeMillis() - epoch;
            } while (timestamp <= lastTimestamp);
            return timestamp;
        }

    }

    private static final String BASE58_ALPHABET
            = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
    private static final int BASE = 58;

    // Method to convert long ID to Base58
    public static String toBase58(long id) {
        StringBuilder base58 = new StringBuilder();

        if (id == 0) {
            return String.valueOf(BASE58_ALPHABET.charAt(0));
        }

        while (id > 0) {
            int remainder = (int) (id % BASE);
            base58.insert(0, BASE58_ALPHABET.charAt(remainder));
            id /= BASE;
        }

        return base58.toString();
    }

    // Main method to generate the short URL using Snowflake ID
    public static String generateShortUrl(long epoch, long nodeId) {
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(epoch, nodeId);
        long id = idGenerator.nextId(); // Generate a unique Snowflake ID
        System.out.println("The 64 bit id is " + id);
        return toBase58(id); // Convert to Base58 encoded short URL
    }


    public static void main(String[] args) {

        long epoch = 1577836800000L; // Custom epoch (January 1, 2020)
        long nodeId = 1L; // Example node ID, you can change this as needed

        String shortUrl = generateShortUrl(epoch, nodeId);
        System.out.println("Generated Short URL: " + shortUrl);
    }

}
