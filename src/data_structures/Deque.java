package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node first, last;

    private class Node {

        private final Item item;
        private Node next;
        private Node previous;

        private Node(Item item, Node next, Node previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }

        private Item getItem() {
            return item;
        }

        private Node(Item item) {
            this.item = item;
        }

        private Node getNext() {
            return next;
        }

        private void setNext(Node next) {
            this.next = next;
        }

        private Node getPrevious() {
            return previous;
        }

        private void setPrevious(Node previous) {
            this.previous = previous;
        }

    }

    // construct an empty deque
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null!!!");
        }
        Node n = new Node(item);
        if (size == 0) {
            insertFirstElement(n);
        } else {
            n.setNext(first);
            first.setPrevious(n);
            first = n;
        }
        size++;
        // StdOut.printf("Added to the beginning of deque ---> %s\n", item);
        // writeReport();
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null!!!");
        }
        Node n = new Node(item, null, last);
        if (size == 0) {
            insertFirstElement(n);
        } else {
            Node oldLast = last;
            oldLast.setNext(n);
            last = n;
        }
        size++;
        // StdOut.printf("Added to the end of deque ---> %s\n", item);
        // writeReport();
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("data_structures.Deque is already empty!!!");
        }
        Item i = first.getItem();
        if (size == 1) {
            removeLastElement();
        } else {
            first = first.getNext();
            first.setPrevious(null);
            size--;
        }
        // StdOut.printf("Removed from the beginning of deque ---> %s\n", i);
        // writeReport();
        return i;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("data_structures.Deque is already empty!!!");
        }
        Item i = last.getItem();
        if (size == 1) {
            removeLastElement();
        } else {
            last = last.getPrevious();
            last.setNext(null);
            size--;
        }
        // StdOut.printf("Removed from the end of deque ---> %s\n", i);
        // writeReport();
        return i;
    }

    private void insertFirstElement(Node node) {
        node.setNext(null);
        node.setPrevious(null);
        first = node;
        last = node;
    }

    private void removeLastElement() {
        first = null;
        last = null;
        size = 0;
        // StdOut.println("Last element removed from deque.");
    }

    /* private void writeReport() {
        if (size == 0) {
            StdOut.println("No elements in deque!!!");
            return;
        }
        StdOut.printf("Total number of elements ---> %d\nFirst element ---> %s\nLast element ---> %s\n", size(), first.item, last.item);
        Iterator<Item> i = iterator();
        while (i.hasNext()) {
            StdOut.printf("Element ---> %s\n", i.next());
        }
        StdOut.println("---------------------------\n");
    } */
    // return an iterator over items in order from front to end
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Method not implemented!!!");
        }

        @Override
        public Item next() {
            Item i = null;
            if (current == null) {
                throw new NoSuchElementException("No more elements in  deque!!!");
            } else {
                i = current.getItem();
                current = current.getNext();
            }
            return i;
        }

    }

    // unit testing (optional)
    public static void main(String[] args) {

        Deque<Integer> deque = new Deque<Integer>();
        StdOut.printf("deque.isEmpty() ---> %s\n", deque.isEmpty());
        StdOut.printf("size() ---> %d\n", deque.size());
        deque.addFirst(4);
        deque.addLast(4);
        StdOut.printf("deque.removeFirst() ---> %d\n", deque.removeFirst());
        StdOut.printf("deque.removeLast() ---> %d\n", deque.removeLast());

        deque.addFirst(1);
        deque.addFirst(2);
        Iterator<Integer> it = deque.iterator();
        it.next();
        // it.next();
        StdOut.printf("hasNext() ---> %s\n", it.hasNext());

    }

}
