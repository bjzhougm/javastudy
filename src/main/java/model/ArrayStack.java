package model;

public class ArrayStack {
    static final int CAPACITY = 1000;
    int top;
    int[] stack;

    public static void main(String[] args) {
        ArrayStack s = new ArrayStack();
        s.push(10);
        s.push(20);
        s.push(30);
        System.out.println(s.pop() + " Popped from stack");
    }

    public ArrayStack() {
        top = -1;
        stack = new int[CAPACITY];
    }

    public void push(int val) {
        if (top >= (CAPACITY - 1)) {
            System.out.println("ArrayStack Overflow.");
            return;
        }
        stack[++top] = val;
    }

    public int pop() {
        if (top < 0) {
            System.out.println("ArrayStack Underflow.");
            return 0;
        }
        int element = stack[top--];
        return element;
    }

    public int peek() {
        if (top < 0) {
            System.out.println("ArrayStack Underflow");
            return 0;
        }
        int element = stack[top];
        return element;
    }

    public boolean isEmpty() {
        return top < 0;
    }

}
