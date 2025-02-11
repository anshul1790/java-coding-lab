package com.learn.challenges.basicsothers;

import java.util.*;

public class ConsistentHashing {

    private final int virtualNodes;
    private final TreeMap<Integer, String> ring = new TreeMap<>();
    private final HashFunction hashFunction = new HashFunction();

    public ConsistentHashing(int virtualNodes, List<String> partitions) {
        this.virtualNodes = virtualNodes;
        for (String partition : partitions) {
            addPartition(partition);
        }
    }

    public void addPartition(String partition) {
        for (int i = 0; i < virtualNodes; i++) {
            int hash = hashFunction.hash(partition + "-VN" + i);
            ring.put(hash, partition);
        }
    }

    public void removePartition(String partition) {
        for (int i = 0; i < virtualNodes; i++) {
            int hash = hashFunction.hash(partition + "-VN" + i);
            ring.remove(hash);
        }
    }

    public String getPartition(String data) {
        if (ring.isEmpty()) {
            throw new IllegalStateException("No partitions available");
        }
        int hash = hashFunction.hash(data);
        // Find the first key >= hash or wrap around to the first key
        Map.Entry<Integer, String> entry = ring.ceilingEntry(hash);
        if (entry == null) {
            entry = ring.firstEntry();
        }
        return entry.getValue();
    }

    public static void main(String[] args) {
        List<String> partitions = Arrays.asList("A", "B", "C", "D");
        ConsistentHashing consistentHashing = new ConsistentHashing(3, partitions);

        String[] dataItems = {"item1", "item2", "item3", "item4", "item5"};
        for (String data : dataItems) {
            System.out.println("Data: " + data + " -> Partition: " + consistentHashing.getPartition(data));
        }

        System.out.println("\nAdding new partition E...");
        consistentHashing.addPartition("E");
        for (String data : dataItems) {
            System.out.println("Data: " + data + " -> Partition: " + consistentHashing.getPartition(data));
        }
    }

    // Simple hash function using Java's built-in hashing
    static class HashFunction {
        public int hash(String key) {
            return (key.hashCode() & 0x7fffffff) % 360;
        }
    }
}

