package com.learn.challenges.linkedlist;

public class NthNodeTraversalFromEnd {

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
        traversalFromEnd(linkedList, 3);
    }

    /*Input: 1 -> 2 -> 3 -> 4, N = 3
    Output: 2
    Explanation: Node 2 is the third node from the end of the linked list.*/
    static void traversalFromEnd(Node head, int n) {
        Node current = head;
        Node referencePointer = head;
        while (n > 1) {
            referencePointer = referencePointer.nextNode;
            n--;
        }
        while(referencePointer.nextNode != null) {
            referencePointer = referencePointer.nextNode;
            current = current.nextNode;
        }
        System.out.println(current.val);
    }
}
