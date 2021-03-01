package leecode;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/solution/dong-hua-yan-shi-206-fan-zhuan-lian-biao-by-user74/
 */
public class ReverseList {

    static class  ListNode{
        int value;
        ListNode next;
        public ListNode(int value) {
            this.value = value;
        }
    }

    public ListNode reverseList(ListNode head) {

        ListNode cur=head;
        ListNode pro=null;
        while(cur!=null)
        {
            ListNode Next=cur.next;
            cur.next=pro;
            pro=cur;
            cur=Next;
        }
        return pro;
    }

}
