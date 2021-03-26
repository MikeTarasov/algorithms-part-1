package part_4_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HuffmanStringDecodingDictionary {
    /**
     * Восстановите строку по её коду и беспрефиксному коду символов.
     * <p>
     * В первой строке входного файла заданы два целых числа k и l через пробел — количество различных букв,
     * встречающихся в строке, и размер получившейся закодированной строки, соответственно.
     * В следующих k строках записаны коды букв в формате "letter: code".
     * Ни один код не является префиксом другого. Буквы могут быть перечислены в любом порядке.
     * В качестве букв могут встречаться лишь строчные буквы латинского алфавита;
     * каждая из этих букв встречается в строке хотя бы один раз.
     * Наконец, в последней строке записана закодированная строка.
     * Исходная строка и коды всех букв не пусты.
     * Заданный код таков, что закодированная строка имеет минимальный возможный размер.
     * <p>
     * В первой строке выходного файла выведите строку s. Она должна состоять из строчных букв латинского алфавита.
     * Гарантируется, что длина правильного ответа не превосходит 10^4 символов.
     * <p>
     * Input 1:
     * 1 1
     * a: 0
     * 0
     * Output 1:
     * a
     * <p>
     * Input 2:
     * 4 14
     * a: 0
     * b: 10
     * c: 110
     * d: 111
     * 01001100100111
     * Output 2:
     * abacabad
     * <p>
     * Test x10 middle time:
     * n= 1_000 time= 61 ms
     * n= 10_000 time= 126 ms -> x2.1
     * n= 100_000 time= 309 ms -> x2.5
     */
    private static final int count = 100_000;
    private static final Map<String, Character> dictionary = new HashMap<>(40);
    private static final String testFile = "src/part_4_2/tests/n" + count + ".txt";
    private static String codedString;

    public static void main(String[] args) throws FileNotFoundException {
        long start = System.currentTimeMillis();
        huffmanStringDecoding();
        System.out.println("\nn= " + count + " time= " + (System.currentTimeMillis() - start) + " ms");
    }

    public static void huffmanStringDecoding() throws FileNotFoundException {
        input();

        StringBuilder matrix = new StringBuilder();
        StringBuilder builder = new StringBuilder();

        for (char codedChar : codedString.toCharArray()) {

            matrix.append(codedChar);

            if (dictionary.containsKey(matrix.toString())) {
                builder.append(dictionary.get(matrix.toString()));
                matrix = new StringBuilder();
            }
        }
        System.out.println(builder.toString());
    }

    private static void input() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(testFile));
        scanner.nextLine();
//        Scanner scanner = new Scanner(System.in);

        int symbolsCount = scanner.nextInt();
        int codedStringLength = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < symbolsCount; i++) {
            String[] letterCodes = scanner.nextLine().replaceAll(":", "").split("\\s+");
            dictionary.put(letterCodes[1], letterCodes[0].charAt(0));
        }
        codedString = scanner.nextLine();
        scanner.close();
    }
}