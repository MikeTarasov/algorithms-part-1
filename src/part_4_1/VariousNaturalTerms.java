package part_4_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VariousNaturalTerms {
    /**
     * По данному числу 1≤n≤10^9 найдите максимальное число k, для которого nn можно представить как сумму k
     * различных натуральных слагаемых. Выведите в первой строке число k, во второй — k слагаемые.
     * <p>
     * 1 = 1,
     * 2 = 2,
     * 1+2 = 3,
     * 1+3 = 4,
     * 1+4 = 5,
     * 1+2+3 = 6,
     * 1+2+4 = 7,
     * 1+2+5 = 8,
     * 1+2+6 = 9,
     * 1+2+3+4 = 10,
     * 1+2+3+5 = 11,
     * 1+2+3+6 = 12,
     * 1+2+3+7 = 13,
     * 1+2+3+8 = 14,
     * 1+2+3+4+5 = 15
     * <p>
     * Input 1:
     * 4
     * Output 1:
     * 2
     * 1 3
     */
    public static void main(String[] args) {
        variousNaturalTerms();
    }

    private static void variousNaturalTerms() {
        int n = input();
        int remainder = n;
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (remainder - i >= i + 1) {
                result.add(i);
                remainder -= i;
            } else {
                result.add(remainder);
                break;
            }
        }
        System.out.println(result.size());
        result.forEach(e -> System.out.print(e + " "));
    }

    private static int input() {
        return new Scanner(System.in).nextInt();
    }
}