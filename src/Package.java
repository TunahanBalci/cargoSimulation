public class Package<DATA_TYPE> {

    private DATA_TYPE data;
    public Package(DATA_TYPE name){
        this.data = name;
    }
    public DATA_TYPE data(){
        return data;
    }

    @Override
    public String toString(){
        return data.toString();
    }
}
