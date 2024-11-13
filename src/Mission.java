public class Mission {

    private City sourceCity;
    private City middleCity;
    private City destinationCity;
    private int sourcePickup;
    private int middlePickup;
    private int [] middleDrop;

    Mission(City sourceCity, City middleCity, City destinationCity, int sourcePickup, int middlePickup, int [] middleDrop){
        this.sourceCity = sourceCity;
        this.middleCity = middleCity;
        this.destinationCity = destinationCity;
        this.sourcePickup = sourcePickup;
        this.middlePickup = middlePickup;
        this.middleDrop = middleDrop;
    }

    public void execute(){

        Vehicle vehicle = sourceCity.getPriortyVehicle();;

        vehicle.pickUp(sourceCity, sourcePickup);

        sourceCity.removeVehicle(vehicle);

        middleCity.addVehicle(vehicle);
//        middleCity.debug(); //DEBUG

        City tempCity = new City("TEMP");

        vehicle.deliverIndices(tempCity, middleDrop); // delivers to tempcity
        vehicle.pickUp(middleCity, middlePickup);

        while (!tempCity.packages().isEmpty()){
            Package p = tempCity.packages().removeFromBack();
//            System.out.println("TRANSFERING " + p + " FROM TEMP TO MIDDLE"); //DEBUG
            middleCity.packages().push(p);
        }
        middleCity.removeVehicle(vehicle);

        destinationCity.addVehicle(vehicle);
        vehicle.deliverAll(destinationCity);

        if (Tester.DEBUG_MISSION_INFO){
            Utils.printInfo(" --- MISSION ENDED ---");
        }

//        sourceCity.debug(); //DEBUG
//        middleCity.debug(); //DEBUG
//        destinationCity.debug(); //DEBUG
    }

    @Override
    public String toString(){
        String middleDropString = "[";
        for (int i = 0; i < middleDrop.length; i++){
            if (i == middleDrop.length - 1){
                middleDropString += middleDrop[i] + "]";
                break;
            }
            middleDropString += middleDrop[i] + ",";
        }
        return sourceCity.name() + " - " + middleCity.name() + " - " + destinationCity.name() + " - " + sourcePickup + " - " + middlePickup + " - " + middleDropString;
    }
}
