import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringQueueImpl implements StringQueue {

    private Node head = null;
    private Node tail = null;
    int counter = 0;

    public boolean isEmpty() {
        return head == null;
    }

    public void put(String item) {
        Node t = tail;
        tail = new Node(item);
        if (isEmpty()) {
            head = tail;
        } else {
            t.setNextNode(tail);
        }
        counter++;
    }

    public String get() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            String p = head.getItem();
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.getNextNode();
            }
            counter--;
            return p;
        }
    }

    public String peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            String p = head.getItem();
            return p;
        }
    }

    public void printQueue(PrintStream stream) {
        if (isEmpty()) {
            stream.println(("The queue is empty!\n"));
        } else {
            Node currentNode = head.getNextNode();
            while (currentNode != null) {
                stream.println(currentNode.getItem());
                currentNode = currentNode.getNextNode();
            }
        }
    }

    public int size() {
        return counter;
    }

}
