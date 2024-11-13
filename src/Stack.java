public class Stack<DATA_TYPE> extends DoublyLinkedList<DATA_TYPE>{

    @Override
    public void push(DATA_TYPE data){
        addToFront(data);
    }

    @Override
    public DATA_TYPE pop(){
        return removeFromFront();
    }

    @Override
    public DATA_TYPE peek(){
        return getFirst();
    }

    @Override
    public DATA_TYPE removeIndex(int index){
        return super.removeIndex(index);
    }


    public Stack copy(){
        Stack copy = new Stack();
        for (int i = 0; i < size(); i++){
            copy.push(get(i));
        }
        return copy;
    }
}
