public class Vehicle {

    private String name;
    private double priority;
    private Vehicle next;
    private Vehicle prev;
    private DoublyLinkedList<Package> cargo = new DoublyLinkedList<>();

    Vehicle(String name, double priority) {
        this.name = name;
        this.priority = priority;
    }

    public void setNext(Vehicle next) {
        this.next = next;
    }

    public Vehicle getNext() {
        return next;
    }

    public void setPrev(Vehicle prev) {
        this.prev = prev;
    }

    public Vehicle getPrev() {
        return prev;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public void pickUp(Center center) {
        Node<Package> packageNode = center.packages().pop(); // Attempt to remove the package from the center
        if (packageNode == null) {
            System.out.println("WARNING: NO PACKAGE - Vehicle/pickUp " + center.getName());
            return; // Avoid pushing null nodes to cargo
        }
        cargo.push(packageNode); // Add the package to the vehicle's cargo
        System.out.println("INFO: Picked up " + packageNode.getSelf().getData() + " from " + center.getName());
    }

    public void dropOff(Center center) {
        Node<Package> n = cargo.getFirst();
        cargo.removeFirst();
        center.packages().addFirst(n);
    }

    public void dropOffIndex(Center center, int index) {
        if (index < 0 || index >= cargo.size()) {
            System.out.println("WARNING: INVALID INDEX - Vehicle/Dropoff");
            return;
        }
        Node<Package> packageNode = cargo.get(index);
        if (packageNode != null) {
            center.packages().addFirst(new Node<>(packageNode.getSelf()));
            cargo.remove(index);
        }
    }

    public String getName() {
        return name;
    }

    public int size() {
        return cargo.size();
    }

    public DoublyLinkedList<Package> cargo() {
        return cargo;
    }
}
