import java.io.File;
import java.util.Scanner;

public class ReadFile {

    public DoublyLinkedList<String> read(String filename) {
        DoublyLinkedList<String> LineList = new DoublyLinkedList();
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                LineList.enqueue(line);
            }
            return LineList;
        } catch (Exception e) {
            Utils.printError("FILE NOT FOUND");
            e.printStackTrace();
        }
        return null;
    }


    public void processCities(DoublyLinkedList<City> cities,
                              DoublyLinkedList<String> lineList){
        while (!lineList.isEmpty()){
            cities.push(new City(lineList.dequeue()));
        }
    }

    public void processVehicles(DoublyLinkedList<City> cities,
                                DoublyLinkedList<String> lineList){
        while (!lineList.isEmpty()){
            String[] line = lineList.dequeue().split(" ");
            String name = line[0];
            double priority = Double.parseDouble(line[2]);

            cities.findByName(line[1]).addVehicle(new Vehicle(name, priority));
        }
    }

    public void processPackages(DoublyLinkedList<City> cities,
                                DoublyLinkedList<String> lineList){

        while (!lineList.isEmpty()){
            String[] line = lineList.dequeue().split(" ");
            String data = line[0];
            City city = cities.findByName(line[1]);
            city.packages().push(new Package<String>(data));

            if (Tester.DEBUG_READFILE_INFO){
                Utils.printReadFile("Added package " + city.packages().peek() + " to city " + city.name());
            }
        }

    }

    public void processMissions(DoublyLinkedList<Mission> missions,
                                DoublyLinkedList<City> cities,
                                DoublyLinkedList<String> lineList){
        while (!lineList.isEmpty()){
            String[] line = lineList.dequeue().split("-");
            City sourceCity = cities.findByName(line[0]);
            City middleCity = cities.findByName(line[1]);
            City destinationCity = cities.findByName(line[2]);
            int sourcePickup = Integer.parseInt(line[3]);
            int middlePickup = Integer.parseInt(line[4]);
            String [] temp = line[5].split(",");
            int [] middleDrop = new int[temp.length];
            for (int i = 0; i < temp.length; i++){
                middleDrop[i] = Integer.parseInt(temp[i]);
            }
            missions.push(new Mission(sourceCity, middleCity, destinationCity, sourcePickup, middlePickup, middleDrop));
        }
    }
}
