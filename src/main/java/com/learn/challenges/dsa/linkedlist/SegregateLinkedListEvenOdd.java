package com.learn.challenges.dsa.linkedlist;

public class SegregateLinkedListEvenOdd {

    static Node createLinkedList() {
        Node head = new Node(1);
        Node current = head;
        for(int i = 2; i <= 5; i ++) {
            current.nextNode = new Node(i);
            if (i == 4) {
                current = current.nextNode;
                current.nextNode = new Node(4);
            }
            current = current.nextNode;
        }
        return head;
    }

    public static void main(String[] args) {
        Node ll = createLinkedList();
        segregateEvenOddLl(ll);
    }

    static void segregateEvenOddLl(Node head) {
        Node current = head;
        Node evenHead = null, evenTail = null;
        Node oddHead = null, oddTail = null;
        while (current != null) {
            Node nextNode = current.nextNode;
            if (current.val % 2 == 0) {
                if (evenHead == null) {
                    evenHead = current;
                    evenTail = evenHead;
                } else {
                    evenTail.nextNode = current;
                    evenTail = evenTail.nextNode;
                }
            } else {
                if (oddHead == null) {
                    oddHead = current;
                    oddTail = oddHead;
                } else {
                    oddTail.nextNode = current;
                    oddTail = oddTail.nextNode;
                }
            }
            current.nextNode = null;
            current = nextNode;
        }
        System.out.println(evenHead);
        System.out.println(oddHead);
    }
}