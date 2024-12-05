package com.learn.challenges.leet;

import java.util.ArrayList;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode(){}
    ListNode(int val) {this.val = val;}
    ListNode(ListNode next, int val) {this.next = next; this.val = val;}

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

public class AddTwoNumberLinkedList15Nov {

    public static void main(String[] args) {
        ListNode l1 = createLinkedList(new int[]{2, 4, 3});
        ListNode l2 = createLinkedList(new int[]{5, 6, 4});
        ListNode finalNode = twoSumLinkedList(l1, l2);
        List<Integer> resultArray = convertLinkedListToArray(finalNode);
        System.out.println(resultArray); // Output: [7, 0, 8]
    }

    public static List<Integer> convertLinkedListToArray(ListNode head) {
        List<Integer> result = new ArrayList<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            result.add(currentNode.val);
            currentNode = currentNode.next;
        }
        return result;
    }

    static ListNode twoSumLinkedList(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode currentNode = head;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            int val = sum % 10;
            currentNode.next = new ListNode(val);
            currentNode = currentNode.next;
            if (l1 != null) l1 = l1.next; if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) {
            currentNode.next = new ListNode(carry);
        }
        return head.next;
    }

    static ListNode createLinkedList(int[] vals) {
        // Create the head of the list
        ListNode head = new ListNode(vals[0]);
        ListNode currentNode = head;
        int index = 1;
        while (index < vals.length) {
            currentNode.next = new ListNode(vals[index]);
            currentNode = currentNode.next;
            index ++;
        }
        System.out.println(head);
        return head;
    }

    /*static void createLinkedList2() {
        ListNode tail = new ListNode(1);
        ListNode second = new ListNode(tail, 2);
        System.out.println(second);
    }

    static void createLinkedList() {
        ListNode head = new ListNode(0);
        ListNode first = new ListNode(1);
        head.next = first;
        ListNode second = new ListNode( 2);
        first.next = second;
        ListNode third = new ListNode( 3);
        second.next = third;
        System.out.println("head " + head);
        ListNode currentNode = head;

        while (currentNode != null) {
            System.out.println(currentNode);
            currentNode = currentNode.next;
        }
    }
*/
}
