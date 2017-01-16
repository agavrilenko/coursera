package org.my.coursera.utils;

import java.util.Random;

/**
 * Created by trash on 22-Nov-16.
 */
public class DataUtils {
    public static void shuffle(Integer[] toSort) {
        Random rnd = new Random(1341234314);
        for (int i = toSort.length; i > 1; i--) {
            int index = rnd.nextInt(i);
            Integer tmp = toSort[i - 1];
            toSort[i - 1] = toSort[index];
            toSort[index] = tmp;

        }
    }

    public static void shuffle(int[] toSort) {
        Random rnd = new Random(1341234314);
        for (int i = toSort.length; i > 1; i--) {
            int index = rnd.nextInt(i);
            Integer tmp = toSort[i - 1];
            toSort[i - 1] = toSort[index];
            toSort[index] = tmp;

        }
    }
}
