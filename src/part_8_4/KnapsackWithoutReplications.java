package part_8_4;

import java.util.Arrays;

public class KnapsackWithoutReplications {
    /**
     * Первая строка входа содержит целые числа 1 ≤ W ≤ 10^4 и 1 ≤ n ≤ 300 — вместимость рюкзака и
     * число золотых слитков.
     * Следующая строка содержит nn целых чисел 0 ≤ w1, ..., wn ≤ 10^5, задающих веса слитков.
     * Найдите максимальный вес золота, который можно унести в рюкзаке.
     * <p>
     * Input:
     * 10 3
     * 1 4 8
     * <p>
     * Output:
     * 9
     *
     * Tests:
     *
     * W=   1_000, n=   1_000, time=       1 ms
     * W=  10_000, n=   1_000, time=       4 ms
     * W=  10_000, n=  10_000, time=      24 ms
     * W=  10_000, n= 100_000, time=     115 ms
     * W= 100_000, n=  10_000, time=     717 ms
     * W= 100_000, n=  10_000, time=   1_661 ms
     * W= 100_000, n= 100_000, time=   5_273 ms
     * W= 100_000, n= 200_000, time=   8_994 ms
     * W= 100_000, n= 100_000, time=  11_749 ms
     * W= 200_000, n= 100_000, time=  34_875 ms
     * W= 200_000, n= 200_000, time=  32_057 ms
     * W= 200_000, n= 200_000, time=  34_670 ms
     * W= 400_000, n= 200_000, time=  75_320 ms
     * W= 400_000, n= 400_000, time= 141_242 ms
     *
     */
    private final int knapsackCapacity = 100_000;
    private final int goldQuantity = 10_000;


    public static void main(String[] args) {
        new KnapsackWithoutReplications().run();
    }

    public void run() {
        int[] weights = input();
        long start = System.currentTimeMillis();
        int count = knapsackWithoutReplicationsCount(weights);
        System.out.println("W= " + knapsackCapacity + ", n= " + goldQuantity + ", time= " + (System.currentTimeMillis() - start) + " ms");
        System.out.println(count);
    }

    private int knapsackWithoutReplicationsCount(int[] weights) {
        int[] full = new int[knapsackCapacity + 1];
        //пустой можем заполнить без извлечения элементов
        full[0] = 1;
        int marker = 0;
        for (int weight : weights) {
            marker = Math.min(marker + weight, knapsackCapacity);
            for (int i = marker; i >= weight; i--) {  //уникальный набор - перебор с конца!
                if (full[i - weight] == 1) {          //для повторяющихся перебор с начала!
                    full[i] = 1;
                }
            }
        }

        marker = knapsackCapacity;
        while (full[marker] == 0) {
            marker--;
        }
        return marker;
    }


    private int[] input() {
//        Scanner scanner = new Scanner(System.in);
//
//        knapsackCapacity = scanner.nextInt();
//        int goldQuantity = scanner.nextInt();
        int[] weights = new int[goldQuantity];
//        scanner.nextLine();
//        for (int i = 0; i < goldQuantity; i++) {
//            weights[i] = scanner.nextInt();
//        }
//        scanner.close();

        for (int i = 0; i < goldQuantity; i++) {
            weights[i] = (int) Math.round(Math.random() * 100_000);
        }
        Arrays.sort(weights);
        return weights;
    }
}