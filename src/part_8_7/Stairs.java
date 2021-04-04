package part_8_7;

public class Stairs {
    /**
     * Даны число 1 ≤ n ≤ 10^2 ступенек лестницы и целые числа -10^4 ≤  a1, ..., an  ≤ 10^4,
     * которыми помечены ступеньки.
     * Найдите максимальную сумму, которую можно получить, идя по лестнице снизу вверх
     * (от нулевой до nn-й ступеньки), каждый раз поднимаясь на одну или две ступеньки.
     * <p>
     * Tests:
     * n=       1_000, time=   0 ms
     * n=      10_000, time=   1 ms
     * n=     100_000, time=   5 ms
     * n=   1_000_000, time=  12 ms
     * n=  10_000_000, time=  26 ms
     * n= 100_000_000, time= 137 ms
     * n= 200_000_000, time= 267 ms
     * n= 400_000_000, time= 556 ms
     */
    private final int n = 400_000_000;

    public static void main(String[] args) {
        new Stairs().run();
    }

    public void run() {
        int[] array = input();
        long start = System.currentTimeMillis();
        long result = stairs(array);
        System.out.println("n= " + n + ", time= " + (System.currentTimeMillis() - start) + " ms");
        System.out.println(result);
    }

    private long stairs(int[] array) {
        int length = array.length;
        if (length == 1) {
            return array[0];
        }

        long sum2f = 0;
        long sum2s = array[0];

        for (int i = 1; i < length; i++) {
            long sum2i = Math.max(sum2f + array[i], sum2s + array[i]);
            sum2f = sum2s;
            sum2s = sum2i;
        }

        return sum2s;
    }

    private int[] input() {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) Math.round(Math.random() * 20_000 - 10_000);
        }
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        scanner.nextLine();
//        int[] array = new int[n];
//        for (int i = 0; i < n; i++) {
//            array[i] = scanner.nextInt();
//        }
//        scanner.close();
        return array;
    }
}