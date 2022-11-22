import java.io.*;
import java.util.*;



public class TwoDTree {

    private class TreeNode {

        Point item;
        TreeNode l;
        TreeNode r;
        String cord;
        Rectangle rec;

        public TreeNode(Point item) {
            this.item = item;
        }



        public Point getItem() {
            return item;
        }

        public TreeNode getL() {
            return l;
        }

        public TreeNode getR() {
            return r;
        }

        public void setL(TreeNode l) {
            this.l = l;
        }

        public void setR(TreeNode r) {
            this.r = r;
        }
    };

    private TreeNode head,item,l,r;
    private int counter,xmin,xmax,ymin,ymax;
    private String cord;
    private Rectangle rec;


    public TwoDTree() {
        item = null;
        l = null;
        r = null;
        cord = null;
        rec = null;

    }


    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return counter;
    }

    public void insert(Point p) {



        if (head == null) {
            head = new TreeNode(p);
            head.cord = "X";
            int xmin = 0, xmax = 100, ymin = 0, ymax = 100;
            head.rec = new Rectangle(xmin, xmax, ymin, ymax);
            counter++;
            return;
        }

        TreeNode current = head;



            while (true) {

                if (current.getItem().x()== p.x() && current.getItem().y() == p.y()) {
                    System.out.println("The point already exists");
                    return;
                }


                if (current.cord.equals("X")) {
                    if (current.getItem().x() > p.x() ) {
                        if (current.getL() == null) {
                            current.setL(new TreeNode(p));
                            current.getL().cord = "Y";
                            current.getL().rec = new Rectangle(current.rec.xmin(), current.item.x(), current.rec.ymin(), current.rec.ymax()); //create the rectangle that belongs at the node
                            counter++;
                            return;
                        } else {
                            current = current.getL();
                        }
                    } else {
                        if (current.getR() == null) {
                            current.setR(new TreeNode(p));
                            current.getR().cord = "Y";
                            current.getR().rec = new Rectangle(current.item.x(),  current.rec.xmax(), current.rec.ymin(), current.rec.ymax());
                            counter++;
                            return;
                        } else {
                            current = current.getR();
                        }

                    }


                } else {
                    if (current.getItem().y() > p.y()) {
                        if (current.getL() == null) {
                            current.setL(new TreeNode(p));
                            current.getL().cord = "X";
                            current.getL().rec = new Rectangle(current.rec.xmin(), current.rec.xmax(), current.rec.ymin(), current.item.y());
                            counter++;
                            return;
                        } else {
                            current = current.getL();
                        }

                    } else {
                        if (current.getR() == null) {
                            current.setR(new TreeNode(p));
                            current.getR().cord = "X";
                            current.getR().rec = new Rectangle(current.rec.xmin(), current.rec.xmax(), current.item.y(), current.rec.ymax());
                            counter++;
                            return;
                        } else {
                            current = current.getR();
                        }

                    }

                }
            }




    }

    public boolean search(Point p) {
        TreeNode current = head;


        while (current != null) {

                if (current.getItem().x() == p.x()  && current.getItem().y() == p.y())
                    return true;

                if (current.cord.equals("X")) {
                    if (p.x() > current.getItem().x()) {
                        current = current.getR();
                    } else {
                        current = current.getL();
                    }


                } else {
                    if (p.y() > current.getItem().y()) {
                        current = current.getR();
                    } else {
                        current = current.getL();
                    }

                }
        }
        return false;
    }




    public Point nearestNeighbor(Point p) throws EmptyListException {
        if (isEmpty()) {
            return null;
        }

        List treeList = new List();

        TreeNode current = head;


        while (current != null) {
            treeList.insert(current.getItem());

            if (current.cord.equals("X")) {
                if (p.x() > current.getItem().x()) {
                    current = current.getR();
                } else {
                    current = current.getL();
                }


            } else {
                if (p.y() > current.getItem().y()) {
                    current = current.getR();
                } else {
                    current = current.getL();
                }

            }
        }
        double distance = treeList.peek().squareDistanceTo(p);
        Point nearestPoint = treeList.remove();

        for (int i = 0; i < treeList.size()+1; i++) {
            if (distance > treeList.peek().squareDistanceTo(p)) {
                distance = treeList.peek().squareDistanceTo(p);
                nearestPoint =  treeList.remove();

            }
        }

        System.out.println("The nearest distance is: " + nearestPoint.distanceTo(p) );
        return nearestPoint;
    }


