package com.learn.challenges.linkedlist;

public class ReverseLinkedList {

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
        reverseLinkedList(linkedList);
    }

    /* Input: Linked List = 1 -> 2 -> 3 -> 4 -> NULL
    Output: Reversed Linked List = 4 -> 3 -> 2 -> 1 -> NULL */
    static void reverseLinkedList(Node head) {
        Node current = head;
        Node next = null;
        Node prev = null;
        while (current != null) {
            next = current.nextNode;
            current.nextNode = prev;
            prev = current;
            current = next;
        }
        System.out.println(prev);
    }
}
