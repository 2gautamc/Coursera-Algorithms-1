import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF UF;
    private int size;
    private boolean[] isOpen;
    private int countOpen;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throwExcept("IllegalArgument", "Size cannot be less than 0.");
        size = n;
        UF = new WeightedQuickUnionUF(n * n + 2);
        isOpen = new boolean[n * n + 2];
        for (int i = 1; i < n * n; i++) isOpen[i] = false;
        isOpen[0] = true;
        isOpen[n * n + 1] = true;
        countOpen = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!validate(row, col))
            throwExcept("IllegalArgument", "Invalid value for row or column.");
        int id = getIndex(row, col);
        if (isOpen[id]) return;
        else {
            for (int neighbour : getNeighbours(id)) {
                if (neighbour != -1) {
                    int[] rowcol = getRowCol(neighbour);
                    if (isOpen(rowcol[0], rowcol[1]))
                        UF.union(id, neighbour);
                }
            }
            isOpen[id] = true;
            countOpen++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return isOpen[getIndex(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int id = getIndex(row, col);
        // int parent = UF.find(id);
        // return parent == UF.find(0);
        return UF.connected(id, 0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return countOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return UF.find(0) == UF.find(size * size + 1);
    }

    private int getIndex(int row, int col) {
        int id = (((row - 1) * size) + col);
        return id;
    }

    private int[] getRowCol(int id) {
        int row = id / size + 1;
        int col = id % size;
        int[] rowcol = new int[] { row, col };
        return rowcol;
    }

    private int[] getNeighbours(int id) {
        int[] neighbours = new int[4];
        if (id > size) neighbours[0] = id - size;
        else neighbours[0] = 0;
        if (id % size < size) neighbours[1] = id + 1;
        else neighbours[1] = -1;
        if (id <= size * size - size) neighbours[2] = id + size;
        else neighbours[2] = size * size + 1;
        if (id % size > 1) neighbours[3] = id - 1;
        else neighbours[3] = -1;
        return neighbours;
    }

    private boolean validate(int row, int col) {
        if (row < 1 || col < 1 || row > size || col > size)
            return false;
        return true;
    }

    private void throwExcept(String type, String prompt) {
        if (type.contentEquals("IllegalArgument"))
            throw new IllegalArgumentException(prompt);
        else if (type.contentEquals("IllegalArgument"))
            throw new IllegalArgumentException(prompt);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 1);
        StdOut.println(p.isOpen(1, 1));
        p.open(2, 2);
        StdOut.println(p.isOpen(2, 2));
        p.open(2, 1);
        StdOut.println(p.isOpen(2, 1));
        StdOut.println(p.percolates());
    }
}
