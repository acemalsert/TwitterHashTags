//-----------------------------------------------------
// Title: CMPE343 HW1
// Author: Ahmet Cemal Sert
// Section: 1
// Assignment: HW1 Part2
// Description: This class defines a Queue implementation
//-----------------------------------------------------

package Part2;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Queue<Item> implements Iterable<Item>  {
    private Node<Item> first;    // beginning of the queue
    private Node<Item> last;     // end of the queue
    private int N;               // number of items in the queue



    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    // Empty Constructor
    public Queue() {
        first = null;
        last  = null;
        N = 0;
    }


    // Checks if the queue is empty
    public boolean isEmpty() {
        return first == null;
    }


    // Returns the number of items in the queue
    public int size() {
        return N;
    }

    // Returns the item least recently added
    // Throws NoSuchElementException if this queue is empty
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    // adds the given item to the queue
    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        N++;
    }


    // Removes and returns the last item(the least recent item)
    // Throws NoSuchElementException if this queue is empty
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }


    // toString method
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append('\n');
        }
        return s.toString();
    }

    // Returns an iterator that iterates over the items in queue in FIFO order
    public Iterator<Item> iterator()  {
        return new LinkedIterator(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}