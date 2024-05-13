package HashTable;

import java.util.Random;

class MyTestingClass {
    private int value;

    public MyTestingClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return value % 10;
    }
}

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(rand.nextInt(1000));
            table.put(key, "value" + i);
        }
        int[] bucketSizes = new int[10];
        for (int i = 0; i < table.chainArray.length; i++) {
            int count = 0;
            MyHashTable.HashNode<MyTestingClass, String> current = table.chainArray[i];
            while (current != null) {
                count++;
                current = current.next;
            }
            bucketSizes[i] = count;
        }

        System.out.println("number of elements:");
        for (int i = 0; i < bucketSizes.length; i++) {
            System.out.println("bucket " + i + ": " + bucketSizes[i]);
        }
    }
}
