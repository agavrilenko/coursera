import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by trash on 19-Jan-17.
 */
public class WordNetTest {
    @Test
    public void testWordNet_Tree() {
        WordNet net = new WordNet("input/synsets15.txt", "input/hypernyms15Tree.txt");
        assertTrue(net.isNoun("k"));
        assertEquals(2, net.distance("d", "e"));
        assertEquals("b r", net.sap("d", "e"));
        assertEquals(1, net.distance("d", "b"));
        assertEquals("b r", net.sap("d", "b"));
        assertEquals(7, net.distance("i", "o"));
        assertEquals("b r", net.sap("i", "o"));
        assertEquals(5, net.distance("i", "m"));
        assertEquals("b r", net.sap("i", "m"));
        assertEquals(5, net.distance("i", "p"));
        assertEquals("b r", net.sap("i", "p"));
        assertEquals(0, net.distance("i", "i"));
    }

    @Test
    public void testWordNet_Path() {
        WordNet net = new WordNet("input/synsets15.txt", "input/hypernyms15Path.txt");
        assertTrue(net.isNoun("k"));
        assertFalse(net.isNoun("q"));
        assertEquals(1, net.distance("d", "e"));
        assertEquals("e", net.sap("d", "e"));
        assertEquals(2, net.distance("d", "b"));
        assertEquals("d", net.sap("d", "b"));
        assertEquals(6, net.distance("i", "o"));
        assertEquals("o", net.sap("i", "o"));
    }

    @Test(expected = NullPointerException.class)
    public void testWordNet_CornerCase_NullPointer_Dist() {
        WordNet net = new WordNet("input/synsets15.txt", "input/hypernyms15Path.txt");
        assertEquals(1, net.distance("d", null));

    }

    @Test(expected = NullPointerException.class)
    public void testWordNet_CornerCase_NullPointer_IsNoun() {
        WordNet net = new WordNet("input/synsets15.txt", "input/hypernyms15Path.txt");
        assertEquals(1, net.isNoun(null));

    }

    @Test(expected = NullPointerException.class)
    public void testWordNet_CornerCase_NullPointer_SAP() {
        WordNet net = new WordNet("input/synsets15.txt", "input/hypernyms15Path.txt");
        assertEquals("e", net.sap("d", null));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWordNet_Cycle() {
        new WordNet("input/synsets15.txt", "input/hypernyms15Cycle.txt");
    }

}