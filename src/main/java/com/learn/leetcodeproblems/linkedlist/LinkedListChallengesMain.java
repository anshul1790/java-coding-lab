package com.learn.leetcodeproblems.linkedlist;

class Node {
    int val;
    Node nextNode;
    Node (int val) {
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


public class LinkedListChallengesMain {
    public static void main(String[] args) {
        Node myNode = new Node(0);
        Node current = myNode;
        for (int i = 1; i <= 5; i++) {
            current.nextNode = new Node(i);
            current = current.nextNode;
        }
        //findMiddleOfLinkedList(myNode);
        //reverseLinkedList(myNode);
        rotateLinkedList(myNode, 1);
    }

    static void findMiddleOfLinkedList(Node linkedList) {
        Node head = linkedList;
        Node slowPointer = head;
        Node fastPointer = head;
        while (fastPointer != null && fastPointer.nextNode != null) {
            slowPointer = slowPointer.nextNode;
            fastPointer = fastPointer.nextNode.nextNode;
        }
        assert slowPointer != null;
        System.out.println(slowPointer.val);
    }

    //Input: Linked List = 1 -> 2 -> 3 -> 4 -> NULL
    //Output: Reversed Linked List = 4 -> 3 -> 2 -> 1 -> NULL
    static void reverseLinkedList (Node linkedList) {
        Node current = linkedList;
        Node prev = null;
        Node next = null;
        while (current != null) {
            next = current.nextNode;
            current.nextNode = prev;
            prev = current;
            current = next;
        }
        System.out.println(prev);
    }

    //Input: linked list = 10 -> 20 -> 30 -> 40 -> 50, k = 4
    //Output: 50 -> 10 -> 20 -> 30 -> 40

    //Input: linked list = 10 -> 20 -> 30 -> 40, k = 6, 2
    //Output: 30 -> 40 -> 10 -> 20
    static void rotateLinkedList (Node head, int k) {
        int linkedListLength = 1;
        Node current = head;
        while (current.nextNode != null) {
            current = current.nextNode;
            linkedListLength++;
        }
        k = k % linkedListLength;

        current.nextNode = head;
        current = head;

        for (int i = 1; i < k; i++) {
            current = current.nextNode;
        }

        head = current.nextNode;
        current.nextNode = null;
        System.out.println(head);

    }

}












