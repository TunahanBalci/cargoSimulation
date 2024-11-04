public class Vehicle{

    private String name;
    private double priority;

    private Vehicle next;
    private Vehicle prev;

    private DoublyLinkedList<Package> cargo = new DoublyLinkedList<>();

    Vehicle (String name, double priority){
        this.name = name;
        this.priority = priority;
    }
    public void setNext(Vehicle next){
        this.next = next;
    }
    public Vehicle getNext(){
        return next;
    }
    public void setPrev(Vehicle prev){
        this.prev = prev;
    }
    public Vehicle getPrev(){
        return prev;
    }
    public double getPriority(){
        return priority;
    }
    public void setPriority(double priority){
        this.priority = priority;
    }
    public void pickUp(Center center){
        cargo.push(center.packages().pop());
    }
    public void dropOff(Center center){
        center.addPackage(cargo.pop());
    }

    public void dropOffIndex(Center center, int index){

        center.addPackage(cargo.get(index));
//        System.out.println("Vehicle/DropOffIndex: INDEX: " + index);
        cargo.remove(index);
    }
    public String getName(){
        return name;
    }
    public int size(){
        return cargo.size();
    }
    public DoublyLinkedList cargo(){
        return cargo;
    }
}

