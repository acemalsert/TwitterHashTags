//-----------------------------------------------------
// Title: CMPE343 HW1
// Author: Ahmet Cemal Sert
// Section: 1
// Assignment: HW1 Part2
// Description: This class defines a Linear Probing  HashTable implementation
// Reference: https://algs4.cs.princeton.edu/34hash/LinearProbingHashST.java.html
//-----------------------------------------------------

package Part2;

public class LinearProbingHashTable<Key,Value> {


        // must be a power of 2
        private static final int INIT_CAPACITY = 4;

        private int n;           // number of key-value pairs in the symbol table
        private int m;           // size of linear probing table
        private Key[] keys;      // the keys
        private Value[] vals;    // the values
        private int probing = 0;


        // Empty Constructor
        public LinearProbingHashTable() {
            this(INIT_CAPACITY);
        }

        // Constructor
        public LinearProbingHashTable(int capacity) {
            m = capacity;
            n = 0;
            keys = (Key[])   new Object[m];
            vals = (Value[]) new Object[m];
        }


        // Getters and Setters
    public static int getInitCapacity() {
        return INIT_CAPACITY;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public Key[] getKeys() {
        return keys;
    }

    public void setKeys(Key[] keys) {
        this.keys = keys;
    }

    public Value[] getVals() {
        return vals;
    }

    public void setVals(Value[] vals) {
        this.vals = vals;
    }

    public int getProbing() {
        return probing;
    }

    public void setProbing(int probing) {
        this.probing = probing;
    }

    // Returns size
        public int size() {
            return n;
        }

        // Checks if the symbol table is empty
        public boolean isEmpty() {
            return size() == 0;
        }


        // Returns true if this symbol table contains the specified key. Throws IllegalArgumentException if key is null
        public boolean contains(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to contains() is null");
            return get(key) != null;
        }


        // hash function for keys - returns value between 0 and m-1 (assumes m is a power of 2)
        // (from Java 7 implementation, protects against poor quality hashCode() implementations)
        private int hash(Key key) {
            probing++;
            int h = key.hashCode();
            h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
            return h & (m-1);
        }

        // resizes the hash table to the given capacity by re-hashing all of the keys
        private void resize(int capacity) {
            LinearProbingHashTable<Key, Value> temp = new LinearProbingHashTable<Key, Value>(capacity);
            for (int i = 0; i < m; i++) {
                if (keys[i] != null) {
                    temp.put(keys[i], vals[i]);
                }
            }
            keys = temp.keys;
            vals = temp.vals;
            m    = temp.m;
        }


        //Inserts the specified key-value pair into the symbol table, overwriting the old
        //value with the new value if the symbol table already contains the specified key.
        public void put(Key key, Value val) {
            if (key == null) throw new IllegalArgumentException("first argument to put() is null");

            if (val == null) {
                delete(key);
                return;
            }

            // double table size if 50% full
            if (n >= m/2) resize(2*m);

            int i;
            for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
                if (keys[i].equals(key)) {
                    vals[i] = val;
                    return;
                }
            }
            keys[i] = key;
            vals[i] = val;
            n++;
        }


        // Returns the value associated with the specified key.
        public Value get(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to get() is null");
            for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
                if (keys[i].equals(key))
                    return vals[i];
            return null;
        }



        //Removes the specified key and its associated value from this symbol table if the key is in this symbol table
        public void delete(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to delete() is null");
            if (!contains(key)) return;

            // find position i of key
            int i = hash(key);
            while (!key.equals(keys[i])) {
                i = (i + 1) % m;
            }

            // delete key and associated value
            keys[i] = null;
            vals[i] = null;

            // rehash all keys in same cluster
            i = (i + 1) % m;
            while (keys[i] != null) {
                // delete keys[i] an vals[i] and reinsert
                Key   keyToRehash = keys[i];
                Value valToRehash = vals[i];
                keys[i] = null;
                vals[i] = null;
                n--;
                put(keyToRehash, valToRehash);
                i = (i + 1) % m;
            }

            n--;

            // halves size of array if it's 12.5% full or less
            if (n > 0 && n <= m/8) resize(m/2);

            assert check();
        }

       // Returns all keys in  table as an  Iterable
        public Iterable<Key> keys() {
            Queue<Key> queue = new Queue<Key>();
            for (int i = 0; i < m; i++)
                if (keys[i] != null) queue.enqueue(keys[i]);
            return queue;
        }

        // integrity check - don't check after each put() because
        // integrity not maintained during a delete()
        private boolean check() {

            // check that hash table is at most 50% full
            if (m < 2*n) {
                System.err.println("Hash table size m = " + m + "; array size n = " + n);
                return false;
            }

            // check that each key in table can be found by get()
            for (int i = 0; i < m; i++) {
                if (keys[i] == null) continue;
                else if (get(keys[i]) != vals[i]) {
                    System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
                    return false;
                }
            }
            return true;
        }
    }

