public class Node {

    private String item;
    private Node nextNode;

    public  Node (String item, Node nextNode) {
        this.item = item;
        this.nextNode = nextNode;
    }

    public  Node (String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }


}
