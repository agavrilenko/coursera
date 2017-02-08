import edu.princeton.cs.algs4.In;
import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

/**
 * Created by trash on 07-Feb-17.
 */
public class BoggleSolverTest {

    public static final String PATH = "src/test/resources/";

    @Test
    public void testBoggleSolver_Board4x4() {

        In in = new In(new Scanner(BoggleSolverTest.class.getClassLoader().getResourceAsStream("dictionary-algs4.txt")));
        String[] dictionary = in.readAllLines();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(PATH + "board4x4.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            score += solver.scoreOf(word);
        }
        Assert.assertEquals(33, score);

    }

    @Test
    public void testBoggleSolver_BoardQ() {

        In in = new In(new Scanner(BoggleSolverTest.class.getClassLoader().getResourceAsStream("dictionary-algs4.txt")));
        String[] dictionary = in.readAllLines();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(PATH + "board-q.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            score += solver.scoreOf(word);
        }
        Assert.assertEquals(84, score);

    }

    @Test
    public void testBoggleSolver_BoardQuestion() {
        String[] dictionary = new String[]{"QUESTION"};
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(PATH + "board-q.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            score += solver.scoreOf(word);
        }
        Assert.assertEquals(11, score);

    }

    @Test
    public void testBoggleSolver_Board4() {

        In in = new In(new Scanner(BoggleSolverTest.class.getClassLoader().getResourceAsStream("dictionary-yawl.txt")));
        String[] dictionary = in.readAllLines();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(PATH + "board-points4.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            score += solver.scoreOf(word);
        }
        Assert.assertEquals(4, score);

    }

    @Test
    public void testBoggleSolver_Board5() {

        In in = new In(new Scanner(BoggleSolverTest.class.getClassLoader().getResourceAsStream("dictionary-yawl.txt")));
        String[] dictionary = in.readAllLines();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(PATH + "board-points5.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            score += solver.scoreOf(word);
        }
        Assert.assertEquals(5, score);

    }

    @Test
    public void testBoggleSolver_Board100() {

        In in = new In(new Scanner(BoggleSolverTest.class.getClassLoader().getResourceAsStream("dictionary-yawl.txt")));
        String[] dictionary = in.readAllLines();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(PATH + "board-points100.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            score += solver.scoreOf(word);
        }
        Assert.assertEquals(100, score);

    }

    @Test
    public void testBoggleSolver_Board1500() {

        In in = new In(new Scanner(BoggleSolverTest.class.getClassLoader().getResourceAsStream("dictionary-yawl.txt")));
        String[] dictionary = in.readAllLines();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(PATH + "board-points1500.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            score += solver.scoreOf(word);
        }
        Assert.assertEquals(1500, score);

    }

    @Test
    public void testBoggleSolver_Board777() {

        In in = new In(new Scanner(BoggleSolverTest.class.getClassLoader().getResourceAsStream("dictionary-yawl.txt")));
        String[] dictionary = in.readAllLines();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(PATH + "board-points777.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            score += solver.scoreOf(word);
        }
        Assert.assertEquals(777, score);

    }

    @Test
    public void testBoggleSolver_Board26539_10times() {

        In in = new In(new Scanner(BoggleSolverTest.class.getClassLoader().getResourceAsStream("dictionary-yawl.txt")));
        String[] dictionary = in.readAllLines();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(PATH + "board-points26539.txt");
        int count = 0;
        while (count++ < 10) {
            int score = 0;
            for (String word : solver.getAllValidWords(board)) {
                score += solver.scoreOf(word);
            }
            Assert.assertEquals(26539, score);
        }

    }

}