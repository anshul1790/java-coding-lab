package com.learn.challenges.dsa.linkedlist;

class Node {
    int val;
    Node nextNode;

    Node(int val) {
        this.val = val;
        this.nextNode = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", nextNode=" + nextNode +
                '}';
    }
}
