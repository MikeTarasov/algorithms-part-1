package part_2_2;


public class VeryLargeFibonacci {
    /**
     * Даны целые числа 1<=n<=10^18 и 2<=m<=10^5,
     * необходимо найти остаток от деления n-го числа Фибоначчи на m.
     * <p>
     * n=10 m=2 -> 1
     * n=1025 m=55 -> 5
     * n=12589 m=369 -> 89
     * n=1598753 m=25897 - 20305
     * <p>
     * Собираем в список остатки по модулю m до тех пор, пока не получим период. Он всегда начинается с 0,1
     * Искомый остаток будет n по модулю длины этого списка. Без особых замудрений, что в форуме ниже ;)
     * Например, n = 10, m = 4. Список остатков будет такой 0,1,1,2,3,1 так как дальше все время будет повторяться.
     * Теперь 10 по модулю 6(длина списка) = 4. А четвертый элемент списка это 3. Проверяем, 55 mod 4 = 3. Ок?
     */
    public static void main(String[] args) {
        String[] split = new java.util.Scanner(System.in).nextLine().split("\\s+");
        long n = Long.parseLong(split[0]);
        long m = Long.parseLong(split[1]);

        java.util.ArrayList<Long> list = new java.util.ArrayList<>();
        list.add(0L);
        list.add(1L);

        long previousReminder = -1L;
        long fi2 = 0; // F(i-2)
        long fi1 = 1; // F(i-1)
        long fi;      // F(i)
        for (int i = 2; i < 6 * m + 2; i++) {
            fi = (fi1 + fi2) % m;
            fi2 = fi1;
            fi1 = fi;

            long remainder = fi % m;

            if (remainder == 1 && previousReminder == 0) {
                list.remove(list.size() - 1);
                break;
            }

            list.add(remainder);
            previousReminder = remainder;
        }

        int index = (int) (n % list.size());
        Long result = list.get(index);

        System.out.println(result);
    }
    //            Scanner in = new Scanner(System.in);
    //            long n = in.nextLong();
    //            long m = in.nextLong();
    //            List<Long> list = new ArrayList<>();
    //            long a = 0;
    //            long b = 1;
    //            long c;
    //            list.add(a);
    //            list.add(b);
    //            for (long i = 0; i < n; i++) {
    //                c= b;
    //                b = (a+b) % m;
    //                a = c;
    //                if (a == 0 & b == 1) break;
    //                list.add(b);
    //            }
    //         System.out.println(list.get((int)(n % (list.size()-1))));


    //public static int pizzanoFibonacci(long n, int m) {
    //        int period = 1;
    //        int previous = 1;
    //        int current = 1;
    //        int buffer;
    //
    //        while (previous != 0 || current != 1) {
    //            buffer = current;
    //            current = (current + previous) % m;
    //            previous = buffer;
    //            period++;
    //        }
    //
    //        int border = (int) (n % period);
    //        for (int i = 1; i <= border; i++) {
    //            buffer = current;
    //            current = (current + previous) % m;
    //            previous = buffer;
    //        }
    //
    //        return previous;
    //    }


    //      static long someMethod(long n, long m) {
    //        int size = (int)m * 6;
    //        long[] arr = new long[size];
    //        arr[0] = 0;
    //        arr[1] = 1;
    //        long modSize = 1;
    //
    //        for (int i = 2; i < size; i++) {
    //            arr[i] = (arr[i - 1] + arr[i - 2]) % m;
    //            if ((arr[i] % m == 0) && ((arr[i] + arr[i - 1]) % m == 1)) {
    //                modSize = i;
    //                break;
    //            }
    //        }
    //        return arr[(int)(n % modSize)] % m;
    //    }
    //
    //    public static void main(String[] args) {
    //        Scanner sc = new Scanner(System.in);
    //        System.out.println(someMethod(sc.nextLong(), sc.nextLong()));
    //    }
}