//-----------------------------------------------------
// Title: CMPE343 HW1
// Author: Ahmet Cemal Sert
// Section: 1
// Assignment: HW1 Part1
// Description: This class defines a Sequential Search Symbol Table implementation
//-----------------------------------------------------

package Part1;

import java.util.*;

public class SequentialSearchST<Key, Value>{
    private int N ;          // number of key-value pairs
    private Node first;      // the linked list of key-value pairs

    // Getter and Setters
    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    // helper linked list data type
   public class Node  {
        private Key key;
        private Value val;
        private Node next;
        public  int count=0;

        public Node(Key key, Value val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
            count++;
        }

        // Getter and Setters

        public Key getKey() {
            return key;
        }

        public void setKey(Key key) {
            this.key = key;
        }

        public Value getVal() {
            return val;
        }

        public void setVal(Value val) {
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    // Empty constructor
    public SequentialSearchST() {

    }


    // Returns the number of key-value pairs of the table
    public int size() {
        return N;
    }

    // Checks if the Symbol Table is empty
    public boolean isEmpty() {
        return size() == 0;
    }


    // Checks if the symbol table contains the key given in the parameter
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // Returns the value associated with the given key if symbol table contains the key
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    // Inserts the key-value pair into the symbol table, overwriting the old value
    // Ovverides the value of it if the key is already on the table
    public void put(Key key, Value val) {
        if (val == null) { // if value is null deletes the key
            delete(key);
            return;
        }

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                x.count++; // if the same value exists overrides the old one and increase count by 1
                return;
            }
        }
        first = new Node(key, val, first);
        N++;

    }

    // removes the key and associated value from the symbol table
    public void delete(Key key) {
        first = delete(first, key);
    }

    // delete key in linked list beginning at Node x
    // warning: function call stack too large if table is large
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            N--;
            x.count--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    // Returns all keys in the symbol table as an iterable
    public Iterable<Key> keys()  {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next) queue.enqueue(x.key);
        return queue;
    }

    // Returns all node counts in the symbol table as an iterable
    public Iterable<Key> counts(){
        Queue<Integer> queue = new Queue<Integer>();
        for (Node x = first; x != null; x = x.next) queue.enqueue(x.count);
        return (Iterable<Key>) queue;
    }

    // Returns all keys with how many times they occur
    public Iterable keysAndCounts(){
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next) queue.enqueue((Key)(x.key+ " : " +x.count));
        return  queue;
    }


    // checks if the given counts match with the nodes .If they do adds them to queue
    public Iterable popularKeysAndCounts(){
        Queue<Key> queue = new Queue<Key>();
        int[] array = maxCount();

        for (Node x = first; x != null; x = x.next)
            if(array[0] == x.count) queue.enqueue((Key) (x.key+":"+x.count));
            else if(array[1] == x.count) queue.enqueue((Key) (x.key+":"+x.count));
            else if(array[2] == x.count) queue.enqueue((Key) (x.key+":"+x.count));
            else if(array[3] == x.count) queue.enqueue((Key) (x.key+":"+x.count));
            else if(array[4] == x.count) queue.enqueue((Key) (x.key+":"+x.count));
            else if(array[5] == x.count) queue.enqueue((Key) (x.key+":"+x.count));
            else if(array[6] == x.count) queue.enqueue((Key) (x.key+":"+x.count));
            else if(array[7] == x.count) queue.enqueue((Key) (x.key+":"+x.count));
            else if(array[8] == x.count) queue.enqueue((Key) (x.key+":"+x.count));
            else if(array[9] == x.count) queue.enqueue((Key) (x.key+":"+x.count));

        return  queue;
    }



    // finds maximum counts sorts them and returns them as an array
    public int [] maxCount(){
        int max = 1, max2 =0, max3 = 0, max4 = 0, max5 = 0, max6 =0, max7 = 0, max8 = 0, max9 = 0, max10 =0;


        int topTen[] = new int[10];
        for (Node x = first; x != null; x = x.next)
            if(x.count>max) max = x.count;

        for (Node x = first; x != null; x = x.next)
            if(x.count<max && x.count >= max2) max2 = x.count;

        for (Node x = first; x != null; x = x.next)
            if(x.count<max2 && x.count >= max3) max3 = x.count;

        for (Node x = first; x != null; x = x.next)
            if(x.count<max3 && x.count >= max4) max4 = x.count;

        for (Node x = first; x != null; x = x.next)
            if(x.count<max4 && x.count >= max5) max5 = x.count;

        for (Node x = first; x != null; x = x.next)
            if(x.count<max5 && x.count >= max6) max6 = x.count;

        for (Node x = first; x != null; x = x.next)
            if(x.count<max6 && x.count >= max7) max7 = x.count;

        for (Node x = first; x != null; x = x.next)
            if(x.count<max7 && x.count >= max8) max8 = x.count;

        for (Node x = first; x != null; x = x.next)
            if(x.count<max8 && x.count >= max9) max9 = x.count;

        for (Node x = first; x != null; x = x.next)
            if(x.count<max9 && x.count >= max10) max10 = x.count;


        topTen[0] = max;
        topTen[1] = max2;
        topTen[2] = max3;
        topTen[3] = max4;
        topTen[4] = max5;
        topTen[5] = max6;
        topTen[6] = max7;
        topTen[7] = max8;
        topTen[8] = max9;
        topTen[9] = max10;

        Arrays.sort(topTen);
        return topTen;
    }



    // class tester
    public static void main(String [] args){

        SequentialSearchST sequentialSearchST = new SequentialSearchST();


        System.out.println(sequentialSearchST.isEmpty()); // True
        sequentialSearchST.put("Ankara",1);
        sequentialSearchST.put("İstanbul",2);
        sequentialSearchST.put("İzmir",3);
        sequentialSearchST.put("Mersin",4);
        sequentialSearchST.put("Ankara",5); // to check if it increments the count
        sequentialSearchST.delete("Ankara"); // deletes the key
        sequentialSearchST.put("Muğla",6);
        sequentialSearchST.put("Muğla",7);
        sequentialSearchST.put("Muğla",8);
        sequentialSearchST.put("Muğla",9);
        sequentialSearchST.put("İzmir",10);
        sequentialSearchST.put("Tokat",11);
        sequentialSearchST.put("İzmir",12);
        sequentialSearchST.put("Muğla",13);
        sequentialSearchST.put("İzmir",14);
        sequentialSearchST.put("Muğla",15);
        sequentialSearchST.put("İzmir",16);

        System.out.println(sequentialSearchST.isEmpty()); // False
        System.out.println("Size is: "+ sequentialSearchST.size());

        System.out.println(sequentialSearchST.contains("Ankara")); // false since we delete it
        System.out.println(sequentialSearchST.counts());
        System.out.println(sequentialSearchST.keys());
        System.out.println(sequentialSearchST.keysAndCounts());
        //int [] array = sequentialSearchST.maxCount();

        //for(int i = 0;i<array.length;i++) System.out.println(array[i]);
    }
}