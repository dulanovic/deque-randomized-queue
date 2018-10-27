package data_structures;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int length = 0;

    public RandomizedQueue() {
        items = (Item[]) new Object[4];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return length == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return length;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null!!!");
        }
        items[length++] = item;
        if (length == items.length) {
            resize(2 * items.length);
        }
        // StdOut.printf("Element enqueued ---> %s\n", item);
        // writeReport();
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Randomized queue is empty!!!");
        }
        int randomIndex = StdRandom.uniform(length);
        int newLast = --length;
        Item i = items[randomIndex];
        if (randomIndex != newLast) {
            items[randomIndex] = items[newLast];
            items[newLast] = null;
        }
        if (!isEmpty() && length <= items.length / 4) {
            resize(items.length / 2);
        }
        // StdOut.printf("Element dequeued ---> %s\n", i);
        // writeReport();
        return i;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Randomized queue is empty!!!");
        }
        int randomIndex = StdRandom.uniform(length);
        Item i = items[randomIndex];
        // StdOut.printf("Random element ---> %s\n", i);
        return i;
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        int noIndices = (items.length < capacity) ? items.length : capacity;
        int index = 0;
        for (int i = 0; i < noIndices; i++) {
            if (items[i] != null) {
                newArray[index] = items[i];
                index++;
            }
        }
        this.items = newArray;
        // StdOut.printf("NEW!!! Total capacity of the randomized queue ---> %d\n\n", items.length);
    }

    /* private void writeReport() {
        if (isEmpty()) {
            StdOut.printf("Randomized queue is empty!!!\n");
        }
        StdOut.printf("Total number of elements ---> %d\n", size());
        Iterator<Item> i = iterator();
        while (i.hasNext()) {
            StdOut.printf("Element ---> %s\n", i.next());
        }
        StdOut.println("---------------------------\n");
    } */
    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final Item[] newArray;
        private int current;

        public RandomizedQueueIterator() {
            newArray = (Item[]) new Object[items.length];
            current = 0;
            for (int i = 0; i < items.length; i++) {
                newArray[i] = items[i];
            }
            StdRandom.shuffle(newArray);
        }

        @Override
        public boolean hasNext() {
            while (current < items.length) {
                if (newArray[current] != null) {
                    return true;
                }
                current++;
            }
            return false;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Method not implemented!!!");
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in randomized queue!!!");
            }
            return newArray[current++];
        }

    }

    // unit testing (optional)
    public static void main(String[] args) {

        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        StdOut.printf("rq.size() ---> %s\n", rq.size());
        StdOut.printf("rq.isEmpty() ---> %s\n", rq.isEmpty());
        rq.enqueue(949);
        StdOut.printf("rq.dequeue() ---> %s\n", rq.dequeue());
        // StdOut.printf("rq.sample() ---> %s\n", rq.sample());
        /* rq.enqueue("QSKINQPKJZ");
        rq.enqueue("ZFQNTFHUVA");
        rq.enqueue("UAMWYUVUEK");
        StdOut.printf("rq.sample() ---> %s\n", rq.sample());
        rq.enqueue("PFAQFZXECY");
        StdOut.printf("rq.dequeue() ---> %s\n", rq.dequeue());
        StdOut.printf("rq.dequeue() ---> %s\n", rq.dequeue()); */
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);
        StdOut.printf("rq.dequeue() ---> %d\n", rq.dequeue());
        StdOut.printf("rq.dequeue() ---> %d\n", rq.dequeue());
        StdOut.printf("rq.dequeue() ---> %d\n", rq.dequeue());
        StdOut.printf("rq.dequeue() ---> %d\n", rq.dequeue());
        Iterator i = rq.iterator();
        while (i.hasNext()) {
            StdOut.printf("Element ---> %d\n", i.next());
        }
    }

}
