package leecode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 双栈实现队列
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/solution/shi-yong-liang-ge-zhan-yi-ge-zhuan-men-ru-dui-yi-g/
 */
public class StackToQueue {
    //两个栈，一个出栈，一个入栈
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    /**
     * Initialize your data structure here.
     */
    public StackToQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void appendTail(int x) {
        // 在任何时候都可以向 pushStack 推入元素
        inStack.push(x);
    }


    public int deleteHead() {
        // 从 outStack 取出元素
        if (!outStack.isEmpty()) {
            return outStack.pop();
        }
        // 走到这里是因为 outStack 为空，此时需要将 inStack 里的所有元素依次放入 outStack
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
        return outStack.isEmpty() ? -1 : outStack.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        // 从 outStack 取出元素
        if (!outStack.isEmpty()) {
            return outStack.peek();
        }
        // 走到这里是因为 outStack 为空，此时需要将 inStack 里的所有元素依次放入 outStack
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
        return  outStack.isEmpty() ? -1 : outStack.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        // 两个栈都为空，才说明队列为空
        return inStack.isEmpty() && outStack.isEmpty();
    }
}
