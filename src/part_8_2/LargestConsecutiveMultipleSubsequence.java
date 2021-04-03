package part_8_2;

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
    int n = 80_000_000;

    public static void main(String[] args) {
        new LargestConsecutiveMultipleSubsequence().run();
    }

    public void run() {
        long start = System.currentTimeMillis();
        int[] array = input();
        long middle = System.currentTimeMillis();
//        System.out.println(Arrays.toString(array));
        longestIncreasingSubsequence(array);
        long end = System.currentTimeMillis();

        System.out.printf("generate n= %d, time= %d ms", n, middle - start);
        System.out.printf("\nresult n= %d, time= %d ms", n, end - middle);
    }

    private int[] input() {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) Math.round(Math.random() * 1_000_000_000);
        }
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

    private void longestIncreasingSubsequence(int[] array) {
        int size = array.length;

        int[] tailIndexes = new int[size];
        int[] previousIndexes = new int[size];
        Arrays.fill(tailIndexes, 0);
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

        System.out.println("k = " + length);
        int[] result = new int[length];
        int index = length - 1;
        for (int i = tailIndexes[length - 1]; i >= 0; i = previousIndexes[i]) {
            result[index--] = array[i];
        }

//        System.out.println(Arrays.toString(result));
    }
}
