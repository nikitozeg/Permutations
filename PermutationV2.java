/**
 * This package contains only one java file named as Permutations.
 * This code makes a shuffle permutation based on Fischer-Yacht alghorytm
 *
 * @author Ivanov Nikita
 * @author https://github.com/nikitozeg
 * @author tazg@ya.ru
 * @version 2.0
 */

package yandex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;

public class PermutationV2 {
    /**
     * @value in              : Input object variable.
     * @value out             : Output object variable
     * @value array           : This array is used to store default permutation (1..N)
     * @value N               : Number of toggles
     * @value K               : Number of tests
     */

    private static BufferedReader in;
    private static PrintWriter out;
    private static int[] array;
    private static int n = 0;
    private static int k = 0;

    /**
     * @value in              : Input object variable.
     * @value out             : Output object variable
     * @value array           : This array is used to store default permutation (1..N)
     * @value N               : Number of toggles
     * @value K               : Number of tests
     */
    private PermutationV2() {
        try {
            out = new PrintWriter("output.txt");
            in = new BufferedReader(new FileReader("input.txt"));
            n = Integer.parseInt(in.readLine());
            k = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            System.out.println("Something wrong with files. Check them.");
            System.exit(0);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input type. Should be 2 lines: the first one is N, second one is K ");
            System.exit(0);
        }

        array = new int[n];
        int i = 0;

        while (i != n) {
            array[i] = i + 1;
            i++;                                                               //
        }
    }

    /**
     * Implementing Fisher–Yates shuffle also known as the Knuth shuffle (after Donald Knuth),
     * is an algorithm for generating a random permutation of a finite set—in plain terms,
     * for randomly shuffling the set.
     *
     * @param arr :   default array.
     */
    private static void getShufflePermutations(int[] arr) {
        Random rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }

    /**
     * 1. By object creating, we initialized files and set the N and K.
     * 2. Making permutations in the loop and writing them on the file
     *
     * @value temp : used for temporary storage of array.
     * @value set  : this set used for finding duplicates using .contains()
     * @param args
     *
     */
    public static void main(String[] args) {
        PermutationV2 permInitialization = new PermutationV2();

        HashSet set = new LinkedHashSet();
        int[] temp = array.clone();

        while ((!(set.size() == k))) {
            array = temp.clone();
            getShufflePermutations(array);
            if (!set.contains(Arrays.toString(array))) {
                set.add(Arrays.toString(array.clone()));
                out.println(Arrays.toString(array));
            }
        }

        out.close();

    }
}