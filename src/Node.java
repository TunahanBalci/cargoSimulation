public class Node<T> {

    private Node<T> next;
    private Node<T> prev;
    private T self;

    public Node(T self) {
        if (self == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }
        this.self = self;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public T getSelf() {
        return self;
    }

    public void setSelf(T self) {
        this.self = self;
    }
}
