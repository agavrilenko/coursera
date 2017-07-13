import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public JobQueue(int numWorkers, int[] jobs) {
        this.numWorkers = numWorkers;
        this.jobs = jobs;
    }

    public JobQueue() {
    }

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    public int[] getAssignedWorker() {
        return assignedWorker;
    }

    public long[] getStartTime() {
        return startTime;
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    void assignJobs() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        JobPriQueue queue = new JobPriQueue(numWorkers);
        for (int i = 0; i < numWorkers; i++) {
            queue.add(new MyThread(i, 0));
        }
        for (int i = 0; i < jobs.length; i++) {
            MyThread thread = queue.removeMin();
            assignedWorker[i] = thread.priority;
            startTime[i] = thread.busyUntil;
            thread.busyUntil += jobs[i];
            queue.add(thread);
        }
    }

    void assignJobsNaive() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers];
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            assignedWorker[i] = bestWorker;
            startTime[i] = nextFreeTime[bestWorker];
            nextFreeTime[bestWorker] += duration;
        }
    }

    private static class JobPriQueue {

        MyThread[] threads;
        int length;

        public JobPriQueue(int size) {
            this.threads = new MyThread[size];
            this.length = 0;
        }

        public void add(MyThread thread) {
            if (length == threads.length) {
                throw new RuntimeException("Queue is full");
            }
            length += 1;
            threads[length - 1] = thread;
            siftUp(length - 1);
        }

        private void siftUp(int i) {
            int parent = (i - 1) / 2;
            int ind = i;
            while (i > 0 && threads[ind].compareTo(threads[parent]) < 0) {
                MyThread tmp = threads[ind];
                threads[ind] = threads[parent];
                threads[parent] = tmp;
                ind = parent;
            }
        }

        public MyThread removeMin() {
            MyThread result = threads[0];
            threads[0] = threads[length - 1];
            length -= 1;
            siftDown(0);
            return result;
        }

        private void siftDown(int i) {

            int minInd = i;
            int l = 2 * i + 1;
            int r = 2 * i + 2;

            if (l <= length && threads[l].compareTo(threads[minInd]) < 0) {
                minInd = l;
            }
            if (r <= length && threads[r].compareTo(threads[minInd]) < 0) {
                minInd = r;
            }
            if (minInd != i) {
                MyThread tmp = threads[i];
                threads[i] = threads[minInd];
                threads[minInd] = tmp;
                siftDown(minInd);
            }
        }

        public int getLength() {
            return length;
        }


    }

    private static class MyThread implements Comparable<MyThread> {
        int priority;
        long busyUntil;

        private MyThread(int priority, long busyUntil) {
            this.priority = priority;
            this.busyUntil = busyUntil;
        }


        @Override
        public int compareTo(MyThread o) {
            if (this.busyUntil == o.busyUntil) {
                return this.priority < o.priority ? -1 : 1;
            }
            return this.busyUntil < o.busyUntil ? -1 : 1;
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
