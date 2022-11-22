public class Node {
    protected int data;
    protected Node next = null;


    Node(int data) {
        this.data = data;
    }

    Node() {}


    public int getData() {

        return data;
    }


    public Node getNext() {

        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
