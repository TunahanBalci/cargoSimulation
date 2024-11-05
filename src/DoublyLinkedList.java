public class DoublyLinkedList<CUSTOM_TYPE> {

    private Node<CUSTOM_TYPE> first;
    private Node<CUSTOM_TYPE> last;
    private int size;

    public DoublyLinkedList() {
        this.size = 0;
    }

    public void addFirst(Node<CUSTOM_TYPE> x) {
        if (size == 0) {
            first = last = x;
            x.setNext(null);
            x.setPrev(null);
        } else {
            x.setNext(first);
            first.setPrev(x);
            first = x;
        }
        size++;
    }

    public void addLast(Node<CUSTOM_TYPE> x) {
        if (size == 0) {
            first = last = x;
            x.setNext(null);
            x.setPrev(null);
        } else {
            last.setNext(x);
            x.setPrev(last);
            last = x;
        }
        size++;
    }

    public Node<CUSTOM_TYPE> push(Node<CUSTOM_TYPE> x) {
        addLast(x);
        return x;
    }

    public Node<CUSTOM_TYPE> pop() {
        if (size == 0) return null;
        Node<CUSTOM_TYPE> temp = last;
        removeLast();
        return temp;
    }

    public Node<CUSTOM_TYPE> peek() {
        return last;
    }

    public void addIndex(Node<CUSTOM_TYPE> x, int index) {
        if (index == 0) {
            addFirst(x);
        } else if (index == size) {
            addLast(x);
        } else {
            Node<CUSTOM_TYPE> current = (index > size / 2) ? last : first;
            for (int i = (index > size / 2) ? size - 1 : 0; i != index; i += (index > size / 2) ? -1 : 1) {
                current = (index > size / 2) ? current.getPrev() : current.getNext();
            }
            x.setNext(current);
            x.setPrev(current.getPrev());
            current.getPrev().setNext(x);
            current.setPrev(x);
            size++;
        }
    }

    public void removeFirst() {
        if (size == 0) return;
        if (size == 1) {
            first = last = null;
        } else {
            first = first.getNext();
            first.setPrev(null);
        }
        size--;
    }

    public void removeLast() {
        if (size == 0) return;
        if (size == 1) {
            first = last = null;
        } else {
            last = last.getPrev();
            last.setNext(null);
        }
        size--;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index - removal");
            return;
        }
        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            Node<CUSTOM_TYPE> current = (index > size / 2) ? last : first;
            int i = (index > size / 2) ? size - 1 : 0;
            while (i != index) {
                current = (index > size / 2) ? current.getPrev() : current.getNext();
                i += (index > size / 2) ? -1 : 1;
            }
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
            size--;
        }
    }


    public void removeSpecific(Node<CUSTOM_TYPE> x) {
        Node<CUSTOM_TYPE> current = first;
        while (current != null) {
            if (current.getSelf().equals(x.getSelf())) {
                if (current == first) {
                    removeFirst();
                } else if (current == last) {
                    removeLast();
                } else {
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());
                }
                size--;
                return;
            }
            current = current.getNext();
        }
    }

    public Node<CUSTOM_TYPE> getFirst() {
        return first;
    }

    public Node<CUSTOM_TYPE> getLast() {
        return last;
    }

    public int size() {
        return size;
    }

    public Node<CUSTOM_TYPE> get(int index) {
        if (index < 0 || index >= size) return null;
        Node<CUSTOM_TYPE> current = (index > size / 2) ? last : first;
        for (int i = (index > size / 2) ? size - 1 : 0; i != index; i += (index > size / 2) ? -1 : 1) {
            current = (index > size / 2) ? current.getPrev() : current.getNext();
        }
        return current;
    }

    public int indexOf(CUSTOM_TYPE value) {
        Node<CUSTOM_TYPE> current = first;
        int index = 0;
        while (current != null) {
            if (current.getSelf().equals(value)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1; // Return -1 if the value is not found
    }

}
