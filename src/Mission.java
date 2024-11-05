import java.util.Arrays;

public class Mission {

    public static Vehicle getPrioritizedVehicle(DoublyLinkedList<Vehicle> vehicles) {
        if (vehicles.size() == 0) {
            System.out.println("ERROR: NO VEHICLE");
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

        String[] middleDropOff_String = missionCityCombo[5].split(",");
        int[] indexList_middleDropOff = new int[middleDropOff_String.length];
        for (int i = 0; i < middleDropOff_String.length; i++) {
            indexList_middleDropOff[i] = Integer.parseInt(middleDropOff_String[i]);
        }

        // Sort the drop-off indexes
        Arrays.sort(indexList_middleDropOff);

        Vehicle session_vehicle = getPrioritizedVehicle(cities[0].vehicles());

        // Add packages from source city to preferred vehicle in source city
        for (int i = 0; i < sourcePickupCount; i++) {
            session_vehicle.pickUp(cities[0]);
        }

        System.out.println(session_vehicle.cargo().peek().getSelf());

        // Add vehicle to middle city
        cities[1].addVehicle(new Node<Vehicle>(session_vehicle));

        // Remove vehicle from source city
        try {
            cities[0].vehicles().remove(cities[0].vehicles().indexOf(session_vehicle));
        } catch (Exception e) {
            System.out.println("COULD NOT REMOVE VEHICLE FROM SOURCE CITY: " + e.getMessage());
        }

        // Drop off packages in a temporary city to avoid concurrent modification
        Center tempCity = new Center("temp");
        System.out.println("Middle dropoff count: " + indexList_middleDropOff.length);
        for (int i = 0; i < indexList_middleDropOff.length; i++) {
            int adjustedIndex = indexList_middleDropOff[i] - i;
            session_vehicle.dropOffIndex(tempCity, adjustedIndex);
        }

        // Load vehicle in middle city
        System.out.println("Middle pickup count: " + middlePickupCount);
        for (int i = 0; i < middlePickupCount; i++) {
            session_vehicle.pickUp(cities[1]);
        }

        // Transfer packages from temp city to middle city
        for (int i = 0; i < indexList_middleDropOff.length; i++) {
            cities[1].packages().addFirst(tempCity.packages().pop());
        }

        // Add vehicle to destination city
        cities[2].addVehicle(new Node<Vehicle>(session_vehicle));

        // Remove vehicle from middle city
        try {
            cities[1].vehicles().remove(cities[1].vehicles().indexOf(session_vehicle));
        } catch (Exception e) {
            System.out.println("COULD NOT REMOVE VEHICLE FROM MIDDLE CITY: " + e.getMessage());
        }

        // Drop off packages in destination city
        while (session_vehicle.size() > 0) {
            session_vehicle.dropOff(cities[2]);
        }
    }

    public void assignCities(DoublyLinkedList<Center> centers, Center[] cities, String[] missionCityCombo) {
        Node<Center> temp = centers.getFirst();
        for (int i = 0; i < 3; i++) {
            while (temp != null) {
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
            city.vehicles().remove(city.vehicles().indexOf(vehicle));
        } catch (Exception e) {
            System.out.println("COULD NOT REMOVE VEHICLE FROM CITY: " + e.getMessage());
        }
    }
}
