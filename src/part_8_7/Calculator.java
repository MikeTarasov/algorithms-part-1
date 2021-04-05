package part_8_7;

import java.util.Scanner;

public class Calculator {
    /**
     * У вас есть примитивный калькулятор, который умеет выполнять всего три операции с текущим числом x:
     * заменить x на 2*x, 3*x или x+1.
     * <p>
     * По данному целому числу 1 ≤ n ≤ 10^5 определите минимальное число операций k,
     * необходимое, чтобы получить n из 1.
     * Выведите k и последовательность промежуточных чисел.
     * <p>
     * Sample Input 1:
     * 1
     * Sample Output 1:
     * 0
     * 1
     * <p>
     * Sample Input 2:
     * 5
     * Sample Output 2:
     * 3
     * 1 2 4 5
     * <p>
     * Sample Input 3:
     * 96234
     * Sample Output 3:
     * 14
     * 1 3 9 10 11 22 66 198 594 1782 5346 16038 16039 32078 96234
     */
    private final int n = 96234;

    public static void main(String[] args) {
        new Calculator().run();
    }

    public void run() {
        int n = input();
        calculator(n);
    }

    private void calculator(int n) {
        if (n == 1) {
            System.out.println(0 + "\n" + 1);
            return;
        }
        int[] steps = new int[n + 1];
        int[] previous = new int[n + 1];

        for (int i = 1; i < n; i++) {
            fill(steps, previous, i, i * 3);
            fill(steps, previous, i, i * 2);
            fill(steps, previous, i, i + 1);
        }
        int minSteps = steps[n];
        System.out.println(minSteps);

        int[] result = new int[minSteps + 1];
        result[0] = 1;
        int j = minSteps;
        for (int i = n; previous[i] > 0; i = previous[i]) {
            result[j--] = i;
        }

        for (int k : result) {
            System.out.print(k + " ");
        }
    }

    private void fill(int[] steps, int[] previous, int i, int x) {
        if (x > n) {
            return;
        }
        if (steps[x] == 0 || steps[x] > steps[i] + 1) {
            steps[x] = steps[i] + 1;
            previous[x] = i;
        }
    }

    private int input() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        return n;
    }
}