import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * Created by trash on 25-Nov-16.
 */
public class BoardTest {

    @Test
    public void testManhattan() {
        int[][] blocks = new int[][]{{1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}};
        Board board = new Board(blocks);
        Assert.assertEquals(0, board.manhattan());
        Assert.assertTrue(board.isGoal());

    }

    @Test
    public void testManhattan_fromExample() {
        int[][] blocks = new int[][]{{0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}};
        Board board = new Board(blocks);
        System.out.println(board.toString());

    }

    @Test
    public void testManhattan_Size4() {
        int[][] blocks = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        Board board = new Board(blocks);
        Assert.assertEquals(0, board.manhattan());
        Assert.assertTrue(board.isGoal());

    }

    @Test
    public void testManhattan_Value3() {
        int[][] blocks = new int[][]{{2, 1, 3}, {4, 5, 6}, {7, 0, 8}};
        Board board = new Board(blocks);
        Assert.assertEquals(3, board.manhattan());
        Assert.assertFalse(board.isGoal());

    }

    @Test
    public void testManhattan_IncorrectVertical() {
        int[][] blocks = new int[][]{{5, 1, 3}, {4, 2, 6}, {7, 0, 8}};
        Board board = new Board(blocks);
        Assert.assertEquals(5, board.manhattan());
        Assert.assertFalse(board.isGoal());

    }

