package HashTable;

public class MyHashTable<K,V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int num; //capacity
    private int size;

    public MyHashTable() {
        this(10);
    }
    public int size(){
        return size;
    }

    public MyHashTable(int capacity) {
    this.num=capacity;
    this.chainArray=new HashNode[num];
    this.size=0;
    }

    private int hash(K key, V value){
        return Math.abs(key.hashCode()) % num;
    }
    public void put(K key, V value){
        if(key==null || value == null)
            throw new IllegalArgumentException("null");
        int bucketIndex = getBucketIndex(key);
        HashNode<K,V> head = chainArray[bucketIndex];
        while(head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head= chainArray[bucketIndex];
        HashNode node = new HashNode(key, value); // key , value -> null
        node.next = head;
        chainArray[bucketIndex] = node;
    }
    private int getBucketIndex(K key){
        return Math.abs(key.hashCode()) % num;
    }
    public V get (K key){
        if (key == null) {
            throw new IllegalArgumentException("key null");
        }
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = chainArray[bucketIndex];
        HashNode<K, V> previous = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (previous != null) {
                    previous.next = head.next;
                } else {
                    chainArray[bucketIndex] = head.next;
                }
                size--;
                return head.value;
            }
            previous = head;
            head = head.next;
        }

        return null;
    }
    public V remove(K key){
        if (key == null) {
            throw new IllegalArgumentException("key null");
        }
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = chainArray[bucketIndex];
        HashNode<K, V> previous = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (previous != null) {
                    previous.next = head.next;
                } else {
                    chainArray[bucketIndex] = head.next;
                }
                size--;
                return head.value;
            }
            previous = head;
            head = head.next;
        }
        return null;
    }

    public boolean contains (V value){
        for (HashNode<K, V> node : chainArray) {
            HashNode<K, V> currentNode = node;
            while (currentNode != null) {
                if (currentNode.value.equals(value)) {
                    return true;
                }
                currentNode = currentNode.next;
            }
        }
        return false;
    }
    public K getKey(V value){
        for (HashNode<K, V> node : chainArray) {
            HashNode<K, V> currentNode = node;
            while (currentNode != null) {
                if (currentNode.value.equals(value)) {
                    return currentNode.key;
                }
                currentNode = currentNode.next;
            }
        }
        return null;
    }

    public static void main(String[] args){
        MyHashTable table = new MyHashTable(10);
        table.put(1, "Khadisha");
        table.put(2, "Max");
        table.put(4, "Zara");
        System.out.println(table.size());
        System.out.println(table.remove(2));
        System.out.println(table.remove(1));
        System.out.println(table.size());
    }
}
