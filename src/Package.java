
public class Package<CUSTOM_TYPE> {

    private CUSTOM_TYPE data;

    private Package<CUSTOM_TYPE> next;
    private Package<CUSTOM_TYPE> prev;

    public Package(CUSTOM_TYPE data){
        this.data = data;
    }

    public void setData(CUSTOM_TYPE data){
        this.data = data;
    }
    public CUSTOM_TYPE getData(){
        return data;
    }

    public void setNext(Package<CUSTOM_TYPE> next){
        this.next = next;
    }
    public Package<CUSTOM_TYPE> getNext(){
        return next;
    }
    public void setPrev(Package<CUSTOM_TYPE> prev){
        this.prev = prev;
    }
    public Package<CUSTOM_TYPE> getPrev(){
        return prev;
    }
}
