import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by trash on 19-Feb-17.
 */
public class EncodeDecodeTest {

    @Test
    public void testEcnodeDecode_Simple() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, NoSuchFieldException {
        String someText = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&**()]['\"/?.,-_=+";
        String decodedText = encodeDecode(someText);
        Assert.assertEquals(someText, decodedText);
    }

    @Test
    public void testEcnodeDecode_Starts() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, NoSuchFieldException {
        String someText = "*************";
        String decodedText = encodeDecode(someText);
        Assert.assertEquals(someText, decodedText);
    }

    @Test
    public void testEcnodeDecode_SomeText() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, NoSuchFieldException {
        String someText = "religion, freedom of";
        String decodedText = encodeDecode(someText);
        Assert.assertEquals(someText, decodedText);
    }

    @Test
    public void testEcnodeDecode_Amendments() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, IOException, NoSuchFieldException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(EncodeDecodeTest.class.getResourceAsStream("amendments.txt")));
        StringBuilder builder = new StringBuilder();
        String tmp;
        while ((tmp = reader.readLine()) != null) {
            builder.append(tmp);
        }
        String someText = builder.toString();
        String decodedText = encodeDecode(someText);
        Assert.assertEquals(someText, decodedText);
    }


    private String encodeDecode(String someText) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Constructor<BurrowsWheeler> constructor = BurrowsWheeler.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Method encode = BurrowsWheeler.class.getDeclaredMethod("encode", String.class);
        encode.setAccessible(true);
        Method decode = BurrowsWheeler.class.getDeclaredMethod("decode", String.class, int.class);
        decode.setAccessible(true);
        Field first = BurrowsWheeler.class.getDeclaredField("first");
        first.setAccessible(true);
        BurrowsWheeler wheeler = constructor.newInstance();

        Constructor<MoveToFront> moveToFrontConstructor = MoveToFront.class.getDeclaredConstructor();
        moveToFrontConstructor.setAccessible(true);
        MoveToFront move = moveToFrontConstructor.newInstance();
        MoveToFront moveBack = moveToFrontConstructor.newInstance();

        Method encodeChar = MoveToFront.class.getDeclaredMethod("encode", char.class);
        Method decodeChar = MoveToFront.class.getDeclaredMethod("decodeChar", int.class);
        encodeChar.setAccessible(true);
        decodeChar.setAccessible(true);

        String decoded = (String) encode.invoke(wheeler, someText);
        char[] outs = new char[decoded.length()];
        for (int i = 0; i < decoded.length(); i++) {
            char in = decoded.charAt(i);
            outs[i] = (char) encodeChar.invoke(move, in);
        }
        String finalEncoded = new String(outs);
        char[] encOuts = finalEncoded.toCharArray();
        char[] decIns = new char[finalEncoded.length()];
        for (int i = 0; i < finalEncoded.length(); i++) {

            decIns[i] = (char) decodeChar.invoke(moveBack, (int) encOuts[i]);

        }
        String stilEncoded = new String(decIns);

        int firstt = first.getInt(wheeler);
        return (String) decode.invoke(null, stilEncoded, firstt);
    }

}
