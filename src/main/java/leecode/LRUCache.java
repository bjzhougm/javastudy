package leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lru-cache/solution/java-hashmapshuang-xiang-lian-biao-by-da-rdjo/
 */

class LRUCache {
    class Node {
        public int key, val;
        public Node next,prev;
        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }}

    Map<Integer, Node> map = new HashMap<>();
    int cap = 0;    //容量
    Node head = null, last = null;  //双向链表头尾指针


    public LRUCache(int capacity) {     //初始化
        cap = capacity;
        head = new Node(-1, -1);
        last = new Node(-1, -1);
        head.next = last; head.prev = last;
        last.next = head; last.prev = head;
    }

    public int get(int key) {
        Node n;
        if((n = map.get(key)) == null)  //元素不存在
            return -1;
        put(n.key, n.val);  //元素存在，则用put函数将其从链表的原位置删除，移到表头
        return n.val;       //返回节点的值


    }

    public void put(int key, int value) {
        Node del;   //指向被删除的元素
        if(map.get(key)!=null || map.size() >= cap){    //容量已满或者已存在，都应该进行删除
            del = (map.get(key)!=null ? map.get(key) : last.prev);

            Node p = del.prev;  //从链表中删除该元素
            del.next.prev = p;
            p.next = del.next;

            map.remove(del.key);    //删除哈希表到该元素的映射
        }

        Node n = new Node(key, value);

        n.next = head.next;     //插入元素到表头
        n.prev = head;
        head.next.prev = n;
        head.next = n;

        map.put(key, n);    //哈希表中新增映射
    }
}