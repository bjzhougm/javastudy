package leecode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. 用队列实现栈
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 * https://leetcode-cn.com/problems/implement-stack-using-queues/solution/liang-ge-dui-lie-shi-xian-zhan-by-georgedc/
 */
public class QueueToStack {

    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    /** Initialize your data structure here. */
    public QueueToStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue1.add(x);
    }

    /**
     * 执行pop操作的时候 把所有的后入的元素poll出 放进queue2中
     * 这时候queue1 poll的时候就是pop的栈顶的数据
     * 把队列2的数据放回队列1
     * Removes the element on top of the stack and returns that element. */

    public int pop() {
        while (queue1.size() > 1)
            queue2.add(queue1.poll());
        int ans = queue1.poll();
        while (queue2.size() > 0)
            queue1.add(queue2.poll());
        return ans;
    }

    /** 取栈顶元素一样 从队列1中放到队列2中，然后再放回去 Get the top element. */
    public int top() {
        while (queue1.size() > 1)
            queue2.add(queue1.poll());
        int ans = queue1.peek();
        queue2.add(queue1.poll());
        while (queue2.size() > 0)
            queue1.add(queue2.poll());
        return ans;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty();
    }
}
