package com.learn.challenges.linkedlist;

public class DeleteLastOccurrenceMain {
    static Node createLinkedList() {
        Node head = new Node(1);
        Node current = head;
        for(int i = 2; i <= 5; i ++) {
            current.nextNode = new Node(i);
            current = current.nextNode;
        }
        current.nextNode = new Node(1);
        current = current.nextNode;
        current.nextNode = new Node(1);
        current = current.nextNode;
        current.nextNode = new Node(2);
        current = current.nextNode;
        System.out.println(head);
        return head;
    }

    public static void main(String[] args) {
        Node linkedList = createLinkedList();
        deleteLastOccurrence(linkedList, 1);
    }

    /*Input: head: 1 -> 2 -> 3 ->1  -> 4 -> NULL, key = 1
    Output: 1 -> 2 -> 3 -> 4 -> NULL*/
    static void deleteLastOccurrence(Node head, int item) {
        Node current = head;
        Node prev = null;
        Node lastOccurrenceCurrent = null;
        Node lastOccurrencePrev = null;
        while (current != null) {
            if (current.val == item) {
                lastOccurrenceCurrent = current;
                lastOccurrencePrev = prev;
            }
            prev = current;
            current = current.nextNode;
        }
        assert lastOccurrencePrev != null;
        lastOccurrencePrev.nextNode = lastOccurrenceCurrent.nextNode;
        System.out.println(head);
    }
}

