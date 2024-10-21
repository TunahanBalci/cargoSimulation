public class Package {

    private Center center;
    private String content;

    private Package next;
    private Package prev;


    public Package(Center center, String content) {
        this.center = center;
        this.content = content;
    }

    public Package getNext(){
        return this.next;
    }
    public Package getPrev(){
        return this.prev;
    }
    public void setNext(Package p){
        this.next = p;
    }
    public void setPrev(Package p){
        this.prev = p;
    }
    public Center getCity(){
        return center;
    }
    public void setCity(Center center) {
        this.center = center;
    }
    public String getContent(){
        try {
            return content;
        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }
    public void setContent(String content){
        this.content = content;
    }
}
