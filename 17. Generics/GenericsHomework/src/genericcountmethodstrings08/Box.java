package genericcountmethodstrings08;

import java.util.List;

public class Box<T extends Comparable<T>> implements Comparable<Box<T>> {

    private T data;

    public Box(T data) {
        this.data = data;
    }

    public static <T> void swapItems(List<T> list, int firstIndex, int secondIndex) {
        T temp = list.get(firstIndex);
        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, temp);
    }

    public static <T extends Comparable<T>> int count(List<T> list, T element) {
        int count = 0;
        for (T el : list) {
            int compare = el.compareTo(element);
            if (compare > 0) {
                count++;
            }
        }

        return count;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.data.getClass().getName(), this.data);
    }

    @Override
    public int compareTo(Box<T> o) {
        return this.data.compareTo(o.data);
    }
}
