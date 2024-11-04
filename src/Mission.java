import java.util.Arrays;

public class Mission {

    public static Vehicle getPrioritizedVehicle(DoublyLinkedList<Vehicle> vehicles) {
        if (vehicles.size() == 0) {
            System.out.println("No vehicles available");
            return null;
        }
        try {
            if (vehicles.size() == 1) {
                return vehicles.getFirst().getSelf();
            }

            Node<Vehicle> current = vehicles.getFirst();
            Vehicle prioritizedVehicle = current.getSelf();

            while (current != null) {
                if (current.getSelf().getPriority() > prioritizedVehicle.getPriority()) {
                    prioritizedVehicle = current.getSelf();
                }
                current = current.getNext();
            }
            return prioritizedVehicle;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return null;
    }

    public void operate(String line, DoublyLinkedList<Center> centers) {
        String[] missionCityCombo = line.split("-");

        int sourcePickupCount = Integer.parseInt(missionCityCombo[3]);
        int middlePickupCount = Integer.parseInt(missionCityCombo[4]);

        Center[] cities = new Center[3];
        assignCities(centers, cities, missionCityCombo);

        String[] middleDropOff_String = missionCityCombo[5].split(","); // temp
        int[] indexList_middleDropOff = new int[middleDropOff_String.length];
        for (int i = 0; i < middleDropOff_String.length; i++) {
            indexList_middleDropOff[i] = Integer.parseInt(middleDropOff_String[i]);
        }

        // Sort the drop off indexes
        Arrays.sort(indexList_middleDropOff);


        Vehicle session_vehicle = getPrioritizedVehicle(cities[0].vehicles());

        // Add packages from source city to preferred vehicle in source city
        for (int i = 0; i < sourcePickupCount; i++) {
            session_vehicle.pickUp(cities[0]);
        };

        System.out.println(session_vehicle.cargo().peek().getSelf()); //

        // Add vehicle to middle city
        cities[0].addVehicle(new Node<Vehicle>(session_vehicle));

        // Remove vehicle from source city
        try {

            cities[0].vehicles().removeSpecific(cities[0].vehicles().getNode(session_vehicle));
        } catch (Exception e) {
            System.out.println("COULD NOT REMOVE VEHICLE FROM SOURCE CITY: " + e.getMessage());
        }

        // Drop off packages in middle city
        for (int i = 0; i < indexList_middleDropOff.length; i++) {
            session_vehicle.dropOffIndex(cities[0], (indexList_middleDropOff[i] - i));
        };

        // Load vehicle in middle city
        for (int i = 0; i < middlePickupCount; i++) {
            session_vehicle.pickUp(cities[0]);
        }

        // Add vehicle to destination city
        cities[0].addVehicle(new Node<Vehicle>(session_vehicle));

        // Remove vehicle from middle city
        try {

            cities[0].vehicles().removeSpecific(cities[0].vehicles().getNode(session_vehicle));
        } catch (Exception e) {
            System.out.println("COULD NOT REMOVE VEHICLE FROM MIDDLE CITY: " + e.getMessage());
        }

        // Drop off packages in destination city
        while (session_vehicle.size() > 0) {
            session_vehicle.dropOff(cities[0]);
        }

    }



    public void assignCities(DoublyLinkedList<Center> centers, Center[] cities, String[] missionCityCombo) {
        Node<Center> temp = centers.getFirst();
        for (int i = 0; i < 3; i++) {
            while (temp.getNext() != null) {
                if (temp.getSelf().getName().equals(missionCityCombo[i])) {
                    cities[i] = temp.getSelf();
                    break;
                }
                temp = temp.getNext();
            }
        }
    }

    public void removeFromCity(Center city, Vehicle vehicle) {
        try {
            city.vehicles().removeSpecific(city.vehicles().getNode(vehicle));
        } catch (Exception e) {
            System.out.println("COULD NOT REMOVE VEHICLE FROM CITY: " + e.getMessage());
        }
    }
}
