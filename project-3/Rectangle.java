public class Rectangle {

    private int xmin,xmax,ymin,ymax;

    public Rectangle(int xmin, int xmax, int ymin, int ymax) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }

    public int xmin() {return xmin;}
    public int ymin()  {return ymin;}

    public int xmax() {return xmax;}

    public int ymax() {return ymax;}

    public boolean contains(Point p) {
        if (p.x() >= xmin && p.x() <= xmax && p.y() >= ymin && p.y() <= ymax)
            return true;
        else
            return false;
    }

    public boolean intersects(Rectangle that) {
        Point A = new Point(that.xmin,that.ymin);
        Point B = new Point(that.xmin,that.ymax);
        Point C = new Point(that.xmax,that.ymax);
        Point D = new Point(that.xmax,that.ymin);
        if (this.contains(A) || this.contains(B) || this.contains(C) || this.contains(D))
            return true;
        else
            return false;
    }


    public double distanceTo(Point p) {

        if (contains(p)) {
            return 0;
        } else {
            Point temp = null;
            if (p.x() < xmin && p.y() >= ymin && p.y() <= ymax) {
                 temp = new Point(xmin, p.y());
            } else if (p.y() < ymin && p.x() >= xmin && p.x() <= xmax) {
                 temp = new Point(p.x(), ymin);
            } else if (p.x() > xmax && p.y() >= ymin && p.y() <= ymax) {
                 temp = new Point(xmax, p.y());
            } else if (p.y() > ymax && p.x() >= xmin && p.x() <= xmax) {
                 temp = new Point(p.x(), ymax);
            } else if (p.x() < xmin && p.y() < ymin) {
                 temp = new Point(xmin, ymin);
            } else if (p.x() > xmax && p.y() < ymin) {
                 temp = new Point(xmax, ymin);
            } else if (p.x() < xmin && p.y() > ymax) {
                 temp = new Point(xmin, ymax);
            } else if (p.x() > xmax && p.y() > ymax) {
                 temp = new Point(xmax, ymax);
            }
            return p.distanceTo(temp);
        }
    }

    public int squareDistanceTo(Point p) {
        return (int)(this.distanceTo(p)*this.distanceTo(p));
    }
    public String toString() {
        return "[" + xmin + ", " + xmax + "] X [" + ymin + ", " + ymax + "]";
    }


}
