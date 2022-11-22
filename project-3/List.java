public class List<T>  {

    private class Node {
        protected Point data;
        protected Node next = null;


        Node(Point data) {
            this.data = data;
        }



        public Point getData() {

            return data;
        }


        public Node getNext() {

            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    };

    private Node head = null;
    private Node tail = null;
    private int counter = 0;


    public List() {
    }


    public boolean isEmpty() {
        return head == null;
    }


    public void insert(Point data) {
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


    public Point remove() throws EmptyListException {
        if (isEmpty())
            throw new EmptyListException();

        Point data = head.getData();

        if (size() == 1) { //only one value left in the list

            head = tail = null;
        } else
            head = head.getNext();

        counter--;
        return data;
    }

    public Point peek() throws EmptyListException {
        if (isEmpty())
            throw new EmptyListException();

        Point data = head.getData();

        return data;
    }


    public int size() {
        return counter;
    }




    public void printList() {
        if (isEmpty()) {
            System.out.println("0");
        } else {

            Node current = head;

            while (current != null) {
                System.out.print(current.getData() + " ");

                current = current.getNext();
            }
        }
    }
    

}
