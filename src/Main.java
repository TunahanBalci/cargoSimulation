public class Main {
    public static void main(String[] args) {

        DoublyLinkedList<City> cities = new DoublyLinkedList<>();
        DoublyLinkedList<Mission> missions = new DoublyLinkedList<>();

        ReadFile readFile = new ReadFile();
        String cityFileLocation = "cities.txt"; // change with args
        String packageFileLocation = "packages.txt"; // change with args
        String vehicleFileLocation = "vehicles.txt"; // change with args
        String missionFileLocation = "missions.txt"; // change with args

        readFile.processCities(cities, readFile.read(cityFileLocation));
        readFile.processPackages(cities, readFile.read(packageFileLocation));
        readFile.processVehicles(cities, readFile.read(vehicleFileLocation));
        readFile.processMissions(missions, cities, readFile.read(missionFileLocation));

        for (int i = 0; i < missions.size(); i++) {
            Mission mission = missions.get(i);
            if (Tester.DEBUG_MISSION_INFO){
                Utils.printInfo("NEW MISSION: " + mission.toString());
            }
            mission.execute();
        }

        WriteFile writeFile = new WriteFile();
        writeFile.write(cities, "result.txt");
        System.out.println("MISSION(S) EXECUTED SUCCESSFULLY");
    }
}