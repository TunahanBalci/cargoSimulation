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
            x.setNext(null);
            last = x;
        }
        size++;
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
        if (index < 0 || index >= size) return;
        if (index == 0) {
            removeFirst();
            return;
        }
        if (index == size - 1) {
            removeLast();
            return;
        }

        Node<CUSTOM_TYPE> current;
        if (index > size / 2) {
            current = last;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
        } else {
            current = first;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        }
        current.getPrev().setNext(current.getNext());
        current.getNext().setPrev(current.getPrev());
        size--;
    }

    public void addIndex(Node<CUSTOM_TYPE> p, int index) {
        if (index == 0) {
            addFirst(p);
        } else if (index == size) {
            addLast(p);
        } else {
            Node<CUSTOM_TYPE> current = (index > size / 2) ? last : first;
            for (int i = (index > size / 2) ? size - 1 : 0; i != index; i += (index > size / 2) ? -1 : 1) {
                current = (index > size / 2) ? current.getPrev() : current.getNext();
            }
            p.setNext(current);
            p.setPrev(current.getPrev());
            current.getPrev().setNext(p);
            current.setPrev(p);
            size++;
        }
    }

    public Node<CUSTOM_TYPE> pop() {
        Node<CUSTOM_TYPE> temp = last;
        removeLast();
        return temp;
    }

    public Node<CUSTOM_TYPE> push(Node<CUSTOM_TYPE> x) {
        addLast(x);
        return x;
    }

    public Node<CUSTOM_TYPE> peek() {
        return last;
    }

    public Node<CUSTOM_TYPE> getNode(Object candidate) {
        Node<CUSTOM_TYPE> current = first;
        while (current != null) {
            if (current.getSelf().equals(candidate)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean includes(Node<CUSTOM_TYPE> candidate) {
        Node<CUSTOM_TYPE> current = first;
        while (current != null) {
            if (current.getSelf().equals(candidate.getSelf())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void removeSpecific(Node<CUSTOM_TYPE> candidate) {
        Node<CUSTOM_TYPE> current = first;
        while (current != null) {
            if (current.getSelf().equals(candidate.getSelf())) {
                if (current == first) {
                    removeFirst();
                } else if (current == last) {
                    removeLast();
                } else {
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());
                    size--;
                }
                return;
            }
            current = current.getNext();
        }
    }

    public void replaceSpecific(Node<CUSTOM_TYPE> candidate, Node<CUSTOM_TYPE> replacement) {
        Node<CUSTOM_TYPE> current = first;
        while (current != null) {
            if (current.getSelf().equals(candidate.getSelf())) {
                current.setSelf(replacement.getSelf());
                return;
            }
            current = current.getNext();
        }
    }

    public Node<CUSTOM_TYPE> get(int index) {
        if (index < 0 || index >= size) return null;
        Node<CUSTOM_TYPE> current = last;
        for (int i = 0; i < index; i++) {
            current = current.getPrev();
        }
        return current;

    }
}
