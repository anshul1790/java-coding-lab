package com.learn.challenges.linkedlist;

public class SumLinkedList {

    static Node createLinkedList(int[] arr) {
        Node newNode = new Node(arr[0]);
        Node current = newNode;
        for (int i = 1; i < arr.length; i++) {
            current.nextNode = new Node(arr[i]);
            current = current.nextNode;
        }
        return newNode;
    }

    public static void main(String[] args) {
        Node l1 = createLinkedList(new int[]{3, 4, 2});
        System.out.println(l1);
        Node l2 = createLinkedList(new int[]{4, 6, 5});
        System.out.println(l2);
        sumLinkedList(l1, l2);
    }

    /*Input: l1 = [2,4,3], l2 = [5,6,4]
    Output: [7,0,8]
    Explanation: 342 + 465 = 807.*/
    static void sumLinkedList(Node l1, Node l2) {
        Node newNode = new Node(0);
        Node current = newNode;
        int carry = 0;
        int finalSum = 0;
        while (l1 != null || l2 != null) {
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            int sum = carry + a + b;
            carry = sum / 10;
            int remainder = sum % 10;
            finalSum = finalSum * 10 + remainder;
            current.nextNode = new Node(remainder);
            current = current.nextNode;
            l1 = l1.nextNode;
            l2 = l2.nextNode;
        }
        newNode = newNode.nextNode;
        System.out.println(newNode);
    }
}
