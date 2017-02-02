import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by trash on 31-Jan-17.
 */
public class BaseballEliminationTest {
    private final static String PATH = "src/test/resources/";
    public static final String MONTREAL = "Montreal";
    public static final String DETROIT = "Detroit";
    public static final String ATLANTA = "Atlanta";
    public static final String PHILADELPHIA = "Philadelphia";
    public static final String NEW_YORK = "New_York";
    public static final String BALTIMORE = "Baltimore";
    public static final String BOSTON = "Boston";
    public static final String TORONTO = "Toronto";


    @Test
    public void testElimanaion_Trivial() {

        BaseballElimination base = new BaseballElimination("teams4.txt");
        assertEquals(1, base.against(MONTREAL, ATLANTA));
        assertEquals(82, base.losses(MONTREAL));
        assertEquals(77, base.wins(MONTREAL));
        assertEquals(3, base.remaining(MONTREAL));
        assertArrayEquals(Arrays.asList(ATLANTA, PHILADELPHIA, NEW_YORK, MONTREAL).toArray(), ((Set) base.teams()).toArray());
        assertEquals(4, base.numberOfTeams());
        assertTrue(base.isEliminated(MONTREAL));
        assertTrue(base.isEliminated(PHILADELPHIA));
        assertFalse(base.isEliminated(ATLANTA));
        assertFalse(base.isEliminated(NEW_YORK));
        assertArrayEquals(((List) base.certificateOfElimination(MONTREAL)).toArray(), new String[]{ATLANTA});
        assertArrayEquals(((List) base.certificateOfElimination(PHILADELPHIA)).toArray(), new String[]{NEW_YORK, ATLANTA});
    }

    @Test
    public void testElimanaion_teams5() {
        BaseballElimination base = new BaseballElimination("teams5.txt");
        assertTrue(base.isEliminated(DETROIT));
        assertTrue(base.isEliminated(DETROIT));
        assertArrayEquals(((List) base.certificateOfElimination(DETROIT)).toArray(), new String[]{TORONTO, BOSTON, BALTIMORE, NEW_YORK});
        assertFalse(base.isEliminated(NEW_YORK));
        assertFalse(base.isEliminated(BALTIMORE));
        assertFalse(base.isEliminated(BOSTON));
        assertFalse(base.isEliminated(BOSTON));
        assertFalse(base.isEliminated(TORONTO));
    }

    @Test
    public void testElimanaion_teams12() {
        BaseballElimination base = new BaseballElimination("teams12.txt");
        assertTrue(base.isEliminated("Japan"));
    }

}