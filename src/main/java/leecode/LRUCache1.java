package leecode;

import java.util.HashMap;

public class LRUCache1 {

    HashMap<Integer,Node> map;
    DoubleLinkedList cache;
    int cap;
    public LRUCache1(int capacity){
        map = new HashMap<>();
        cache = new DoubleLinkedList();
        cap = capacity;
    }

    public void put(int key,int val){
        Node node = new Node(key,val);
        if(map.containsKey(key)){
            cache.delete(map.get(key));
            map.put(key,node);
            cache.addFirst(node);
        }else {
            if (map.size()==cap){
                int k = cache.deleteLast();
                map.remove(key);
                cache.addFirst(node);
                map.put(key,node);
            }
        }
    }

    public int get(int key){
        if (!map.containsKey(key)) return -1;
        int val = map.get(key).val;
        put(key,val);
        return val;
    }


    class Node{
        public int key;
        public int val;
        public Node prev;
        public Node next;

        public Node(int key,int val){
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
            node.next = head.next;
            node.prev = head.prev;
            head.next=node;
            head.next.prev=node;
        }

        public int delete(Node node){
            int key = node.key;
            node.next.prev=node.prev;
            node.prev.next=node.next;
            return key;
        }

        public int deleteLast(){
            if(head.next == tail) return -1;
            return delete(tail.prev);
        }

    }
}
