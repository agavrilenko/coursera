import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by trash on 31-Jan-17.
 */
public class BaseballEliminationTest {

    public static final String MONTREAL = "Montreal";

    @Test
    public void testElimanaion_Simple() {

        BaseballElimination base = new BaseballElimination("teams4.txt");
        assertEquals(1, base.against(MONTREAL, "Atlanta"));
        assertEquals(82, base.losses(MONTREAL));
        assertEquals(77, base.wins(MONTREAL));
        assertEquals(3, base.remaining(MONTREAL));
        assertEquals(Arrays.asList("Atlanta", "Philadelphia", "New_York", "Montreal"), base.teams());
        assertEquals(4, base.numberOfTeams());
        assertTrue(base.isEliminated(MONTREAL));
    }

}