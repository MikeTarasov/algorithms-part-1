package part_2_3;


public class GreatestCommonDivisor {
    /**
     * По данным двум числам 1<= a, b <= 10^9 найдите их наибольший общий делитель.
     * <p>
     * 18 35 -> 1
     * 14159572 63967072 -> 4
     */
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(greatestCommonDivisor(a, b));
    }

    private static int greatestCommonDivisor(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        if (a >= b) {
            return greatestCommonDivisor(a % b, b);
        }
        if (b >= a) {
            return greatestCommonDivisor(a, b % a);
        }
        return 1;
    }

    //    public static void main(String[] args) {
    //        try (Scanner s = new Scanner(System.in)) {
    //            long a = s.nextLong();
    //            long b = s.nextLong();
    //
    //            while (a > 0 && b > 0) {
    //                if (a > b)
    //                    a %= b;
    //                else
    //                    b %= a;
    //            }
    //
    //            System.out.println(Math.max(a, b));
    //        }
    //    }
}
