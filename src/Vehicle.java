import java.util.Arrays;

public class Vehicle {

    Stack<Package> packages = new Stack<>();
    private double priority;
    private String name;

    public Vehicle(String name, double priority) {
        this.name = name;
        this.priority = priority;
    }

    public void pickUp(City city, int count) {
        for (int i = 0; i < count; i++) {
            Package p = city.packages().pop();
            packages.push(p);
            if (Tester.DEBUG_VEHICLE_INFO){
                Utils.printInfo("Vehicle " + name + " picked up package " + p + " from " + city.name());
            }
        }
    }

    public void deliverIndices(City city, int[] indices) {
        Arrays.sort(indices);
        for (int i = 0; i < indices.length; i++) {
            Package p = packages.removeIndex(indices[i] - i);
            city.packages().push(p);

            if (Tester.DEBUG_VEHICLE_INFO){
                Utils.printInfo("Vehicle " + name + " __index_delivered__ package " + p + " to " + city.name() + " - index = " + (indices[i] - i));
            }
        }
    }

    public void deliverAll(City city) {

        Queue<Package> temp = new Queue<>();
        while (!packages.isEmpty()) {
            temp.enqueue(packages.pop());
        }
        while (!temp.isEmpty()) {
            packages.push(temp.dequeue());
        }
        while (!packages.isEmpty()) {
            Package p = packages.pop();
            city.packages().push(p);
            if (Tester.DEBUG_VEHICLE_INFO){
                Utils.printInfo("Vehicle " + name + " delivered package " + p + " to " + city.name());
            }
        }
    }

    public double priority() {
        return priority;
    }

    public String name() {
        return name;
    }

    public String toString() {
        return name;
    }
}
