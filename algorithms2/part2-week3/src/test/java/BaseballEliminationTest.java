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
        assertEquals(base.certificateOfElimination(ATLANTA), null);
        assertArrayEquals(((List) base.certificateOfElimination(PHILADELPHIA)).toArray(), new String[]{NEW_YORK, ATLANTA});
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testElimanaion_agianst_exception() {

        BaseballElimination base = new BaseballElimination("teams4.txt");
        assertEquals(1, base.against("P", ATLANTA));

    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testElimanaion_wins_exception() {

        BaseballElimination base = new BaseballElimination("teams4.txt");
        assertEquals(1, base.wins("P"));

    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testElimanaion_loses_exception() {

        BaseballElimination base = new BaseballElimination("teams4.txt");
        assertEquals(1, base.losses("P"));

    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testElimanaion_remaining_exception() {

        BaseballElimination base = new BaseballElimination("teams4.txt");
        assertEquals(1, base.remaining("P"));

    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testElimanaion_cert_exception() {

        BaseballElimination base = new BaseballElimination("teams4.txt");
        assertEquals(1, base.certificateOfElimination("P"));

    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testElimanaion_elim_exception() {

        BaseballElimination base = new BaseballElimination("teams4.txt");
        assertEquals(1, base.isEliminated("P"));

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

    @Test
    public void testElimanaion_teams10() {
        BaseballElimination base = new BaseballElimination("teams10.txt");
        assertTrue(!base.isEliminated(ATLANTA));
        assertTrue(base.isEliminated("Houston"));
        assertTrue(!base.isEliminated("Indiana"));
    }

    @Test
    public void testElimanaion_teams29() {
        BaseballElimination base = new BaseballElimination("teams29.txt");
        assertTrue(!base.isEliminated("Chicago"));
        assertTrue(!base.isEliminated(ATLANTA));
    }

    @Test
    public void testElimanaion_teams48() {
        BaseballElimination base = new BaseballElimination("teams48.txt");
        assertTrue(!base.isEliminated("Team0"));
    }

    @Test
    public void testElimanaion_teams5b() {
        BaseballElimination base = new BaseballElimination("teams5b.txt");
        assertTrue(!base.isEliminated(TORONTO));
    }

}