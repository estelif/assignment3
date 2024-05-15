import java.util.*;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node current, K key, V val) {
        if (current == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(current.key);
        if (cmp < 0)
            current.left = put(current.left, key, val);
        else if (cmp > 0)
            current.right = put(current.right, key, val);
        else
            current.val = val;
        return current;
    }

    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0)
                current = current.left;
            else if (cmp > 0)
                current = current.right;
            else
                return current.val;
        }
        return null;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node current, K key) {
        if (current == null) return null;

        int cmp = key.compareTo(current.key);
        if (cmp < 0)
            current.left = delete(current.left, key);
        else if (cmp > 0)
            current.right = delete(current.right, key);
        else {
            if (current.right == null) return current.left;
            if (current.left == null) return current.right;
            Node t = current;
            current = min(t.right);
            current.right = deleteMin(t.right);
            current.left = t.left;
        }
        return current;
    }

    private Node min(Node current) {
        while (current.left != null)
            current = current.left;
        return current;
    }

    private Node deleteMin(Node current) {
        if (current.left == null) return current.right;
        current.left = deleteMin(current.left);
        return current;
    }

    public Iterable<Node> iterator() {
        List<Node> nodes = new ArrayList<>();
        inorder(root, nodes);
        return nodes;
    }

    private void inorder(Node current, List<Node> nodes) {
        if (current == null) return;
        inorder(current.left, nodes);
        nodes.add(current);
        inorder(current.right, nodes);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();
        tree.put(5, "Five");
        tree.put(3, "Three");
        tree.put(15, "Fifteen");
        tree.put(12, "Twelve");
        tree.put(7, "Seven");
        tree.put(2, "Two");
        tree.put(8, "Eight");
        tree.put(10, "Ten");


        for (var elem : tree.iterator()) {
            System.out.println("key is " + elem.key + " and value is " + elem.val);
        }
    }
}
