package leecode;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/solution/dong-hua-yan-shi-206-fan-zhuan-lian-biao-by-user74/
 * https://mp.weixin.qq.com/s/-t3zGkByxvNUiEIVxPnydA 绝绝子
 */
public class ReverseList {
    public ListNode reverseList(ListNode head){
        //判断边界
        if(head == null || head.next == null) return head;
        //从下一个节点开始递归
        ListNode reverse = reverseList(head.next);
        //设置下一个节点的 next 为当前节点
        head.next.next = head;
        //把当前节点的 next 赋值为 null，避免循环引用
        head.next = null;
        return  reverse;
    }


    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
