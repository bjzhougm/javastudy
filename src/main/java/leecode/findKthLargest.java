package leecode;

import java.util.PriorityQueue;

public class findKthLargest {
    public int findKthLargestMin(int[] nums, int k) {
        int len = nums.length;
        // 使用一个含有 len 个元素的最小堆，默认是最小堆，可以不写 lambda 表达式：(a, b) -> a - b
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(len, (a, b) -> a - b);
        for (int i = 0; i < len; i++) {
            minHeap.add(nums[i]);
        }
        for (int i = 0; i < len - k; i++) {
            minHeap.poll();
        }
        return minHeap.peek();
    }
}
