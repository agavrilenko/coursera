import org.junit.Assert;
import org.junit.Test;

/**
 * Created by trash on 07-Jul-17.
 */
public class check_bracketsTest {

    @Test
    public void testCheckBracketsSimple() {

        String[] in = new String[]{
                "[]",
                "{}[]",
                "[()]",
                "(())",
                "{[]}()",
                "{",
                "{[}",
                "foo(bar);",
                "foo(bar[i);",
                "{([",
                ")",
                "aaaa)",
        };

        String[] out = new String[]{
                "Success",
                "Success",
                "Success",
                "Success",
                "Success",
                "1",
                "3",
                "Success",
                "10",
                "1",
                "1",
                "5",
        };
        for (int i = 0; i < in.length; i++) {
            Assert.assertEquals("Failed on TC " + i, out[i], check_brackets.processString(in[i]));
        }

    }

}