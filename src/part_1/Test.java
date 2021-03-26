package part_1;

import java.util.Arrays;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        /**
         * В данной задаче требуется вычислить сумму двух входных целых чисел, лежащих в отрезке от нуля до десяти.
         * Никаких подвохов, это очевидная задача, предназначенная для того, чтобы познакомить вас с проверяющей
         * системой. На следующем степе приведены решения данной задачи на нескольких языках программирования
         * (вы можете прямо сейчас перейти туда и скопировать решение оттуда). В этой задаче, как и во всех задачах
         * на программирование, не нужно проверять, что входные данные удовлетворяют требованиям, заявленным в условии.
         * Другими словами, во всех тестах, на которых будет проверяться ваша программа, на вход будут подаваться два
         * целых числа от 0 до 10.
         */
        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();
//        String[] split = input.split("\\s+");
//        System.out.println(Arrays.stream(split).map(Integer::parseInt).reduce(0, Integer::sum));

        System.out.println(Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).reduce(0, Integer::sum));
    }
}
