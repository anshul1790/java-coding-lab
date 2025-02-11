package com.learn.challenges.revise;

public class CreateQueueUsingArray {

    static class CustomQueue<V> {

        int maxSize;
        int size;
        int front;
        int back;
        V[] items;

        public CustomQueue(int maxSize) {
            this.maxSize = maxSize;
            this.front = 0;
            this.back = -1;
            this.size = 0;
            this.items = (V[]) new Object[maxSize];
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public boolean isFull() {
            return this.size == this.maxSize;
        }

        public V enqueue(V item) {
            if (isFull()) {
                System.out.println("Queue already full");
                return null;
            }
            back = (back + 1) % maxSize;
            items[back] = item;
            size ++;
            return items[back];
        }

        public V deque() {
            if (isEmpty()) {
                System.out.println("Queue already empty");
                return null;
            }
            V item = items[front];
            items[front] = null;
            front = (front + 1) % maxSize;
            size--;
            return item;
        }

    }


    public static void main(String[] args) {
        CustomQueue<Integer> myQueue = new CustomQueue<>(5);
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        myQueue.enqueue(3);
        System.out.println(myQueue.deque());
        System.out.println("Size of queue is " + myQueue.size);
        System.out.println(myQueue.deque());
        System.out.println(myQueue.deque());

    }

}
