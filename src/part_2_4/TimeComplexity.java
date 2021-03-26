package part_2_4;

import java.math.BigDecimal;
import java.util.stream.LongStream;

import static java.lang.Math.log;
import static java.lang.Math.pow;

public class TimeComplexity {
    public static void main(String[] args) {
        double n = pow(2, 8);
        System.out.println(log(log(n)));
        System.out.println(pow(log(n), 0.5));
        System.out.println(log(n));
        System.out.println(pow(log(n), 2));
        System.out.println(pow(n, 0.5));

        System.out.println();

        System.out.println(n / (log(n)));
        System.out.println(pow(3, log(n)));
        System.out.println(pow(7, log(n)));
        System.out.println(n * n);
        System.out.println(pow(log(n), log(n)));

        System.out.println();

//        System.out.println(log(getFactorial(Math.round(n)))); //???????
        System.out.println(pow(n, log(n)));
        System.out.println(pow(n, pow(n, 0.5)));
        System.out.println(pow(2, n));
        System.out.println(pow(4, n));

        System.out.println();


        System.out.println(pow(2, 3 * n));
//        System.out.println(getFactorial((Math.round(n)))); //?????
        System.out.println(pow(2, pow(2, n)));
    }

    public static Long getFactorial(long f) {
        if (f < 2) {
            return 1L;
        } else {
            return LongStream.rangeClosed(2, f).mapToObj(BigDecimal::valueOf).reduce(BigDecimal::multiply).get().longValue();
        }
    }
}
