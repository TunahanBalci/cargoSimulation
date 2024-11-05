public class Center {

    private String name;
    private DoublyLinkedList<Package> packages = new DoublyLinkedList<Package>();
    private DoublyLinkedList<Vehicle> vehicles = new DoublyLinkedList<Vehicle>();

    public Center(String name) {
        this.name = name;
    }

    public void addVehicle(Node v) {
        vehicles.push(v);
    }

    public void addPackage(Node n) {
        packages.push(n);
    }

    public String getName() {
        return name;
    }

    public DoublyLinkedList<Vehicle> vehicles() {
        return vehicles;
    }

    public DoublyLinkedList<Package> packages() {
        return packages;
    }
}
