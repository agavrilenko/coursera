import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by trash on 12-Feb-17.
 */
public class MoveToFrontTest {

    public static final String ABRA = "ABRACADABRA!";

    @Test
    public void testMoveToFront_EncodeSimple() throws NoSuchFieldException, IllegalAccessException, IOException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        Constructor<MoveToFront> constructor = MoveToFront.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        MoveToFront move = constructor.newInstance();

        Method encodeChar = MoveToFront.class.getDeclaredMethod("encodeChar", char.class);
        encodeChar.setAccessible(true);
        String abra = ABRA;
        StringBuilder outS = new StringBuilder();
        for (int i = 0; i < abra.length(); i++) {
            char in = abra.charAt(i);
            char out = (char) encodeChar.invoke(move, in);
            outS.append(String.format("%02x", Integer.valueOf(out & 255))).append(" ");
        }
        Assert.assertEquals("41 42 52 02 44 01 45 01 04 04 02 26 ", outS.toString());
    }

    @Test
    public void testMoveToFront_DecodeSimple() throws NoSuchFieldException, IllegalAccessException, IOException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        Constructor<MoveToFront> constructor = MoveToFront.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        MoveToFront move = constructor.newInstance();
        MoveToFront moveBack = constructor.newInstance();

        Method encodeChar = MoveToFront.class.getDeclaredMethod("encodeChar", char.class);
        Method decodeChar = MoveToFront.class.getDeclaredMethod("decodeChar", int.class);
        encodeChar.setAccessible(true);
        decodeChar.setAccessible(true);
        StringBuilder outS = new StringBuilder();
        for (int i = 0; i < ABRA.length(); i++) {
            char in = ABRA.charAt(i);
            char out = (char) encodeChar.invoke(move, in);
            char ch = (char) decodeChar.invoke(moveBack, (int) out);
            outS.append(ch);
        }
        Assert.assertEquals(ABRA, outS.toString());
    }

    @Test
    public void testMoveToFront_DecodeArbitraryText() throws NoSuchFieldException, IllegalAccessException, IOException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        Constructor<MoveToFront> constructor = MoveToFront.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        MoveToFront move = constructor.newInstance();
        MoveToFront moveBack = constructor.newInstance();

        Method encodeChar = MoveToFront.class.getDeclaredMethod("encodeChar", char.class);
        Method decodeChar = MoveToFront.class.getDeclaredMethod("decodeChar", int.class);
        encodeChar.setAccessible(true);
        decodeChar.setAccessible(true);
        String text = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&**()]['\"/?.,-_=+";
        StringBuilder outS = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char in = text.charAt(i);
            char out = (char) encodeChar.invoke(move, in);
            char ch = (char) decodeChar.invoke(moveBack, (int) out);
            outS.append(ch);
        }
        Assert.assertEquals(text, outS.toString());
    }

}