import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by trash on 08-Jul-17.
 */
public class ProcessPackagesTest {

    @Test
    public void testProcessPachagesSimple() {
        Buffer[] buffers = new Buffer[]{
                new Buffer(2),
                new Buffer(2),
                new Buffer(3),
                new Buffer(2),
                new Buffer(1),
                new Buffer(1),
                new Buffer(1),
                new Buffer(1),

        };

        ArrayList<Request>[] in = new ArrayList[]{
                new ArrayList(Arrays.asList(new Request(0, 2), new Request(1, 1), new Request(2, 2))),
                new ArrayList(Arrays.asList(new Request(0, 2), new Request(0, 2), new Request(1, 1))),
                new ArrayList(Arrays.asList(new Request(0, 1), new Request(0, 1), new Request(0, 1))),
                new ArrayList(Arrays.asList(new Request(0, 1), new Request(0, 1), new Request(0, 1))),
                new ArrayList(),
                new ArrayList(Arrays.asList(new Request(0, 0))),
                new ArrayList(Arrays.asList(new Request(0, 1), new Request(0, 1))),
                new ArrayList(Arrays.asList(new Request(0, 1), new Request(1, 1))),
        };

        List<Response>[] out = new List[]{
                Arrays.asList(new Response(false, 0), new Response(false, 2), new Response(false, 3)),
                Arrays.asList(new Response(false, 0), new Response(false, 2), new Response(true, 0)),
                Arrays.asList(new Response(false, 0), new Response(false, 1), new Response(false, 2)),
                Arrays.asList(new Response(false, 0), new Response(false, 1), new Response(true, 0)),
                new ArrayList(),
                Arrays.asList(new Response(false, 0)),
                Arrays.asList(new Response(false, 0), new Response(true, 0)),
                Arrays.asList(new Response(false, 0), new Response(false, 1)),
        };

        for (int i = 0; i < in.length; i++) {
            Assert.assertArrayEquals("Failed on TC " + i, out[i].toArray(), process_packages.ProcessRequests(in[i], buffers[i]).toArray());
        }

    }

}