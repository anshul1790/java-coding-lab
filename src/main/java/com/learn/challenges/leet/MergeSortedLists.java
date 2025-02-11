package com.learn.challenges.leet;

public class MergeSortedLists {

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    static ListNode createLinkedList(int[] n) {
        ListNode n1 = new ListNode(n[0]);
        ListNode current = n1;
        for (int i = 1; i < n.length; i++) {
            current.next = new ListNode(n[i]);
            current = current.next;
        }
        return n1;
    }

    public static void main(String[] args) {
        ListNode n1 = createLinkedList(new int[]{1, 2, 4});
        ListNode n2 = createLinkedList(new int[]{1, 3, 4});
        mergeTwoLists(n1, n2);
    }

    static void mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode mergedListNode = new ListNode(-1);
        ListNode current = mergedListNode;
        while (list1 != null || list2 != null) {
            int x = list1 != null ? list1.val : Integer.MAX_VALUE;
            int y = list2 != null ? list2.val : Integer.MAX_VALUE;
            if (x <= y) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        System.out.println(mergedListNode);
    }
}
