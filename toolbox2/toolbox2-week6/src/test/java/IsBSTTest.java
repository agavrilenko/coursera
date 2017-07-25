import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 21-Jul-17.
 */
public class IsBSTTest {
    @Test
    public void testIsBSTSimple() {

        is_bst.IsBST.Node[][] nodes = new is_bst.IsBST.Node[][]{
                new is_bst.IsBST.Node[]{},
                new is_bst.IsBST.Node[]{node(4, 1, -1), node(2, 2, 3), node(1, -1, -1),
                        node(5, -1, -1)},
                new is_bst.IsBST.Node[]{node(4, 1, 2), node(2, 3, 4), node(6, 5, 6),
                        node(1, -1, -1), node(3, -1, -1), node(5, -1, -1),
                        node(7, -1, -1)},

                new is_bst.IsBST.Node[]{node(1, -1, 1), node(2, -1, 2), node(3, -1, 3),
                        node(4, -1, 4), node(5, -1, -1),},
                new is_bst.IsBST.Node[]{node(2, 1, 2), node(1, -1, -1), node(3, -1, -1)},
                new is_bst.IsBST.Node[]{node(1, 1, 2), node(2, -1, -1), node(3, -1, -1)},
                new is_bst.IsBST.Node[]{node(1, -1, 1), node(2, -1, 2), node(3, -1, 3),
                        node(4, -1, 4), node(5, -1, -1)},
        };

        String[] answers = new String[]{
                "CORRECT",
                "INCORRECT",
                "CORRECT",
                "CORRECT",
                "CORRECT",
                "INCORRECT",
                "CORRECT",
        };

        for (int i = 0; i < nodes.length; i++) {
            is_bst.IsBST trees = new is_bst.IsBST();
            trees.tree = nodes[i];
            Assert.assertEquals("Failed on inOrder " + i, answers[i], trees.isBinarySearchTree() ? "CORRECT" : "INCORRECT");

        }


    }

    private static is_bst.IsBST.Node node(int key, int left, int right) {
        return new is_bst.IsBST.Node(key, left, right);
    }

}