package jar01;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class Jar<E> {
    private final Deque<E> items;

    public Jar() {
        this.items = new ArrayDeque<>();
    }

    public void add(E item) {
        this.items.push(item);
    }

    public E remove() {
        if (items.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.items.pop();
    }

    public int size() {
        return this.items.size();
    }
}
