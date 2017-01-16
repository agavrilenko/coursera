/**
 * Created by trash on 05-Nov-16.
 */
public class FileTest {
    public static void main(String[] args) {

        Percolation per = new Percolation(1);
        per.open(1, 1);
        per.isOpen(1, 1);
        per.percolates();

    }
}
