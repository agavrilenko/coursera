import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * Created by trash on 31-Oct-17.
 */
public class EvacuationTest {

    @Test
    public void testEvacuation() {

        for (int i = 1; i <= 36; i++) {
            String fileName = "evacuation/" + String.format("%02d", i);
            Scanner scIn = new Scanner(new BufferedInputStream(EvacuationTest.class.getClassLoader().getResourceAsStream(fileName)));
            String[] text = scIn.nextLine().split(" ");
            int vertCount = Integer.valueOf(text[0]);
            int edgeCount = Integer.valueOf(text[1]);

            int to;
            int from, capacity;
            Evacuation.FlowGraph graph = new Evacuation.FlowGraph(vertCount);
            for (int j = 0; j < edgeCount; j++) {
                from = scIn.nextInt() - 1;
                to = scIn.nextInt() - 1;
                capacity = scIn.nextInt();
                graph.addEdge(from, to, capacity);
            }
            Scanner scOut = new Scanner(EvacuationTest.class.getClassLoader().getResourceAsStream(fileName + ".a"));
            String line = scOut.nextLine();

            int exp = Integer.valueOf(line);
            Assert.assertEquals(String.format("Failed on TC[%s]", i), exp, Evacuation.maxFlow(graph, 0, vertCount - 1));
        }


    }

}