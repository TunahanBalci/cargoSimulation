import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File input_packages = new File("packages.txt");
        File input_vehicles = new File("vehicles.txt");
        File input_missions = new File("missions.txt");
        File input_cities = new File("cities.txt");

        DoublyLinkedList<Center> cities = new DoublyLinkedList<>();

        // Read cities.txt
        // Add centers to cities list
        try {
            Scanner sc = new Scanner(input_cities);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
//                System.out.println(line);
                cities.push(new Node<Center>(new Center(line)));
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("ERR: COULD NOT READ FILE: " + e.getMessage());
        }

        // Read packages.txt
        // Add packages to cities
        try {
            Scanner sc = new Scanner(input_packages);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] packageCityCombo = line.split(" ");

                Node<Center> currentCity = cities.getFirst();
                while (currentCity != null) {
                    if (currentCity.getSelf().getName().equals(packageCityCombo[1])){
                        currentCity.getSelf().addPackage(new Node<Package<String>>(new Package<String>(packageCityCombo[0])));
                    }
                    currentCity = currentCity.getNext();
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("ERR: COULD NOT READ FILE: " + e.getMessage());
        }


        // Read vehicles.txt
        // Add vehicles to cities

        try {
            Scanner sc = new Scanner(input_vehicles);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] vehicleCityCombo = line.split(" ");

                Node<Center> currentCity = cities.getFirst();
//                System.out.println("CURRENT CITY: " + currentCity.getSelf().getName());
                while (currentCity != null) {
                    if (currentCity.getSelf().getName().equals(vehicleCityCombo[1])) {
                        currentCity.getSelf().addVehicle(new Node<Vehicle>(new Vehicle(vehicleCityCombo[0], Double.parseDouble(vehicleCityCombo[2]))));
                    }
                    currentCity = currentCity.getNext();
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("ERR: COULD NOT READ FILE: " + e.getMessage());
        }

        // Read missions.txt
        // Move vehicles and packages, do mission operations
        try {
            Scanner sc = new Scanner(input_missions);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                Mission mission = new Mission();
                mission.operate(line, cities);
            }

        } catch (Exception e) {
            System.out.println("ERR: COULD  S S S NOT READ FILE: " + e.getMessage());
        }

        generateOutput(cities);
    }

    public static void generateOutput(DoublyLinkedList<Center> cities) {
        try {
            FileWriter writer = new FileWriter("output.txt");

            Node<Center> currentCity = cities.getFirst();
            while (currentCity != null) {

                // Write city name
                writer.write(currentCity.getSelf().getName() + "\n");

                // Write header of packages
                writer.write("Packages:\n");
                Node<Package> currentPackage = currentCity.getSelf().packages().getFirst();

                // Write packages
                while (currentPackage != null) {
                    if (currentPackage.getSelf() != null && currentPackage.getSelf().getData() != null){
                        writer.write(currentPackage.getSelf().getData().toString());
                    }
                    writer.write("\n");
                    currentPackage = currentPackage.getNext();
                }

                // Write header of vehicles
                writer.write("Vehicles:\n");
                Node<Vehicle> currentVehicle = currentCity.getSelf().vehicles().getFirst();
                while (currentVehicle != null) {
                    if (currentVehicle.getSelf() != null && currentVehicle.getSelf().getName() != null){
                        writer.write(currentVehicle.getSelf().getName());
                    }
                    writer.write("\n");
                    currentVehicle = currentVehicle.getNext();
                }
                currentCity = currentCity.getNext();
            }
            writer.close();

        } catch (Exception e) {
            System.out.println("ERR: COULD NOT WRITE TO FILE: " + e.getMessage());
        }
    }
}