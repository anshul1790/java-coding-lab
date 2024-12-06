package com.learn.challenges.linkedlist;

public class NodeDeletionLinkedList {


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
        Node head = createLinkedList();
        //deleteLinkedListByItem(head, 1);
        //deleteLinkedListByItem(head, 5);
        deleteLinkedListAtPos(head, 10);
    }

    /*  Input: head: 1 -> 2 -> 3 -> 4 -> NULL, key = 2
        Output: 1 -> 3 -> 4 -> NULL
    */
    static void deleteLinkedListByItem(Node head, int item) {
        Node current = head;
        Node prev = null;
        if (current.val == item) {
            head = current.nextNode;
        }
        while (current != null && current.val != item) {
            prev = current;
            current = current.nextNode;
        }
        if (current == null || prev == null) return;
        prev.nextNode = current.nextNode;
        System.out.println(head);
    }

    static void deleteLinkedListAtPos(Node head, int pos) {
        if (head == null) return;
        Node current = head;
        if (pos == 1) {
            head = current.nextNode;
            System.out.println(head);
            return;
        }
        Node prev = null; int pointer = 1;
        while (current != null && pointer < pos) {
            prev = current;
            current = current.nextNode;
            pointer++;
        }
        if (current == null) return;
        assert prev != null;
        prev.nextNode = current.nextNode;
        System.out.println(head);
    }
}
