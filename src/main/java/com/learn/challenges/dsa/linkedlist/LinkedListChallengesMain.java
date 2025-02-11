package com.learn.challenges.dsa.linkedlist;


public class LinkedListChallengesMain {
    public static void main(String[] args) {
        Node myLinkedList = new Node(1);
        Node current = myLinkedList;
        for (int i = 2; i < 6; i ++) {
            current.nextNode = new Node(i);
            current = current.nextNode;
        }
        System.out.println(myLinkedList);
         findMiddleOfLinkedList(myLinkedList);
    }

    static void findMiddleOfLinkedList(Node head) {
        Node slowPointerNode = head;
        Node fastPointerNode = head;
        while (fastPointerNode.nextNode != null) {
            slowPointerNode = slowPointerNode.nextNode;
            fastPointerNode = fastPointerNode.nextNode.nextNode;
        }
        System.out.println(slowPointerNode.val);
    }

}












