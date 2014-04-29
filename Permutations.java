/**
 * This package contains only one java file named as Permutations.
 *
 * @author Ivanov Nikita
 * @author https://github.com/nikitozeg
 * @author tazg@ya.ru
 * @version 1.0
 */
package yandex;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @value permutationList asds
 */

public class Permutations {
    /**
     * @value permutationList : This list is used to store permutations.
     * @value in              : Input object variable.
     * @value out             : Output object variable
     * @value array           : This array is used to store default permutation (1..N)
     * @value rate            : Number of combinations begins with the same first digit.
     * @value N               : Number of toggles
     * @value K               : Number of tests
     */

    private static List permutationList = new LinkedList();
    private static BufferedReader in;
    private static PrintWriter out;
    private static int[] array;
    private static int rate = 1;
    private static int n = 0;
    private static int k = 0;

    /**
     * This constructor initialize input and output files,
     * and assign the n and k values. Also assign default array.
     *
     * @throws java.io.IOException             indicates something wrong with input/output.
     * @throws java.lang.NumberFormatException indicates wrong input type.
     */
    public Permutations() {
        try {
            out = new PrintWriter("output.txt");
            in = new BufferedReader(new FileReader("input.txt"));
            n = Integer.parseInt(in.readLine());
            k = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            System.out.println("Something wrong with files. Check them.");
            System.exit(0);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input type. Should be Intger ");
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
     * This method makes one unique Permutation by one iteration.
     *
     * @param p: default array.
     * @return FALSE:    means combinations ended.
     * @return TRUE :    means combinations not ended.
     */
    public boolean nextPermutation(int[] p) {
        for (int a = p.length - 2; a >= 0; --a)
            if (p[a] < p[a + 1])
                for (int b = p.length - 1; ; --b)
                    if (p[b] > p[a]) {
                        int t = p[a];
                        p[a] = p[b];
                        p[b] = t;
                        for (++a, b = p.length - 1; a < b; ++a, --b) {
                            t = p[a];
                            p[a] = p[b];
                            p[b] = t;
                        }
                        return true;
                    }
        return false;
    }

    /**
     * This method gets a rate calculated by the argument p.
     *
     * @param  p    :   default array.
     * @return rate :   number of combinations begins with the same first digit.
     */
    public int getRate(int[] p) {
        int i = 0;
        int length = p.length - 1;

        while (i != length) {
            i++;
            rate *= i;
        }

        return rate;
    }

    /**
     * This method makes two operation: prints all permutations and write them to the file.
     *  @value counter
     *  @value shoft
     *  @value index
     *  @value counter
     */
    public void writeSetOfTest() {

        int counter = 0;
        int shift = 1;
        int index = 0;
        int i = 0;

        while (i != k) {
            System.out.println(Arrays.toString((int[]) permutationList.get(index)));
            out.print(Arrays.toString((int[]) permutationList.get(index)) + " ");
            index += rate;
            i++;
            counter++;

            if (counter == n) {
                index = shift;
                counter = 0;
                shift++;
            }
        }
    }

    /**
     * 1. By object creating, we initialized files and set the N and K.
     * 2. Getting rate for array.
     * 3. Making all unique permutations based on rate.
     * 4. Writing them on the file
     *
     * @param args
     */
    public static void main(String[] args) {

        Permutations obj = new Permutations();
        obj.getRate(array);

        do {
            permutationList.add(array.clone());
        } while (obj.nextPermutation(array));

        obj.writeSetOfTest();
        out.close();
    }
}
