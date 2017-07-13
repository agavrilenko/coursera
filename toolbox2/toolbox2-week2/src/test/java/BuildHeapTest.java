import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by trash on 12-Jul-17.
 */
public class BuildHeapTest {

    @Test
    public void testBuildHeap() {
        int[][] in = new int[][]{
                new int[]{6, 5, 4, 3, 2, 1},
                new int[]{5, 4, 3, 2, 1},
                new int[]{1, 2, 3, 4, 5},
        };
        ArrayList<BuildHeap.Swap>[] out = new ArrayList[]{
                new ArrayList(Arrays.asList(swap(2, 5), swap(1, 4), swap(0, 2), swap(2, 5))),
                new ArrayList(Arrays.asList(swap(1, 4), swap(0, 1), swap(1, 3))),
                new ArrayList(),
        };

        for (int i = 0; i < in.length; i++) {
            BuildHeap heap = new BuildHeap();
            heap.data = in[i];
            heap.generateSwaps();
            Assert.assertEquals("Failed on size comparison " + i, out[i].size(), heap.swaps.size());
            Assert.assertArrayEquals("Failed on TC " + i, out[i].toArray(), heap.swaps.toArray());
        }

    }

//    @Test

    public void testBuildHeapFile() throws IOException {
        RandomAccessFile fileOut = new RandomAccessFile(new File("src/test/resources/make_heap/04.a"), "r");
        RandomAccessFile fileIn = new RandomAccessFile(new File("src/test/resources/make_heap/04"), "r");

        int size = Integer.parseInt(fileIn.readLine());
        int[] data = new int[size];
        String[] dataSt = fileIn.readLine().split(" ");
        for (int i = 0; i < dataSt.length; i++) {
            data[i] = Integer.parseInt(dataSt[i]);
        }

        int outSize = Integer.parseInt(fileOut.readLine());
        String nextPair = null;
        ArrayList<BuildHeap.Swap> swaps = new ArrayList<>();
        while ((nextPair = fileOut.readLine()) != null) {
            String[] pair = nextPair.split(" ");
            swaps.add(new BuildHeap.Swap(Integer.parseInt(pair[0]), Integer.parseInt(pair[1])));
        }

        BuildHeap heap = new BuildHeap();
        heap.data = data;
        heap.generateSwaps();
        Assert.assertEquals(swaps.size(), heap.swaps.size());
        Assert.assertArrayEquals(swaps.toArray(), heap.swaps.toArray());
    }

    public BuildHeap.Swap swap(int index2, int index1) {
        return new BuildHeap.Swap(index2, index1);
    }

}