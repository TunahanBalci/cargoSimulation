public class Queue<DATA_TYPE> extends DoublyLinkedList<DATA_TYPE>{

    public void enqueue(DATA_TYPE data){
        addToBack(data);
    }

    public DATA_TYPE dequeue(){
        return removeFromFront();
    }

    public DATA_TYPE removeIndex(int index){
        return super.removeIndex(index);
    }

    public DATA_TYPE get(int index){
        return super.get(size() - 1 - index);
    }
}
