package com.learn.challenges.linkedlist;

public class RotateLinkedList {

    static Node createLinkedList() {
        Node head = new Node(1);
        Node current = head;
        for(int i = 2; i <= 4; i ++) {
            current.nextNode = new Node(i);
            current = current.nextNode;
        }
        return head;
    }

    public static void main(String[] args) {
        Node linkedList = createLinkedList();
        rotateLinkedList(linkedList, 1);
    }

    /* Input: linked list = 10 -> 20 -> 30 -> 40 -> 50, k = 4
    Output: 50 -> 10 -> 20 -> 30 -> 40*/
    static void rotateLinkedList(Node head, int k) {

    }
}
