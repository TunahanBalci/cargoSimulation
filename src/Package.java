
public class Package<KKK> {

    private KKK data;

    private Package<KKK> next;
    private Package<KKK> prev;

    public Package(KKK data){
        this.data = data;
    }

    public void setData(KKK data){
        this.data = data;
    }
    public KKK getData(){
        return data;
    }

    public void setNext(Package<KKK> next){
        this.next = next;
    }
    public Package<KKK> getNext(){
        return next;
    }
    public void setPrev(Package<KKK> prev){
        this.prev = prev;
    }
    public Package<KKK> getPrev(){
        return prev;
    }
}
