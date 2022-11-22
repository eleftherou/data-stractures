import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringQueueWithOnePointer implements StringQueue {

    private Node last = null;
    int counter = 0;

    public boolean isEmpty() {
       return counter == 0;
    }

    public void put(String item) {
        Node n = new Node(item); //ν κομβος που εισαγω
        if ( isEmpty()) {
            last = n; 
            last.setNextNode(n); 
        } else {
            n.setNextNode(last.getNextNode());
            last.setNextNode(n);
            last = n;
        }
        counter++;

    }

    public String get() throws NoSuchElementException {
        if ( isEmpty()) {
            throw new NoSuchElementException();
        } else {
            String p = last.getNextNode().getItem();
            last.setNextNode(last.getNextNode().getNextNode());
            counter--;
            return p;
        }
    }

    public String peek() {
        if ( isEmpty()) {
            throw new NoSuchElementException();
        } else {
            String p = last.getNextNode().getItem();
            return p;
        }
    }

    public void printQueue(PrintStream stream) {
        if (isEmpty()) {
            stream.println(("The queue is empty!\n"));
        } else {
            Node currentNode = last.getNextNode();
            for (int i = 0; i < counter; i++) {
                stream.println(currentNode.getItem());
                currentNode = currentNode.getNextNode();
            }
        }
    }

    public int size() {
        return counter;
    }

}
