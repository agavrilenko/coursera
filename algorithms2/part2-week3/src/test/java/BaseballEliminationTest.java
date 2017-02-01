import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by trash on 31-Jan-17.
 */
public class BaseballEliminationTest {
    private final static String PATH = "src/test/resources/";
    public static final String MONTREAL = "Montreal";

    @Test
    public void testElimanaion_Simple() {

        BaseballElimination base = new BaseballElimination("teams4.txt");
        assertEquals(1, base.against(MONTREAL, "Atlanta"));
        assertEquals(82, base.losses(MONTREAL));
        assertEquals(77, base.wins(MONTREAL));
        assertEquals(3, base.remaining(MONTREAL));
        assertArrayEquals(Arrays.asList("Atlanta", "Philadelphia", "New_York", "Montreal").toArray(), ((Set) base.teams()).toArray());
        assertEquals(4, base.numberOfTeams());
        assertTrue(base.isEliminated(MONTREAL));
    }

}