public class DoublyLinkedList<DATA_TYPE> {
    private Node first;
    private Node last;
    private int size = 0;

    public void addToFront(DATA_TYPE data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            first = newNode;
            last = newNode;

            size++;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;

            size++;
        }
    }

    public void addToBack(DATA_TYPE data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
    }

    public DATA_TYPE removeFromFront() {
        if (isEmpty()) {
            return null;
        }
        Node oldFirst = first;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return oldFirst.data;
    }

    public DATA_TYPE removeFromBack() {
        if (isEmpty()) {
            return null;
        }
        Node oldLast = last;
        if (first == last) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return oldLast.data;
    }

    public int indexOf(DATA_TYPE candidate) {
        Node current = first;
        int index = 0;
        while (current != null) {
            if (current.data.equals(candidate)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public DATA_TYPE get(int index) {

        if (index < 0 || index >= size) {
            System.out.println("RETURNED NULL, INDEX: " + index + ", SIZE: " + size);
            return null;
        }
        Node current = first;

        if (index == 0) {
            return first.data;
        } else if (index == size - 1) {
            return last.data;
        } else if (index < size / 2) {
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.data;
        } else {
            current = last;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current.data;
        }
    }

    public boolean contains(DATA_TYPE candidate) {
        return indexOf(candidate) != -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void remove(DATA_TYPE candidate) {
        int index = indexOf(candidate);
        if (index != -1) {
            removeIndex(index);
        }
    }

    public DATA_TYPE removeIndex(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return dequeue();
        } else if (index == size - 1) {
            return pop();
        } else {
            Node current = first;

            if (index < size / 2) {
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
            } else {
                current = last;
                for (int i = size - 1; i > index; i--) {
                    current = current.prev;
                }
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
            return current.data;
        }
    }

    public DATA_TYPE findByName(String name) {
        Node current = first;
        while (current != null) {
            if (current.data.toString().equals(name)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public void enqueue(DATA_TYPE data) {
        addToBack(data);
    }

    public DATA_TYPE dequeue() {
        return removeFromFront();
    }

    public DATA_TYPE peek() {
        return last.data;
    }

    public DATA_TYPE pop() {
        return removeFromBack();
    }

    public void push(DATA_TYPE data) {
        addToBack(data);
    }

    public DATA_TYPE getFirst() {
        return first.data;
    }

    public DATA_TYPE getLast() {
        return last.data;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String output = "<NONE>";
        Node current = first;
        while (current != null) {
            if (current == first && current != last) {
                output = "";
                output += "--first-- " + current.data.toString() + ", ";
            } else if (current == last) {
                output += current.data.toString() + " --last--";
                break;
            } else {
                output += current.data.toString() + ", ";
            }
            current = current.next;
        }
        return output;
    }

    class Node {
        DATA_TYPE data;
        Node prev;
        Node next;

        Node(DATA_TYPE data) {
            this.data = data;
        }
    }

}
