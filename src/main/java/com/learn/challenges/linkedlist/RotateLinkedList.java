package com.learn.challenges.linkedlist;

public class RotateLinkedList {

    static Node createLinkedList() {
        Node head = new Node(1);
        Node current = head;
        for(int i = 2; i <= 5; i ++) {
            current.nextNode = new Node(i);
            current = current.nextNode;
        }
        return head;
    }

    public static void main(String[] args) {
        Node linkedList = createLinkedList();
        rotateLinkedList(linkedList, 4);
    }

    /* Input: linked list = 10 -> 20 -> 30 -> 40 -> 50, k = 4
    Output: 50 -> 10 -> 20 -> 30 -> 40*/
    static void rotateLinkedList(Node head, int k) {
        Node current = head;
        int len = 1;
        while (current.nextNode != null) {
            current = current.nextNode;
            len++;
        }
        k = k % len;
        current.nextNode = head;
        while (k > 0) {
            current = current.nextNode;
            k--;
        }
        head = current.nextNode;
        current.nextNode = null;
        System.out.println(head);
    }
}
