import org.junit.Assert;
import org.junit.Test;

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
        int[][] jobs = new int[][]{
                new int[]{1, 2, 3, 4, 5},
                new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
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
        int[][] jobs = new int[][]{
                new int[]{1, 2, 3, 4, 5},
                new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
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
            JobQueue queueN = new JobQueue(workNums[i], jobs[i]);
            queueN.assignJobsNaive();
            Assert.assertArrayEquals("Failed on assigned wokers " + i, queueN.getAssignedWorker(), queue.getAssignedWorker());
            Assert.assertArrayEquals("Failed on timing " + i, queueN.getStartTime(), queue.getStartTime());
        }

    }

}