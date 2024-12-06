package com.learn.challenges.linkedlist;

public class DeleteMiddleOfLinkedList {

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
        Node ll = createLinkedList();
        deleteMiddleLl(ll);
    }

    /*Input: LinkedList: 1->2->3->4->5
    Output: 1->2->4->5*/

    static void deleteMiddleLl(Node head) {
        Node slowPointer = head;
        Node slowPointerPrev = null;
        Node fastPointer = head;
        while (fastPointer.nextNode != null) {
            slowPointerPrev = slowPointer;
            slowPointer = slowPointer.nextNode;
            fastPointer = fastPointer.nextNode.nextNode;
        }
        System.out.println("Middle Node " + slowPointer.val);
        assert slowPointerPrev != null;
        slowPointerPrev.nextNode = slowPointer.nextNode;
        System.out.println(head);
    }

}
