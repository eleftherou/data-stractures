import java.lang.Comparable;

public class Disk implements Comparable<Disk>{


    private int id;
    private int storage;

    ListInterface<Integer> folders = new List<>();

    public Disk(int id) {
        this.id = id;
        storage = 1000000;
    }


    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getId() {
        return id;
    }


    public int getFreeSpace() {
        return storage;
    }

    public int compareTo(Disk x) {//χρειαζεται στην ουρα προτεραιοτητας
        if (this.getFreeSpace() > x.getFreeSpace())
            return 1;
        else if (this.getFreeSpace() < x.getFreeSpace())
            return -1;
        else
            return 0;
    }
}
