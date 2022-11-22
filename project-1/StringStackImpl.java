import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl implements StringStack {

    private Node head = null;
    int counter = 0;

    public StringStackImpl() {}

    public boolean isEmpty() {
        return head == null;
    }

    public void push(String item){
        head = new Node(item, head);
        counter++;
    }

    public String pop() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            String p = head.getItem();
            if (head.getNextNode()== null) {
                head = null;
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

    public void printStack(PrintStream stream) {
        if (isEmpty()) {
            stream.println(("The stack is empty!\n"));
        } else {
            Node currentNode = head;
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
