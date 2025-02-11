package com.learn.challenges.dsa.stacknqueue;

import java.util.Arrays;

public class CustomArrayQueue<V> {
    private final int maxSize;
    private V[] array;
    private int front;
    private int back;
    private int currentSize;

    @SuppressWarnings("unchecked")
    public CustomArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front = 0;
        this.back = -1;
        this.array = (V[]) new Object[maxSize];
    }

    public boolean isEmpty() {
        return this.currentSize == 0;
    }

    public boolean isFull() {
        return this.currentSize == maxSize;
    }

    public boolean enqueue(V item) {
        if (isFull()) {
            System.out.println("Throw checked exception: queue is already full");
            return false;
        }
        back = (back + 1) % maxSize;
        array[back] = item;
        currentSize ++;
        System.out.println("Item added to queue");
        return true;
    }

    public V dequeue() {
        if (isEmpty()) {
            System.out.println("Throw checked exception: queue is already empty");
            return null;
        }
        V item = array[front];
        array[front] = null;
        front = (front + 1) % maxSize;
        currentSize --;
        return item;
    }

    @Override
    public String toString() {
        return "CustomArrayQueue{" +
                "array=" + Arrays.toString(array) +
                ", front=" + front +
                ", back=" + back +
                ", currentSize=" + currentSize +
                '}';
    }

    public static void main(String[] args) {
        CustomArrayQueue<String> myQueue = new CustomArrayQueue<>(5);
        myQueue.enqueue("Anshul");
        myQueue.enqueue("Sahil");
        myQueue.enqueue("Radhika");
        System.out.println(myQueue);
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue);
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue);
        myQueue.enqueue("Anshul");
        System.out.println(myQueue);
    }

}
