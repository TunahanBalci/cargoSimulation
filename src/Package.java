
public class Package<X> {

    private X data;

    private Package<X> next;
    private Package<X> prev;

    public Package(X data){
        this.data = data;
    }

    public void setData(X data){
        this.data = data;
    }
    public X getData(){
        return data;
    }

    public void setNext(Package<X> next){
        this.next = next;
    }
    public Package<X> getNext(){
        return next;
    }
    public void setPrev(Package<X> prev){
        this.prev = prev;
    }
    public Package<X> getPrev(){
        return prev;
    }
}
