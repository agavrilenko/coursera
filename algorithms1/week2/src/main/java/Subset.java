import edu.princeton.cs.algs4.StdIn;

/**
 * Created by trash on 09-Nov-16.
 */
public class Subset {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Count is not provided");
            return;
        }

        int cnt = Integer.parseInt(args[0]);
        String line = StdIn.readLine();
        String[] values = line.split(" ");
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        for (int i = 0; i < values.length; i++) {
            queue.enqueue(values[i]);
            values[i] = null;
        }
        while (cnt > 0 && !queue.isEmpty()) {
            System.out.println(queue.dequeue());
            cnt--;
        }
    }
}
