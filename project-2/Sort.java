public class Sort { //ταξινομει τη λιστα //ενημερωνει μονο το node και μετα ο καθε κομβος δειχνει στον αμεσως μεγαλυτερο.
    public static Node mergesort(Node c) {
            if (c == null || c.getNext() == null)
                return c;
            Node a = c;
            Node b = c.getNext();
            while ((b != null) && (b.getNext() != null)) {
                c = c.getNext();
                b = b.getNext().getNext();
            }
            b = c.getNext();
            c.setNext(null);
            return  merge(mergesort(a), mergesort(b));
    }

    public static Node merge(Node a, Node b) {
        Node temp = new Node();
        Node head = temp;
        Node c = head;
        while ((a != null) && (b != null)) {
            if (a.getData() > b.getData()) {
                c.setNext(a);
                c = a;
                a = a.getNext();
            } else {
                c.setNext(b);
                c = b;
                b = b.getNext();
            }
        }
        c.setNext(a == null ? b : a);
        return head.getNext();
    }
}

