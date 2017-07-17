import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by trash on 12-Jul-17.
 */
public class JobQueueTest {

    @Test
    public void testJobQueue() {
        int[] workNums = new int[]{
                2,
                4,
        };
        long[][] jobs = new long[][]{
                new long[]{1, 2, 3, 4, 5},
                new long[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };
        int[][] assignedWorker = new int[][]{
                new int[]{0, 1, 0, 1, 0},
                new int[]{0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3},
        };
        long[][] startTime = new long[][]{
                new long[]{0, 0, 1, 2, 4},
                new long[]{0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4},
        };

        for (int i = 0; i < workNums.length; i++) {
            JobQueue queue = new JobQueue(workNums[i], jobs[i]);
            queue.assignJobs();
            Assert.assertArrayEquals("Failed on assigned wokers " + i, assignedWorker[i], queue.getAssignedWorker());
            Assert.assertArrayEquals("Failed on timing " + i, startTime[i], queue.getStartTime());
        }
    }

    @Test
    public void testAgainstNaive() {
        int[] workNums = new int[]{
                2,
                4,
        };
        long[][] jobs = new long[][]{
                new long[]{1, 2, 3, 4, 5},
                new long[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };
        for (int i = 0; i < workNums.length; i++) {
            JobQueue queue = new JobQueue(workNums[i], jobs[i]);
            queue.assignJobs();
            JobQueue queueN = new JobQueue(workNums[i], jobs[i]);
            queueN.assignJobsNaive();
            Assert.assertArrayEquals("Failed on assigned wokers " + i, queueN.getAssignedWorker(), queue.getAssignedWorker());
            Assert.assertArrayEquals("Failed on timing " + i, queueN.getStartTime(), queue.getStartTime());
        }

    }

    @Test
    public void testWithFileDataAndNaive_02() throws IOException {
//        RandomAccessFile fileOut = new RandomAccessFile(new File("src/test/resources/job_queue/02.a"), "r");
        RandomAccessFile fileIn = new RandomAccessFile(new File("src/test/resources/job_queue/02"), "r");

        String[] header = fileIn.readLine().split(" ");
        int workersNum = Integer.valueOf(header[0]);
        int numberOfJobs = Integer.valueOf(header[1]);
        long[] jobs = new long[numberOfJobs];
        String[] jobsSt = fileIn.readLine().split(" ");
        for (int i = 0; i < jobsSt.length; i++) {
            jobs[i] = Long.parseLong(jobsSt[i]);

        }
        JobQueue queue = new JobQueue(workersNum, jobs);
        JobQueue queueN = new JobQueue(workersNum, jobs);


        queue.assignJobs();
        queueN.assignJobsNaive();
        Assert.assertArrayEquals(queueN.getAssignedWorker(), queue.getAssignedWorker());
        Assert.assertArrayEquals(queueN.getStartTime(), queue.getStartTime());


    }

    @Test
    public void testWithFileData_02() throws IOException {
        RandomAccessFile fileOut = new RandomAccessFile(new File("src/test/resources/job_queue/02.a"), "r");
        RandomAccessFile fileIn = new RandomAccessFile(new File("src/test/resources/job_queue/02"), "r");

        String[] header = fileIn.readLine().split(" ");
        int workersNum = Integer.valueOf(header[0]);
        int numberOfJobs = Integer.valueOf(header[1]);
        long[] jobs = new long[numberOfJobs];
        String[] jobsSt = fileIn.readLine().split(" ");
        for (int i = 0; i < jobsSt.length; i++) {
            jobs[i] = Long.parseLong(jobsSt[i]);

        }
        JobQueue queue = new JobQueue(workersNum, jobs);

        long[] startTimes = new long[numberOfJobs];
        int[] assignedWorkers = new int[numberOfJobs];

        for (int i = 0; i < numberOfJobs; i++) {
            String[] pair = fileOut.readLine().split(" ");
            assignedWorkers[i] = Integer.parseInt(pair[0]);
            startTimes[i] = Long.parseLong(pair[1]);
        }
        queue.assignJobs();
        Assert.assertArrayEquals(assignedWorkers, queue.getAssignedWorker());
        Assert.assertArrayEquals(startTimes, queue.getStartTime());


    }

    @Test
    public void testWithFileData_08() throws IOException {
        RandomAccessFile fileOut = new RandomAccessFile(new File("src/test/resources/job_queue/08.a"), "r");
        RandomAccessFile fileIn = new RandomAccessFile(new File("src/test/resources/job_queue/08"), "r");

        String[] header = fileIn.readLine().split(" ");
        int workersNum = Integer.valueOf(header[0]);
        int numberOfJobs = Integer.valueOf(header[1]);
        long[] jobs = new long[numberOfJobs];
        String[] jobsSt = fileIn.readLine().split(" ");
        for (int i = 0; i < jobsSt.length; i++) {
            jobs[i] = Long.parseLong(jobsSt[i]);

        }
        JobQueue queue = new JobQueue(workersNum, jobs);

        long[] startTimes = new long[numberOfJobs];
        int[] assignedWorkers = new int[numberOfJobs];

        for (int i = 0; i < numberOfJobs; i++) {
            String[] pair = fileOut.readLine().split(" ");
            assignedWorkers[i] = Integer.parseInt(pair[0]);
            startTimes[i] = Long.parseLong(pair[1]);
        }
        queue.assignJobs();
        Assert.assertArrayEquals(assignedWorkers, queue.getAssignedWorker());
        Assert.assertArrayEquals(startTimes, queue.getStartTime());


    }

}