/*

    public Point nearestNeighbor(Point p) { //the method finds the nearest point but eventually returns the point that was given as a parameter at the first call of the recursive function
        if (head == null) {
            return null;
        } else {
            Point nearestPoint = head.getItem();
            double minDistance = head.getItem().distanceTo(p);

            return nearestNeighborRecursive(head, p ,minDistance, nearestPoint);

        }


    }



    public Point nearestNeighborRecursive(TreeNode treeNode, Point p, double minDistance,Point nearestPoint){

            if (treeNode == null) {
                return null;
            }


            if (treeNode.getItem().distanceTo(p) < minDistance) {
                nearestPoint = treeNode.getItem();
                minDistance = treeNode.getItem().distanceTo(p);
            }



            if (treeNode.cord.equals("X")) {
                    if (p.x() - treeNode.getItem().x() < 0) {
                        nearestNeighborRecursive(treeNode.getL(), p, minDistance, nearestPoint );
                        if (treeNode.getR() != null) {
                            if (minDistance > treeNode.getR().rec.squareDistanceTo(p))
                                nearestNeighborRecursive(treeNode.getR(), p, minDistance, nearestPoint );
                        }
                    } else if (p.x() - treeNode.getItem().x() > 0) {
                        nearestNeighborRecursive(treeNode.getR(), p, minDistance,nearestPoint );
                        if (treeNode.getL() != null) {
                            if (minDistance > treeNode.getL().rec.squareDistanceTo(p))
                                nearestNeighborRecursive(treeNode.getL(), p, minDistance, nearestPoint );
                        }
                    } else if (p.y() != treeNode.getItem().y()) {
                        nearestNeighborRecursive(treeNode.getR(), p, minDistance,nearestPoint );
                        if (treeNode.getL() != null) {
                            if (minDistance > treeNode.getL().rec.squareDistanceTo(p))
                                nearestNeighborRecursive(treeNode.getL(), p, minDistance, nearestPoint );
                        }
                    }
                } else {
                    if (p.y() - treeNode.getItem().y() < 0) {
                        nearestNeighborRecursive(treeNode.getL(), p, minDistance, nearestPoint );
                        if (treeNode.getR() != null) {
                            if (minDistance > treeNode.getR().rec.squareDistanceTo(p))
                                nearestNeighborRecursive(treeNode.getR(), p, minDistance, nearestPoint );
                        }
                    } else if (p.y() - treeNode.getItem().y() > 0) {
                        nearestNeighborRecursive(treeNode.getR(), p, minDistance,nearestPoint );
                        if (treeNode.getL() != null) {
                            if (minDistance > treeNode.getL().rec.squareDistanceTo(p))
                                nearestNeighborRecursive(treeNode.getL(), p, minDistance, nearestPoint );
                        }
                    } else if (p.x() != treeNode.getItem().x()) {
                        nearestNeighborRecursive(treeNode.getR(), p, minDistance, nearestPoint );
                        if (treeNode.getL() != null) {
                            if (minDistance > treeNode.getL().rec.squareDistanceTo(p))
                                nearestNeighborRecursive(treeNode.getL(), p, minDistance, nearestPoint );
                        }
                    }

                }

            System.out.println(nearestPoint);

           return nearestPoint;

    }

*/
    public List<Point> rangeSearch(Rectangle rect) {
        List<Point> treeList = new List<>();

        if (head != null) {
            treeList = rangeSearchRecursive(head,rect, treeList);
        }


        return treeList;
    }

    public List<Point> rangeSearchRecursive(TreeNode treeNode, Rectangle rect, List<Point> treeList) {

        if (treeNode.rec.intersects(rect)) {
            if (rect.contains(treeNode.getItem())){
                treeList.insert(treeNode.getItem());
            }
            if (treeNode.getL() != null) {
                rangeSearchRecursive(treeNode.getL(), rect, treeList);
            }
            if (treeNode.getR() != null) {
                rangeSearchRecursive(treeNode.getR(), rect, treeList);
            }
        }



        return treeList;
    }


    public static void main(String[] args) throws EmptyListException {

        String filename = args[0];

        BufferedReader reader = null;
        String line;
        TwoDTree tree = new TwoDTree();

        try {
            reader = new BufferedReader(new FileReader(new File(filename)));
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }

        try {
            line = reader.readLine();
            int N = Integer.parseInt(line);

            line = reader.readLine();

            for (int i = 0; i < N; i++) {
                if (line == null) {
                    System.out.println("Wrong, there are less points than it is referred");
                    return;
                }
                String[] input = line.split(" ");
                if (Integer.parseInt(input[0]) > 100 ||Integer.parseInt(input[1]) >100) {
                    System.out.println("The given coordinate is bigger than 100");
                    return;
                }
                tree.insert(new Point(Integer.parseInt(input[0]),Integer.parseInt(input[1])));
                line = reader.readLine();
            }

            if (line != null) {
                System.out.println("Wrong, there are more points than it is referred");
                return;
            }

            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("Error closing file");
            }
        } catch (IOException e) {
            System.err.println("Error reading line");
        }

        Scanner in = new Scanner(System.in);
        String answer;
        boolean done = false;

        while (!done) {
            System.out.println("\n1. Compute the size of the tree");
            System.out.println("2. Insert a new point");
            System.out.println("3. Search if a given point exists in the tree");
            System.out.println("4. Provide a query rectangle");
            System.out.println("5. Provide a query point");
            System.out.println("0. Exit");
            answer = in.nextLine();
            if (answer.equals("1")) {
                System.out.println("The size of the tree is: " + tree.size());
            } else if (answer.equals("2")) {
                System.out.println("Give the x coordinate of the new point");
                answer = in.nextLine();
                int x = Integer.parseInt(answer);
                if (x > 100) {
                    System.out.println("Coordinate is bigger than 100");
                    break;
                }
                System.out.println("Give the y coordinate of the new point");
                answer = in.nextLine();
                int y = Integer.parseInt(answer);
                if ( y > 100) {
                    System.out.println("Coordinate is bigger than 100");
                    break;
                }
                tree.insert(new Point(x,y));
            } else if (answer.equals("3")) {
                System.out.println("Give the x coordinate of the point");
                answer = in.nextLine();
                int x = Integer.parseInt(answer);
                if (x > 100) {
                    System.out.println("Coordinate is bigger than 100");
                    break;
                }
                System.out.println("Give the y coordinate of the point");
                answer = in.nextLine();
                int y = Integer.parseInt(answer);
                if (y > 100) {
                    System.out.println("Coordinate is bigger than 100");
                    break;
                }
                Point point = new Point(x,y);
                if (tree.search(point)) {
                    System.out.println("Point " + point + " exists in the tree");
                } else {
                    System.out.println("Point " + point + " does not exists in the tree");
                }
            } else if (answer.equals("4")) {
                System.out.println("Give the coordinates of the rectangle");
                answer = in.nextLine();
                String[] inputX = answer.split(" ");
                int xmin = Integer.parseInt(inputX[0]);
                if (xmin > 100) {
                    System.out.println("Coordinate is bigger than 100");
                    break;
                }
                int xmax = Integer.parseInt(inputX[1]);
                if (xmax > 100) {
                    System.out.println("Coordinate is bigger than 100");
                    break;
                }
                answer = in.nextLine();
                String[] inputY = answer.split(" ");
                int ymin = Integer.parseInt(inputY[0]);
                if (ymin > 100) {
                    System.out.println("Coordinate is bigger than 100");
                    break;
                }
                int ymax = Integer.parseInt(inputY[1]);
                if (ymax > 100) {
                    System.out.println("Coordinate is bigger than 100");
                    break;
                }

                Rectangle rectangle = new Rectangle(xmin,xmax,ymin,ymax);

                System.out.println("Rectangle " + rectangle.toString() + "contains: ");
                tree.rangeSearch(rectangle).printList();

            } else if (answer.equals("5")) {
                System.out.println("Give a query point");
                answer = in.nextLine();
                String[] input = answer.split(" ");
                int x = Integer.parseInt(input[0]);
                if (x > 100) {
                    System.out.println("Coordinate is bigger than 100");
                    break;
                }
                int y = Integer.parseInt(input[1]);
                if (y > 100) {
                    System.out.println("Coordinate is bigger than 100");
                    break;
                }

                Point point = new Point(x,y);


                System.out.print("The nearest point is: " + tree.nearestNeighbor(point));
            } else {
                done = true;
            }
        }

    }
}

