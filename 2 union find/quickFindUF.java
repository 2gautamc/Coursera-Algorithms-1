import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class quickFindUF {
    private int[] id;

    public quickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; ++i) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return (id[p] == id[q]);
    }

    public void union(int p, int q) {
        for (int i = 0; i < id.length; i++) {
            if (id[i] == id[p]) {
                id[i] = id[q];
            }
        }
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        quickFindUF UF = new quickFindUF(N);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            UF.union(p, q);
        }
        StdOut.println(UF.connected(0, 1));
        StdOut.println(UF.connected(0, 5));
        StdOut.println(UF.connected(0, 9));
        StdOut.println(UF.connected(5, 6));
        StdOut.println(UF.connected(7, 2));
        StdOut.println(UF.connected(4, 6));
    }
}
