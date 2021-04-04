package part_8_7;

import java.util.Scanner;

public class Stairs {
    /**
     * Даны число 1 ≤ n ≤ 10^2 ступенек лестницы и целые числа -10^4 ≤  a1, ..., an  ≤ 10^4,
     * которыми помечены ступеньки.
     * Найдите максимальную сумму, которую можно получить, идя по лестнице снизу вверх
     * (от нулевой до nn-й ступеньки), каждый раз поднимаясь на одну или две ступеньки.
     */
    public static void main(String[] args) {
        new Stairs().run();
    }

    public void run() {
        int[] array = input();
        int result = stairs(array);
        System.out.println(result);
    }

    private int stairs(int[] array) {
        int length = array.length;
        if (length == 1) {
            return array[0];
        }

        int sum2f = 0;
        int sum2s = array[0];

        for (int i = 1; i < length; i++) {
            int sum2i = Math.max(sum2f + array[i], sum2s + array[i]);
            sum2f = sum2s;
            sum2s = sum2i;
        }

        return sum2s;
    }

    private int[] input() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        scanner.close();
        return array;
    }
}