package genericswapmethodstrings06;

import java.util.List;

public class Box<T> {

    private T data;

    public Box(T data) {
        this.data = data;
    }

    public static <T> void swapItems(List<T> list, int firstIndex, int secondIndex) {
        T temp = list.get(firstIndex);
        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, temp);
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.data.getClass().getName(), this.data);
    }
}
