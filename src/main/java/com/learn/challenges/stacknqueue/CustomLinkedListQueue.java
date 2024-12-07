package com.learn.challenges.stacknqueue;

public class CustomLinkedListQueue<V> {

    static class Node<V> {
        V data;
        Node<V> next;
        Node(V data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    Node<V> front;
    Node<V> back;
    private int currentSize;

    public CustomLinkedListQueue() {
        this.front = null;
        this.back = null;
    }

    public boolean enqueue(V item) {
        Node<V> newNode = new Node<>(item);
        if (back != null) {
            back.next = newNode;
        }
        back = newNode;
        if (front == null) {
            front = back;
        }
        currentSize ++;
        return true;
    }

    public V dequeue() {
        if (front == null) {
            System.out.println("Queue is already empty");
            return null;
        }
        V item = front.data;
        front = front.next;
        if (front == null) { back = null; }
        currentSize --;
        return item;
    }

    @Override
    public String toString() {
        return "CustomLinkedListQueue{" +
                "front=" + front +
                ", back=" + back +
                ", currentSize=" + currentSize +
                '}';
    }

    public static void main(String[] args) {
        CustomLinkedListQueue<String> myQueue = new CustomLinkedListQueue<>();
        myQueue.enqueue("Anshul");
        myQueue.enqueue("Sahil");
        System.out.println(myQueue);
        myQueue.dequeue();
        System.out.println(myQueue);
    }

}
