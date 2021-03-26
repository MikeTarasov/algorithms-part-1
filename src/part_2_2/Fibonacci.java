package part_2_2;

public class Fibonacci {
    /**
     * Дано целое число 1≤n≤40, необходимо вычислить n-е число Фибоначчи
     */
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int n = scanner.nextInt();
        int f = 0;
        int temp = 1;
        for (int i = 0; i < n; i++) {
            f += temp;
            temp = f - temp;
        }
        System.out.println(f);
    }
}
