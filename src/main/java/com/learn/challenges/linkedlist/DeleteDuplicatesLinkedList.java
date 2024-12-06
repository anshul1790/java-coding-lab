package com.learn.challenges.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class DeleteDuplicatesLinkedList {

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
        current.nextNode = new Node(7);
        current = current.nextNode;
        return head;
    }

    public static void main(String[] args) {
        Node head = createLinkedList();
        System.out.println("Original Linked list is " + head);
        deleteDuplicates(head);
    }

    /*LinkedList: 2->2->4->5
    Output: 2 -> 4 -> 5*/

    static void deleteDuplicates(Node head) {
        Set<Integer> duplicates = new HashSet<>();
        Node current = head;
        Node prev = null;
        while (current != null) {
            if (!duplicates.contains(current.val)) {
                duplicates.add(current.val);
                prev = current;
            } else {
                prev.nextNode = current.nextNode;
            }
            current = current.nextNode;
        }
        System.out.println(head);
    }
}
