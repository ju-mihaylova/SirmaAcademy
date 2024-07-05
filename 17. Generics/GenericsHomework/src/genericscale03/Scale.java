package genericscale03;

public class Scale<T extends Comparable<T>> {
    private T left;
    private T right;

    public Scale(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public T getRight() {
        return right;
    }

    public void setRight(T right) {
        this.right = right;
    }

    public T getHeavier() {
        int compare = this.left.compareTo(this.right);
        if (compare > 0) {
            return this.left;
        } else if (compare < 0) {
            return this.right;
        } else {
            return null;
        }
    }
}