    @Test
    public void testNeighbors() {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 5, 8}});
        Board n1 = new Board(new int[][]{{1, 0, 3}, {4, 2, 6}, {7, 5, 8}});
        Board n2 = new Board(new int[][]{{1, 2, 3}, {4, 6, 0}, {7, 5, 8}});
        Board n3 = new Board(new int[][]{{1, 2, 3}, {0, 4, 6}, {7, 5, 8}});
        Board n4 = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 8}});

        List<Board> neighbors = (List<Board>) board.neighbors();
        Assert.assertEquals(4, neighbors.size());
        Assert.assertTrue(neighbors.contains(n1));
        Assert.assertTrue(neighbors.contains(n2));
        Assert.assertTrue(neighbors.contains(n3));
        Assert.assertTrue(neighbors.contains(n4));
    }

    @Test
    public void testNeighborsHor() {
        Board board = new Board(new int[][]{{1, 0, 3}, {4, 2, 6}, {7, 5, 8}});
        Board n1 = new Board(new int[][]{{0, 1, 3}, {4, 2, 6}, {7, 5, 8}});
        Board n2 = new Board(new int[][]{{1, 3, 0}, {4, 2, 6}, {7, 5, 8}});
        Board n3 = new Board(new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 5, 8}});

        List<Board> neighbors = (List<Board>) board.neighbors();
        Assert.assertEquals(3, neighbors.size());
        Assert.assertTrue(neighbors.contains(n1));
        Assert.assertTrue(neighbors.contains(n2));
        Assert.assertTrue(neighbors.contains(n3));
    }

    @Test
    public void testNeighborsVert() {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 6, 0}, {7, 5, 8}});
        Board n1 = new Board(new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 5, 8}});
        Board n2 = new Board(new int[][]{{1, 2, 0}, {4, 6, 3}, {7, 5, 8}});
        Board n3 = new Board(new int[][]{{1, 2, 3}, {4, 6, 8}, {7, 5, 0}});

        List<Board> neighbors = (List<Board>) board.neighbors();
        Assert.assertEquals(3, neighbors.size());
        Assert.assertTrue(neighbors.contains(n1));
        Assert.assertTrue(neighbors.contains(n2));
        Assert.assertTrue(neighbors.contains(n3));
    }

    @Test
    public void testNeighbors_Vert_WithParent() {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 6, 0}, {7, 5, 8}});
        Board n2 = new Board(new int[][]{{1, 2, 0}, {4, 6, 3}, {7, 5, 8}});
        Board n21 = new Board(new int[][]{{1, 0, 2}, {4, 6, 3}, {7, 5, 8}});
        Board n22 = new Board(new int[][]{{1, 2, 3}, {4, 6, 0}, {7, 5, 8}});

        List<Board> neighbors = (List<Board>) board.neighbors();
        Assert.assertEquals(3, neighbors.size());
        Board first = neighbors.get(0);
        Assert.assertEquals(n2, first);

        neighbors = (List<Board>) first.neighbors();
        Assert.assertEquals(2, neighbors.size());
        Assert.assertTrue(neighbors.contains(n21));
        Assert.assertTrue(neighbors.contains(n22));


    }

    @Test
    public void testNeighborsCorner() {
        Board board = new Board(new int[][]{{1, 2, 0}, {4, 6, 3}, {7, 5, 8}});
        Board n1 = new Board(new int[][]{{1, 2, 3}, {4, 6, 0}, {7, 5, 8}});
        Board n2 = new Board(new int[][]{{1, 0, 2}, {4, 6, 3}, {7, 5, 8}});

        List<Board> neighbors = (List<Board>) board.neighbors();
        Assert.assertEquals(2, neighbors.size());
        Assert.assertTrue(neighbors.contains(n1));
        Assert.assertTrue(neighbors.contains(n2));
    }

    @Test
    public void testNeighbors_ZeroOnLastLineMiddle() {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 8}});
        Board n1 = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
        Board n2 = new Board(new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 5, 8}});
        Board n3 = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {0, 7, 8}});

        List<Board> neighbors = (List<Board>) board.neighbors();
        Assert.assertEquals(3, neighbors.size());
        Assert.assertTrue(neighbors.contains(n1));
        Assert.assertTrue(neighbors.contains(n2));
        Assert.assertTrue(neighbors.contains(n3));
    }

    @Test
    public void testEquality() {
        Board board = new Board(new int[][]{{1, 2, 0}, {4, 6, 3}, {7, 5, 8}});
        Board n1 = new Board(new int[][]{{1, 2, 0}, {4, 6, 3}, {7, 5, 8}});
        Board n2 = new Board(new int[][]{{1, 0, 2}, {4, 6, 3}, {7, 5, 8}});

        Assert.assertTrue(board.equals(n1));
        Assert.assertFalse(board.equals(n2));
    }

    @Test
    public void testTwin() {
        Board board = new Board(new int[][]{{1, 2, 0}, {4, 6, 3}, {7, 5, 8}});
        Board twin = board.twin();
        Assert.assertFalse(board.equals(twin));
    }

    @Test
    public void testHamming() {
        Board board = new Board(new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}});
        Assert.assertEquals(4, board.hamming());
    }

    @Test
    public void testHamming07() {
        Board board = new Board(new int[][]{{1, 2, 3}, {0, 7, 6}, {5, 4, 8}});
        Assert.assertEquals(4, board.hamming());
    }

    @Test
    public void testHamming17() {
        Board board = new Board(new int[][]{{5, 1, 8}, {2, 7, 3}, {4, 0, 6}});
        Assert.assertEquals(8, board.hamming());
    }

    @Test
    public void testManhattan_27() {
        int[][] blocks = new int[][]{{5, 8, 7},
                {1, 4, 6},
                {0, 3, 2}};
        Board board = new Board(blocks);
        Assert.assertEquals(16, board.manhattan());

    }

    @Test
    public void testManhattan_27_1() {
        int[][] blocks = new int[][]{{5, 0, 7},
                {1, 8, 6},
                {3, 4, 2}};
        Board board = new Board(blocks);
        Assert.assertEquals(17, board.manhattan());

    }

    @Test
    public void testManhattan_2x2_Un() {
        int[][] blocks = new int[][]{{0, 1},
                {2, 3}};
        Board board = new Board(blocks);
        Assert.assertEquals(4, board.manhattan());

    }

    @Test
    public void testNeighbors_Neighbors() {
        int[][] blocks = new int[][]{{0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}};
        Board board = new Board(blocks);
        int cnt = 0;
        Iterator<Board> iter = board.neighbors().iterator();
        while (iter.hasNext()) {
            Iterator iter1 = iter.next().neighbors().iterator();
            while (iter1.hasNext()) {
                iter1.next();
                cnt++;
            }
        }
        Assert.assertEquals(6, cnt);

    }

    @Test
    public void testNeighbors_Neighbors4x4() {
        int[][] blocks = new int[][]{{2, 0, 3, 4},
                {1, 10, 6, 8},
                {5, 9, 7, 12},
                {13, 14, 11, 15}
        };
        Board board = new Board(blocks);
        int cnt = 0;
        Iterator<Board> iter = board.neighbors().iterator();
        while (iter.hasNext()) {
            Iterator iter1 = iter.next().neighbors().iterator();
            while (iter1.hasNext()) {
                iter1.next();
                cnt++;
            }
        }
        Assert.assertEquals(9, cnt);

    }


}