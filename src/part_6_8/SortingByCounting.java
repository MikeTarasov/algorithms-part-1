package part_6_8;

public class SortingByCounting {
    /**
     * Первая строка содержит число 1≤n≤10^4, вторая — n натуральных чисел, не превышающих 10.
     * <p>
     * Выведите упорядоченную по не убыванию последовательность этих чисел.
     * <p>
     * Input:
     * 5
     * 2 3 9 2 9
     * <p>
     * Output:
     * 2 2 3 9 9
     * <p>
     * Test:
     * n= 1_000, time= 0 ms
     * n= 10_000, time= 1 ms
     * n= 100_000, time= 6 ms
     * n= 1_000_000, time= 18 ms
     * n= 10_000_000, time= 83 ms
     * n= 100_000_000, time= 801 ms
     * n= 200_000_000, time= 7_797 ms
     */
    private final int n = 200_000_000;

    public static void main(String[] args) {
        new SortingByCounting().start();
    }

    public void start() {
        int[] array = input();

        long start = System.currentTimeMillis();
        array = sortingByCounting(array);

        System.out.println("n= " + n + ", time= " + (System.currentTimeMillis() - start) + " ms");
//        print(array);
    }

    private int[] sortingByCounting(int[] input) {
        int length = input.length;
        int[] sorted = new int[length];
        //у нас числа от 0 до 10 -> число = индексу
        int[] digits = new int[11];

        //считаем число вхождений чисел
        for (int digit : input) {
            digits[digit] += 1;
        }

        //считаем последний индекс каждого числа
        int sum = digits[0];
        for (int i = 1; i < digits.length; i++) {
            if (digits[i] > 0) {
                sum += digits[i];
                digits[i] = sum;
            }
        }

        //расставляем числа по порядку
        for (int i = length - 1; i >= 0; i--) {
            int digit = input[i];
            digits[digit] -= 1;
            int index = digits[digit];
            sorted[index] = digit;
        }

        return sorted;
    }

    private int[] input() {
        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = (int) Math.round(Math.random() * 10);
        }
//        Scanner scanner = new Scanner(System.in);
//
//        int n = scanner.nextInt();
//        scanner.nextLine();
//        int[] input = new int[n];
//        for (int i = 0; i < n; i++) {
//            input[i] = scanner.nextInt();
//        }
        return input;
    }

    private void print(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
    }
}