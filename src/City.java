public class City {

    private String name;
    private DoublyLinkedList<Vehicle> vehicles = new DoublyLinkedList<>();
    private Stack<Package> packages = new Stack<>();

    public City(String name) {
        this.name = name;
    }

    public Package removePackage(Package p) {
        return packages.dequeue();
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.enqueue(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) { //removes specific
        vehicles.remove(vehicle);
    }

    public DoublyLinkedList<Vehicle> vehicles() {
        return vehicles;
    }

    public Stack<Package> packages() {
        return packages;
    }

    public String name() {
        return name;
    }

    public Vehicle getPriortyVehicle() {
        int count = 0;
        Vehicle temp1 = this.vehicles.get(0);
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).priority() > temp1.priority()) {
                temp1 = vehicles.get(i);
            }
        }
        return temp1;
    }

    @Override
    public String toString() {
        return name;
    }

    public void debug(){
        Utils.cityInfo("City: " + name);
        Utils.cityInfo("Packages:");

        Utils.cityInfo(packages.toString());
        Utils.cityInfo("Vehicles:");

        for (int i = 0; i < vehicles.size(); i++){
            Utils.cityInfo(vehicles.get(i).name());
        }
    }
}
