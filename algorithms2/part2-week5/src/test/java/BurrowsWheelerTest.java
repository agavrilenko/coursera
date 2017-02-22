import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by trash on 15-Feb-17.
 */
public class BurrowsWheelerTest {

    public static final String ORIGINAL = "ABRACADABRA!";
    public static final String EXPECTED = "ARD!RCAAAABB";

    @Test
    public void testBurrowsWheeler_Simple() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String s = EXPECTED;

        Constructor<BurrowsWheeler> constructor = BurrowsWheeler.class.getDeclaredConstructor(int.class, String.class);
        constructor.setAccessible(true);

        Method buildNext = BurrowsWheeler.class.getDeclaredMethod("buildNext");
        Method getOriginal = BurrowsWheeler.class.getDeclaredMethod("getOriginal");
        buildNext.setAccessible(true);
        getOriginal.setAccessible(true);
        BurrowsWheeler wheeler = constructor.newInstance(3, s);
//        Assert.assertEquals(s.length(), wheeler.getLength());
        buildNext.invoke(wheeler);
        String original = (String) getOriginal.invoke(wheeler);
        Assert.assertEquals(ORIGINAL, original);

    }

    @Test
    public void testBurrowsWheeler_encode() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<BurrowsWheeler> constructor = BurrowsWheeler.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Method encode = BurrowsWheeler.class.getDeclaredMethod("encode", String.class);
        encode.setAccessible(true);
        BurrowsWheeler wheeler = constructor.newInstance();
        String result = (String) encode.invoke(wheeler, ORIGINAL);
        Assert.assertEquals(EXPECTED, result);

    }


    @Test
    public void testBurrowsWheeler_encodeDecode() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Assert.assertEquals(ORIGINAL, encodeDecode(ORIGINAL));
    }

    @Test
    public void testBurrowsWheeler_encodeDecodeAlphaNum() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        String original = "abcdefghijklmnopqrstuvwxyz0123456789";
        Assert.assertEquals(original, encodeDecode(original));
    }

    @Test
    public void testBurrowsWheeler_encodeDecodeText() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        String original = "a**";
        Assert.assertEquals(original, encodeDecode(original));
    }

    @Test
    public void testBurrowsWheeler_encodeDecodeText1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        String original = "baa";
        Assert.assertEquals(original, encodeDecode(original));
    }

    @Test
    public void testBurrowsWheeler_encodeDecodeAlphaNumSymb() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        String original = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&**()]['\"/?.,-_=+";
        Assert.assertEquals(original, encodeDecode(original));
    }

    private String encodeDecode(String original1) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Constructor<BurrowsWheeler> constructor = BurrowsWheeler.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Method encode = BurrowsWheeler.class.getDeclaredMethod("encode", String.class);
        encode.setAccessible(true);
        Method decode = BurrowsWheeler.class.getDeclaredMethod("decode", String.class, int.class);
        decode.setAccessible(true);
        Field first = BurrowsWheeler.class.getDeclaredField("first");
        first.setAccessible(true);
        BurrowsWheeler wheeler = constructor.newInstance();
        String encoded = (String) encode.invoke(wheeler, original1);
        int firstt = first.getInt(wheeler);
        return (String) decode.invoke(null, encoded, firstt);
    }
}