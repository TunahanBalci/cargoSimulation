public class CityList {

    public Center source;
    public Center destination;
    private int size = 0;

    public CityList(Center center) {
        this.source = center;
        this.destination = center;
        this.size = 1;

    }

    public void addToBack(Center c) {

        try {
            destination.setNext(c);
            c.setPrev(destination);
            destination = c;
            if (size == 0) {
                destination = c;
            } else if (size == 1) {
                source.setNext(c);
            }
            size++;

        } catch (Exception e) {
            System.out.println("ERROR: Couldn't add center: " + e.getMessage());
        }
    }

    public void addToFront(Center c) {
        try {
            source.setPrev(c);
            c.setNext(source);
            source = c;
            if (size == 0) {
                destination = c;
            } else if (size == 1) {
                destination.setPrev(c);
            }
            size++;

        } catch (Exception e) {
            System.out.println("ERROR: Couldn't add center: " + e.getMessage());
        }
    }

    public void addAfter(Center c, Center anchor){

        try {
            if (anchor == source || anchor == destination){
                addToBack(c);
            } else {

                Center temp = source;

                for (int i = 0; i < size; i++){
                    if (temp.getPrev() == anchor){
                        temp.setPrev(c);
                        c.setNext(temp);
                        c.setPrev(anchor);
                        break;
                    }
                    temp = temp.getNext();
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: Couldn't add center: " + e.getMessage());
        }
    }

    public Center getSource(){
        return source;
    }

    public Center getDestination(){
        return destination;
    }
}

