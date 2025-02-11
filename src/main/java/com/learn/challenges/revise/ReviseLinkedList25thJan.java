package com.learn.challenges.revise;

import java.util.HashSet;
import java.util.Set;

public class ReviseLinkedList25thJan {

    static class Node {
        int val;
        Node nextNode;
        public Node(int val) {
            this.val = val;
            this.nextNode = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", nextNode=" + nextNode +
                    '}';
        }
    }

    public static void main(String[] args) {
        Node l1 = createLinkedList(new int[]{1, 3, 3, 8, 3, 9, 8});
        //Node l2 = createLinkedList(new int[]{2, 6, 4});
        //addTwoNumbers(l1, l2);
        System.out.println(l1);
        //deleteDuplicates(l1);
        //deleteLastOccurrence(l1, 3);
        //deleteFromLinkedList(l1, 3);
        reverseLinkedList(l1);
    }

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

    static void deleteFromLinkedList(Node head, int val) {
        Node current = head;
        Node prev = null;
        if (current.val == val) {
            head = current.nextNode;
            System.out.println(head);
            return;
        }
        while (current != null) {
            if (current.val == val) {
                prev.nextNode = current.nextNode;
                break;
            }
            prev = current;
            current = current.nextNode;
        }
        System.out.println(head);
    }

    static void deleteLastOccurrence(Node head, int key) {
        Node current = head;
        Node prev = null;
        Node lastCurrent = current;
        Node lastPrev = prev;
        while (current != null) {
            if (current.val == key) {
                lastPrev = prev;
                lastCurrent = current;
            }
            prev = current;
            current = current.nextNode;
        }
        assert lastPrev != null;
        lastPrev.nextNode = lastCurrent.nextNode;
        System.out.println(head);
    }

    /*

     */
    static void deleteDuplicates(Node head) {
        Node current = head;
        Node prev = null;
        Set<Integer> uniques = new HashSet<>();
        while (current != null) {
            if (!uniques.contains(current.val)) {
                uniques.add(current.val);
                prev = current;
            } else {
                prev.nextNode = current.nextNode;
            }
            current = current.nextNode;
        }
        System.out.println(head);
    }


    /*Input: l1 = [9,8,3], l2 = [2,6,4]
    Output: [7,0,8]
    Explanation: 342 + 465 = 807.*/
    static void addTwoNumbers(Node l1, Node l2) {
        Node sum = new Node(0);
        Node current = sum;
        int carry = 0;
        int finalSum = 0;
        while (l1 != null || l2 != null) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int currentSum = carry + l1Val + l2Val;
            carry = currentSum / 10;
            int remainder = currentSum % 10;
            current.nextNode = new Node(remainder);
            current = current.nextNode;
            finalSum = finalSum * 10 + remainder;
            assert l1 != null;
            assert l2 != null;
            l1 = l1.nextNode;
            l2 = l2.nextNode;
        }
        System.out.println(sum);
        if (carry > 0) {
            finalSum = finalSum * 10 + carry;
        }
        System.out.println(finalSum);
        int reverseFinalSum = 0;
        while (finalSum > 0) {
            reverseFinalSum = reverseFinalSum * 10 + (finalSum % 10);
            finalSum = finalSum / 10;
        }
        System.out.println(reverseFinalSum);
    }

    static Node createLinkedList(int[] num) {
        Node node = new Node(0);
        Node current = node;
        for (int i = 0; i < num.length; i++) {
            current.nextNode = new Node(num[i]);
            current = current.nextNode;
        }
        node = node.nextNode;
        return node;
    }

}
