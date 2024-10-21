public class Vehicle {

    private Package firstPackage;
    private Package lastPackage;
    public Center center;
    private int size = 0;

    public Vehicle(Center center, Package firstPackage) {
        this.center = center;

        this.firstPackage = firstPackage;
        this.lastPackage = firstPackage;

        if (firstPackage != null){
            this.size = 1;
        } else {
            this.size = 0;
        }
    }

    public void push(Package p){
        try {
            if (size == 0){
                firstPackage = p;
            } else if (size == 1){
                firstPackage.setNext(p);
                lastPackage = p;
                lastPackage.setPrev(firstPackage);
                size++;
                return;
            }
            lastPackage.setNext(p);
            p.setPrev(lastPackage);
            lastPackage = p;
            size++;
        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public Package pop(){
        try {

            Package temp = lastPackage;
            if (size == 0){
                return null;
            } else if (size == 1){
                lastPackage = null;
                firstPackage = null;
                size--;
                return temp;
            } else {
                lastPackage = lastPackage.getPrev();
                lastPackage.setNext(null);
                size--;
                return temp;
            }
        } catch (Exception e){
            System.out.println("ERROR at pop(): " + e.getMessage());
            return null;
        }
    }

    public Package peek(){
        return lastPackage;
    }
    public int size(){
        return size;
    }

}

