import java.io.FileWriter;

public class WriteFile {

    public void write(DoublyLinkedList<City> cities, String filePath){
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < cities.size(); i++){
                City currentCity = cities.get(i);
                writer.write(currentCity.name() + "\n");
                writer.write("Packages:\n");
                while(currentCity.packages().size() > 0){
                    Package currentPackage = currentCity.packages().pop();
                    writer.write(currentPackage.data().toString() + "\n");
                }
                writer.write("Vehicles:\n");
                for (int j = 0; j < currentCity.vehicles().size(); j++){
                    Vehicle currentVehicle = currentCity.vehicles().get(j);
                    writer.write(currentVehicle.name() + "\n");
                }
                writer.write("-------------\n");
            }
            writer.close();
        } catch (Exception e) {
            Utils.printError("FILE NOT FOUND");
            e.printStackTrace();
        }

    }
}
