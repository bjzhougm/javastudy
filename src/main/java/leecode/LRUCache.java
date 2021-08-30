package leecode;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/lru-cache/solution/java-hashmapshuang-xiang-lian-biao-by-da-rdjo/
 */

public class LRUCache {

    HashMap<Integer, Node> map;

    DoubleLinkedList cache;

    int cap;

    public LRUCache(int capacity){
        map   = new HashMap<>();
        cache = new DoubleLinkedList();
        cap   = capacity;
    }

    public void put(int key, int val){
        Node newNode = new Node(key, val);

        if(map.containsKey(key)){
            cache.delete(map.get(key));
        }else{
            if(map.size() == cap){
                int k = cache.deleteLast();
                map.remove(k);
            }

        }
        cache.addFirst(newNode);
        map.put(key, newNode);
    }

    public int get(int key){
        if(!map.containsKey(key))   return -1;

        int val = map.get(key).val;
        put(key, val);

        return val;
    }


    class Node{
        public int key;
        public int val;
        public Node prev;
        public Node next;

        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    class DoubleLinkedList{
        Node head;
        Node tail;

        public DoubleLinkedList(){
            head = new Node(0,0);
            tail = new Node(0,0);

            head.next = tail;
            tail.prev = head;
        }

        public void addFirst(Node node){

            node.next   = head.next;
            node.prev   = head;

            head.next.prev = node;
            head.next      = node;
        }

        public int delete(Node n){
            int key = n.key;
            n.next.prev = n.prev;
            n.prev.next = n.next;

            return key;
        }

        public int deleteLast(){
            if(head.next == tail)   return -1;

            return delete(tail.prev);
        }
    }
}