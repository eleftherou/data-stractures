public class MaxPQ implements MaxPQInterface{ //ΟΥΡΑ ΠΡΟΤΕΡΑΙΟΤΗΤΑΣ



    private Disk[] heap; // the heap to store data in
    private int size; // current size of the queue
    private Comparable comparable; // the comparator to use between the objects

    private static final int DEFAULT_CAPACITY = 4; // default capacity
    private static final int AUTOGROW_SIZE = 4; // default auto grow


    public MaxPQ(Comparable comparable) {
        this.heap = new Disk[DEFAULT_CAPACITY + 1];
        this.size = 0;
        this.comparable = comparable;
    }

    public Disk[] getHeap() {
        return heap;
    }


    public void add(Disk item) {
        if (size == heap.length-1)
            grow();
        heap[++size] = item;
        swim(size);
    }


    public Disk peek() {
        if (size == 0)
            return  null;
        return heap[1];
    }

    public Disk getMax() {
        if (size == 0)
            return null;
        Disk root = heap[1];
        heap[1] = heap[size];
        size--;
        sink(1);
        return root;
    }



    private void swim(int i) {
        while (i > 1) {
            int p = i/2;
            if( heap[i].compareTo(heap[p]) <= 0)
                return;
            swap(i, p);
            i = p;
        }
    }


    private void sink(int i){
        int left = 2*i, right = left+1, max = left;

        while (left <= size) {

            if (right <= size) {
                max = heap[left].compareTo(heap[right]) < 0 ? right : left;
            }

            if (heap[i].compareTo(heap[max]) >= 0) return;
            swap(i, max);
            i = max; left = 2*i; right = left+1; max = left;
        }
    }


    private void swap(int i, int j) {
        Disk tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }


    private void grow() {
        Disk[] newHeap = new Disk[heap.length + AUTOGROW_SIZE];

        System.arraycopy(heap, 1, newHeap, 1, size);

        heap = newHeap;
    }

}
