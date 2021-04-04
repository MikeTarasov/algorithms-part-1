package part_8_4;

import java.util.Scanner;

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
     */
    private int knapsackCapacity;


    public static void main(String[] args) {
        new KnapsackWithoutReplications().run();
    }

    public void run() {
        int[] weights = input();
        int count = knapsackWithoutReplicationsCount(weights);
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
        Scanner scanner = new Scanner(System.in);

        knapsackCapacity = scanner.nextInt();
        int goldQuantity = scanner.nextInt();
        int[] weights = new int[goldQuantity];
        scanner.nextLine();
        for (int i = 0; i < goldQuantity; i++) {
            weights[i] = scanner.nextInt();
        }
        scanner.close();
        return weights;
    }
}