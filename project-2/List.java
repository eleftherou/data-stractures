public class List<T> implements ListInterface<T> {

    private Node head = null;
    private Node tail = null;
    private int counter = 0;


    public List() {
    }


    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return head == null;
    }



    public void insert(int data) {
        Node n = new Node(data);

        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            tail.setNext(n);
            tail = n;
        }
        counter++;
    }


    public int remove() throws EmptyListException {
        if (isEmpty())
            throw new EmptyListException();

        int data = head.getData();

        if (size() == 1) { //only one value left in the list

            head = tail = null;
        } else
            head = head.getNext();

        counter--;
        return data;
    }

    public int peek() throws EmptyListException {
        if (isEmpty())
            throw new EmptyListException();

        int data = head.getData();

        return data;
    }


    public int size() {
        return counter;
    }




    public void printList() {
        if (isEmpty()) {
            System.out.println("The list is empty");
        } else {

            Node current = head;

            while (current != null) {
                System.out.print(current.getData() + " ");

                current = current.getNext();
            }
        }
    }

    public ListInterface<Integer> copyList() {
        ListInterface<Integer> newList = new List<>();
        Node current = head;

        while (current != null) {
            Node newNode = new Node(current.getData());
            newList.insert(newNode.getData());
            current = current.getNext();
       }
        return  newList;
    }

}
