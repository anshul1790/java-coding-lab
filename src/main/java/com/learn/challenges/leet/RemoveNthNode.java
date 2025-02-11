package com.learn.challenges.leet;

public class RemoveNthNode {

    static class MyListNode {
        int val;
        MyListNode next;
        public MyListNode(int val) {
            this.val = val;
            this.next = null;
        }

        @Override
        public String toString() {
            return "MyListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    static MyListNode createListNode (int[] n) {
        MyListNode myListNode = new MyListNode(0);
        MyListNode current = myListNode;
        for (int i = 0; i < n.length; i ++) {
            current.next = new MyListNode(n[i]);
            current = current.next;
        }
        myListNode = myListNode.next;
        return myListNode;
    }

    static MyListNode removeNthNodeFromLast(MyListNode head, int k) {
        MyListNode fastPointer = head;
        MyListNode slowPointer = head;

        if (fastPointer == null || fastPointer.next == null)
            return null;

        int count = 1;
        while (count <= k) {
            if (fastPointer == null) {
                return head;
            }
            fastPointer = fastPointer.next;
            count ++;
        }

        while (fastPointer != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next;
        }

        assert slowPointer != null;
        slowPointer.next = slowPointer.next.next;
        return head;
    }

    public static void main(String[] args) {
        MyListNode myNode = createListNode(new int[]{1, 2});
        System.out.println(myNode);
        MyListNode node = removeNthNodeFromLast(myNode, 2);
        System.out.println(node);
    }

}
