import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);

        File input_packages = null;
        File input_vehicles = null;
        File input_missions = null;
        File input_cities = null;
        File input_result = null;

        while (true) {
            System.out.println("-------------------------------------");
            System.out.println("\nPROVIDE PATH FOR CITIES, PACKAGES, VEHICLES, MISSIONS AND RESULT \n");
            System.out.println("-------------------------------------");

            System.out.println("\n ENTER PATH FOR CITIES.TXT: ");
            String cities_path = scanner.nextLine();
            try {
                input_cities = new File(cities_path);
            } catch (Exception e) {
                continue;
            }


            System.out.println("\n ENTER PATH FOR PACKAGES.TXT: ");
            String packages_path = scanner.nextLine();
            try {
                input_packages = new File(packages_path);
            } catch (Exception e) {
                System.out.println("ERR: COULD NOT FIND FILE");
                continue;
            }

            System.out.println("\n ENTER PATH FOR VEHICLES.TXT: ");
            String vehicles_path = scanner.nextLine();
            try {
                input_vehicles = new File(vehicles_path);
            } catch (Exception e) {
                System.out.println("ERR: COULD NOT FIND FILE");
                continue;
            }

            System.out.println("\n ENTER PATH FOR MISSIONS.TXT: ");
            String missions_path = scanner.nextLine();
            try {
                input_missions = new File(missions_path);
            } catch (Exception e) {
                System.out.println("ERR: COULD NOT FIND FILE");
                continue;
            }

            System.out.println("\n ENTER PATH FOR RESULT.TXT: ");
            String result_path = scanner.nextLine();
            try {
                input_result = new File(result_path);
            } catch (Exception e) {
                System.out.println("ERR: COULD NOT FIND FILE");
                continue;
            }


            if (input_packages == null || input_vehicles == null || input_missions == null || input_cities == null) {
                System.out.println("ERR: COULD NOT FIND FILE");
                continue;
            } else {
                break;
            }
        }

        DoublyLinkedList<Center> cities = new DoublyLinkedList<>();

        // Read cities.txt
        // Add centers to cities list
        try {
            Scanner sc = new Scanner(input_cities);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
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

            Scanner sc = new Scanner(input_missions);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                Mission mission = new Mission();
                mission.operate(line, cities);
            }



        generateOutput(cities, input_result);
    }

    public static void generateOutput(DoublyLinkedList<Center> cities, File file) {
        try {
            FileWriter writer = new FileWriter(file);

            Node<Center> currentCity = cities.getFirst();
            while (currentCity != null) {

                // Write city name
                writer.write(currentCity.getSelf().getName() + "\n");

                // Write header of packages
                writer.write("Packages:\n");

                Node<Package> currentPackage = currentCity.getSelf().packages().getFirst();
                System.out.println("CURRENT PACKAGE: " + currentPackage.getSelf().getData());

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
                //Write seperation line
                writer.write("-------------\n");
                currentCity = currentCity.getNext();
            }
            writer.close();

        } catch (Exception e) {
            System.out.println("ERR: COULD NOT WRITE TO FILE: " + e.getMessage());
        }
    }
}