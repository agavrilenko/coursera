package org.my.coursera.utils;

import java.util.ArrayList;
import java.util.List;
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

    public static int[] generateSortedList(int n) {
        List<Integer> out = new ArrayList<>();
        Random r = new Random(4425252l);
        for (int i = 0; i < n; i++) {
            if (r.nextBoolean()) {
                out.add(i);
            }
        }
        return out.stream().mapToInt(i -> i).toArray();
    }

    public static int[] generateUnsortedListWithRepeates(int n) {
        List<Integer> out = new ArrayList<>();
        Random r = new Random(4425252l);
        for (int i = 1; i < n + 1; i++) {
            int nextInt = r.nextInt(i);
            for (int j = 0; j < nextInt; j++) {
                out.add(nextInt);
            }
        }
        return out.stream().mapToInt(i -> i).toArray();
    }
}
