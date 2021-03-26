package part_2_2;

public class LargeFibonacci {
    /**
     * Дано число 1≤n≤10, необходимо найти последнюю цифру n-го числа Фибоначчи.
     * <p>
     * Как мы помним, числа Фибоначчи растут очень быстро, поэтому при их вычислении нужно быть аккуратным
     * с переполнением. В данной задаче, впрочем, этой проблемы можно избежать, поскольку нас интересует только
     * последняя цифра числа Фибоначчи: если 0≤a,b≤9 — последние цифры чисел F{i} и F{i+1} соответственно,
     * то (a+b) mod 10 — последняя цифра числа F{i+2}
     */
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int n = scanner.nextInt();
        int f = 0;
        int temp = 1;
        for (int i = 0; i < n; i++) {
            f += temp;
            temp = (f - temp) % 10;
            f = f % 10;
        }
        System.out.println(f);
    }
    //    public static void main(String[] args) {
    //        // put your code here
    //        Scanner scaner = new Scanner(System.in);
    //        int num = scaner.nextInt();
    //        System.out.println(fib(num));
    //    }
    //
    //    private static long fib(double n){
    //        if(n <= 0) {
    //            return 0;
    //        }
    //        long a = 1;
    //        long b = 2;
    //        for (long i = 3; i <= n; i ++) {
    //            b = (a + (a = b)) % 10;
    //        }
    //        return a;
    //    }
}
