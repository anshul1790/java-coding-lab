package com.learn.challenges.basicsothers;

import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SimplifiedConsistentHashing {

    private static final int RING_SIZE = 360; // Ring range: 0 to 360
    private final SortedMap<Integer, String> ring = new TreeMap<>(); // Represents the ring
    private final MessageDigest md;

    public SimplifiedConsistentHashing() {
        try {
            md = MessageDigest.getInstance("SHA-256"); // Hash function for consistent hashing
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }

    // Add a node to the ring
    public void addNode(String nodeName) {
        int nodeHash = getHash(nodeName);
        ring.put(nodeHash, nodeName);
        System.out.println("Added node '" + nodeName + "' at position " + nodeHash);
    }

    // Remove a node from the ring
    public void removeNode(String nodeName) {
        int nodeHash = getHash(nodeName);
        ring.remove(nodeHash);
        System.out.println("Removed node '" + nodeName + "' from position " + nodeHash);
    }

    // Get the node responsible for a given key
    public String getNodeForKey(String key) {
        int keyHash = getHash(key);
        // Find the closest node clockwise
        if (!ring.containsKey(keyHash)) {
            SortedMap<Integer, String> tailMap = ring.tailMap(keyHash);
            keyHash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        }
        return ring.get(keyHash);
    }

    // Compute hash of a string using SHA-256
    private int getHash(String input) {
        byte[] digest = md.digest(input.getBytes());
        return Math.abs(Arrays.hashCode(digest)) % RING_SIZE;
    }

    public static void main(String[] args) {
        SimplifiedConsistentHashing hashing = new SimplifiedConsistentHashing();

        // Adding nodes
        hashing.addNode("NodeA");
        hashing.addNode("NodeB");
        hashing.addNode("NodeC");

        // Assign keys to nodes
        String[] keys = {"Key1", "Key2", "Key3", "Key4", "Key5"};
        for (String key : keys) {
            String node = hashing.getNodeForKey(key);
            System.out.println("Key '" + key + "' is assigned to node '" + node + "'");
        }

        // Adding a new node
        System.out.println("\nAdding NodeD...");
        hashing.addNode("NodeD");

        // Re-assign keys to nodes
        for (String key : keys) {
            String node = hashing.getNodeForKey(key);
            System.out.println("Key '" + key + "' is now assigned to node '" + node + "'");
        }

        // Removing a node
        System.out.println("\nRemoving NodeB...");
        hashing.removeNode("NodeB");

        // Re-assign keys to nodes
        for (String key : keys) {
            String node = hashing.getNodeForKey(key);
            System.out.println("Key '" + key + "' is now assigned to node '" + node + "'");
        }
    }
}

