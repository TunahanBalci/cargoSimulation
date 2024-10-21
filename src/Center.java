public class Center {

    private Center prev;
    private Center next;

    private String name;

    public Center(String name) {
        this.name = name;
    }

    public Center getNext(){
        return this.next;
    }
    public Center getPrev(){
        return this.prev;
    }
    public void setNext(Center c){
        this.next = c;
    }
    public void setPrev(Center c){
        this.prev = c;
    }

    public String getName(){
        try {
            return name;
        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }
    public void setName(String name){
        this.name = name;
    }
}
