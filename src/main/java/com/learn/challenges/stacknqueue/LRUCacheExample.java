package com.learn.challenges.stacknqueue;

import java.util.*;

public class LRUCacheExample {

    private final int capacity;
    Map<Integer, Integer> keys;
    Deque<Integer> integerQueue;

    public LRUCacheExample(int capacity) {
        this.capacity = capacity;
        this.keys = new HashMap<>();
        this.integerQueue = new LinkedList<>();
    }


    public void set(int key, int value) {
        if (keys.containsKey(key)) {
            integerQueue.remove(key);
        } else if (integerQueue.size() == capacity) {
            int last = integerQueue.removeLast();
            keys.remove(last);
        }
        integerQueue.addFirst(key);
        keys.put(key, value);
    }

    public Integer get(int key) {
        if (!keys.containsKey(key)) {
            return -1;
        }
        integerQueue.remove(key);
        integerQueue.addFirst(key);
        return keys.get(key);
    }

    @Override
    public String toString() {
        return "LRUCacheExample{" +
                "capacity=" + capacity +
                ", keys=" + keys +
                ", integerQueue=" + integerQueue +
                '}';
    }

    public static void main(String[] args) {
        LRUCacheExample lruCache = new LRUCacheExample(3);
        //lruCache.set(2, 4);
        lruCache.set(3, 4);
        lruCache.set(5, 8);
        lruCache.set(2, 9);
        lruCache.get(3);
        lruCache.set(4, 9);
        lruCache.set(6, 9);
        System.out.println(lruCache);
    }
}
