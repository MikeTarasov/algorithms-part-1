package part_8_2;

import java.io.IOException;
import java.util.Arrays;

public class LargestConsecutiveMultipleSubsequenceNative {
    /**
     * Дано целое число 1 ≤ n ≤ 10^3 и массив A[1…n] натуральных чисел, не превосходящих 2*10^9.
     * <p>
     * Выведите максимальное 1 ≤ k ≤n, для которого найдётся подпоследовательность 1 ≤ i < i2 <…< ik ≤ n длины k,
     * в которой каждый элемент делится на предыдущий (формально: для  всех 1 ≤ j < k, A[i{j}], A[i{j+1}].
     * <p>
     * Input:
     * 4
     * 3 6 7 12
     * <p>
     * Output:
     * 3
     * <p>
     * Tests:
     * n= 1_000, time= 9 ms
     * n= 10_000, time= 201 ms
     * n= 100_000, time= 20_252 ms
     */
    int n = 100_000;

    public static void main(String[] args) throws IOException {
        new LargestConsecutiveMultipleSubsequenceNative().run();
    }

    private void run() throws IOException {
        long start = System.currentTimeMillis();
        int[] array = input();
        long middle = System.currentTimeMillis();
        largestConsecutiveMultipleSubsequence(array);
        long end = System.currentTimeMillis();

        System.out.printf("generate n= %d, time= %d ms", n, middle - start);
        System.out.printf("\nresult n= %d, time= %d ms", n, end - middle);
    }

    private void largestConsecutiveMultipleSubsequence(int[] array) {
        int[] multipliers = new int[array.length];
        int[] previous = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            multipliers[i] = 1;
            previous[i] = -1;
            for (int j = 0; j < i; j++) {
                if (array[j] <= array[i] && array[i] % array[j] == 0 && multipliers[j] + 1 > multipliers[i]) {
                    multipliers[i] = multipliers[j] + 1;
                    previous[i] = j;
                }
            }
        }

//        System.out.println(Arrays.toString(multipliers));
//        System.out.println(Arrays.toString(previous));

//        OptionalInt optionalMax = Arrays.stream(multipliers).max();
//        int maxMultiple = optionalMax.isPresent() ? optionalMax.getAsInt() : 1;
        int maxMultiple = 1;
        int index = 0;
        for (int i = 0; i < multipliers.length; i++) {
            if (multipliers[i] > maxMultiple) {
                index = i;
                maxMultiple = multipliers[i];
            }
        }

        System.out.println(maxMultiple);

        int[] sequence = new int[maxMultiple];
        for (int i = maxMultiple - 1; i >= 0; i--) {
            sequence[i] = array[index];
            index = previous[index];
        }
        System.out.println(Arrays.toString(sequence));
    }

    private int[] input() throws IOException {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) Math.round(Math.random() * 1_000_000_000);
        }

//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(bufferedReader.readLine());
//        int[] array = new int[n];
//        int i = 0;
//        for (String value : bufferedReader.readLine().split("\\s+")) {
//            array[i] = Integer.parseInt(value);
//            i++;
//        }
//
//        System.out.println(Arrays.toString(array));
//        bufferedReader.close();
        return array;
    }
}
