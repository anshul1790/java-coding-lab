package com.learn.challenges.revise;

public class CreateStackUsingArray {

    static class CustomStack<V> {

        private final int size;
        private int top;
        private final V[] items;

        public CustomStack(int size) {
            this.size = size;
            this.top = -1;
            this.items = (V[]) new Object[size];
        }

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return this.top == -1;
        }

        public boolean isFull() {
            return this.top == size;
        }

        public V push(V item) {
            if (!this.isFull()) {
                items[++top] = item;
            } else {
                System.out.println("Seems like stack is already full");
            }
            return items[top];
        }

        public V pop() {
            if (this.isEmpty()) {
                return null;
            }
            return items[top--];
        }
    }

    public static void main(String[] args) {
        CustomStack<String> myStack = new CustomStack<>(5);
        myStack.push("Ram");
        myStack.push("Raj");
        myStack.push("Kevin");
        System.out.println(myStack.pop());
    }

}
