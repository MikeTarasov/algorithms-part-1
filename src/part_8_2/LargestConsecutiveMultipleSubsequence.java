package part_8_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LargestConsecutiveMultipleSubsequence {
    /**
     * Дано целое число 1 ≤ n ≤ 10^5 и массив A[1…n], содержащий неотрицательные целые числа, не превосходящие 10^9.
     * <p>
     * Найдите наибольшую невозрастающую подпоследовательность в A.
     * В первой строке выведите её длину k, во второй — её индексы 1 ≤ i1 ≤ i2 ... ≤ ik ≤ n
     * (таким образом, A[i1] ≥ A[i2] ≥ … ≥ A[in]).
     * <p>
     * <p>
     * Test random 0-10^9:
     * <p>
     * n=      1_000, time=    28 ms
     * n=     10_000, time=    25 ms
     * n=    100_000, time=    46 ms
     * n=  1_000_000, time=   119 ms
     * n= 10_000_000, time= 1_596 ms
     * n= 20_000_000, time= 2_351 ms
     * n= 40_000_000, time= 4_757 ms
     * n= 80_000_000, time= 9_829 ms
     */

    public static void main(String[] args) throws IOException {
        new LargestConsecutiveMultipleSubsequence().run();
    }

    public void run() throws IOException {
        int[] array = input();
        longestDecreasingSubsequence(array);
    }

    private int[] input() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());

        int[] array = new int[n];
        int i = 0;
        for (String value : bufferedReader.readLine().split("\\s+")) {
            array[i] = Integer.parseInt(value);
            i++;
        }

        bufferedReader.close();
        return array;
    }

    private int getCeilIndex(int[] array, int[] tailIndexes, int right, int key) {
        int left = -1;
        while (right - left > 1) {
            int middle = left + (right - left) / 2;
            if (array[tailIndexes[middle]] < key) { // >= on increasing
                right = middle;
            } else {
                left = middle;
            }
        }
        return right;
    }

    private void longestDecreasingSubsequence(int[] array) {
        int size = array.length;

        int[] tailIndexes = new int[size];
        int[] previousIndexes = new int[size];

        Arrays.fill(previousIndexes, -1);

        int length = 1;

        for (int i = 1; i < size; i++) {
            if (array[i] > array[tailIndexes[0]]) { // <  on increasing
                tailIndexes[0] = i;
            } else if (array[i] <= array[tailIndexes[length - 1]]) { // >=  on increasing
                previousIndexes[i] = tailIndexes[length - 1];
                tailIndexes[length++] = i;
            } else {
                int position = getCeilIndex(array, tailIndexes, length - 1, array[i]);
                previousIndexes[i] = tailIndexes[position - 1];
                tailIndexes[position] = i;
            }
        }

        System.out.println(length);

        int[] result = new int[length];
        int index = length - 1;
        for (int i = tailIndexes[length - 1]; i >= 0; i = previousIndexes[i]) {
            result[index--] = i + 1;
        }
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}