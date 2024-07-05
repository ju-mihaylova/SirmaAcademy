package customlist10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomList<T extends Comparable<T>> {

    private List<T> data;
    private int size;

    public int size() {
        return size;
    }

    public T get(int index) {
        return this.data.get(index);
    }
    public CustomList() {
        this.data = new ArrayList<>();
        this.size = 0;
    }

    public void add(T element) {
        this.data.add(element);
        this.size++;
    }

    public T remove(int index) {
        checkIndex(index);
        this.size--;
        return this.data.remove(index);
    }

    public boolean contains(T element) {
        return this.data.contains(element);
    }

    public void swap(int firstIndex, int secondIndex) {
        checkIndex(firstIndex);
        checkIndex(secondIndex);
        T temp = this.data.get(firstIndex);
        this.data.set(firstIndex, this.data.get(secondIndex));
        this.data.set(secondIndex, temp);
    }

    public int countGreaterThan(T element) {
        int count = 0;

        for (T el : this.data) {
            int compare = el.compareTo(element);

            if (compare > 0) {
                count++;
            }
        }
        
        
        return count;
    }

    public T getMax() {
        checkEmpty();
        return Collections.max(this.data);
    }

    public T getMin() {
        checkEmpty();
        return Collections.min(this.data);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.data.size()) {
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds for size %d", index, this.data.size()));
        }
    }

    private void checkEmpty() {
        if (this.data.isEmpty()) {
            throw new IllegalStateException("List is empty!");
        }
    }

}
