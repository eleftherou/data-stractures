
public interface ListInterface<T> {


    Node getHead();

    void setHead(Node head);


    void insert(int data);

    int remove() throws EmptyListException;

    int peek() throws EmptyListException;

    int size();

    ListInterface<Integer> copyList();

    void printList();

    boolean isEmpty();


}
