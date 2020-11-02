//-----------------------------------------------------
// Title: CMPE343 HW1
// Author: Ahmet Cemal Sert
// Section: 1
// Assignment: HW1 Part1
// Description: This class defines a Seperate Chaining Hash Table implementation
//-----------------------------------------------------

package Part1;

public class SeperateChainingHashTable<Key, Value>  {
    private static final int capacity = 4;

    private int N;                                // number of key-value pairs
    private int M;                                // hash table size
    private SequentialSearchST<Key, Value>[] st;  // array of linked-list symbol tables

    //Initializes an empty Hash Table
    public SeperateChainingHashTable() {
        this(capacity);
    }


    // Initializes an empty Hash Table with M chains
    public SeperateChainingHashTable(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST<Key, Value>();
    }


    // Getter and Setters
    public static int getCapacity() {
        return capacity;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public SequentialSearchST<Key, Value>[] getSt() {
        return st;
    }

    public void setSt(SequentialSearchST<Key, Value>[] st) {
        this.st = st;
    }


    //Resizes hash table in order the given parameter (which is the number of chains) and rehashes all keys
    private void resize(int chains) {
        SeperateChainingHashTable<Key, Value> temp = new SeperateChainingHashTable<Key, Value>(chains);
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.M  = temp.M;
        this.N  = temp.N;
        this.st = temp.st;
    }


    // hash function for keys
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }


    // returns the number of key-value pairs in the HashTable
    public int getSize() {
        return N;
    }


    // returns true if HashTable is empty
    public boolean isEmpty() {
        return getSize() == 0;
    }


    // returns true if the HashTable contains the specified key takes a key as parameter
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    // returns the value associated with the specified key in HashTable
    // Throws IllegalArgumentException if the value is null
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Value of HashTable can't be null");
        int i = hash(key);
        return st[i].get(key);
    }


    //  inserts the specified key-value pair into the HashTable
    //  if value of the given key is null throws IllegalArgumentException
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("Value of HashTable can't be null");
        if (val == null) {
            delete(key);
            return;
        }

        // double table size if average length of list >= 10
        if (N >= 10*M) resize(2*M);

        int i = hash(key);
        if (!st[i].contains(key)) N++;
        st[i].put(key, val);
    }



    // removes the specified key and its value from HashTable
    //  if value of the given key is null throws IllegalArgumentException
    public void delete(Key key) {

        if (key == null) throw new IllegalArgumentException("Value of HashTable can't be null");

        int i = hash(key);
        if (st[i].contains(key)) N--;
        st[i].delete(key);

        // halve table size if average length of list <= 2
        if (M > capacity && N <= 2*M) resize(M/2);
    }

    // returns keys in symbol table as an Iterable
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }

    // returns the number of occurences of the hashtags
    public Iterable<Key> counts(){
        Queue<Integer> queue = new Queue<Integer>();
        for (int i = 0; i < M; i++) {
            for (Key value : st[i].counts()) queue.enqueue((Integer) value);
        }
        return (Iterable<Key>) queue;
    }

    //Returns all the hashtags with how many times they occur
    public Iterable keysAndCounts(){
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++) {
            for (Object key : st[i].keysAndCounts())
                queue.enqueue((Key) key);
        }
        return queue;
    }

    public Iterable MostPopularHashTags(){
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++) {
            for (Object key : st[i].popularKeysAndCounts())
                if(queue.size() < 10) {
                    queue.enqueue((Key) key);
                }
                else break;
        }
        return queue;
    }



    // Tester class of the SeperateChainingHashTable
    public static void main(String [] args){
        String fileName = "C:\\Users\\ACS\\Desktop\\twitter.txt";

        SeperateChainingHashTable seperateChainingHashTable = new SeperateChainingHashTable(5000);
        Reader.readHashtags(fileName,seperateChainingHashTable);


        System.out.println(seperateChainingHashTable.getSize());
        System.out.println("Does seperateChainingHashTable contains #lovemesomefootballguys :"+seperateChainingHashTable.contains("#lovemesomefootballguys"));
        System.out.println(seperateChainingHashTable.getSize());
        System.out.println(seperateChainingHashTable.keys()); // all of our keys
        System.out.println(seperateChainingHashTable.isEmpty());
        System.out.println(seperateChainingHashTable.MostPopularHashTags());

    }
}