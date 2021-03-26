package part_4_1;

import java.util.*;

public class HuffmanStringDecoding {
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
     */
    private static final Set<Dictionary> dictionary = new HashSet<>();
    private static String codedString;

    public static void main(String[] args) {
        huffmanStringDecoding();
    }

    public static void huffmanStringDecoding() {
        input();

        char[] codedChars = codedString.toCharArray();
        String matrix = "";
        StringBuilder builder = new StringBuilder();
        for (char codedChar : codedChars) {
            matrix = matrix.concat(String.valueOf(codedChar));
            String finalMatrix = matrix;
            Optional<Dictionary> optional = dictionary.stream().filter(e -> e.getCode().equals(finalMatrix)).findFirst();
            if (optional.isPresent()) {
                matrix = "";
                builder.append(optional.get().getSymbol());
            }
        }

        System.out.println(builder.toString());
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);

        int symbolsCount = scanner.nextInt();
        int codedStringLength = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < symbolsCount; i++) {
            String[] letterCodes = scanner.nextLine().replaceAll(":", "").split("\\s+");
            dictionary.add(new Dictionary(letterCodes[0], letterCodes[1]));
        }
        codedString = scanner.nextLine();
        scanner.close();
    }

    static class Dictionary {
        private final String symbol;
        private final String code;

        public Dictionary(String symbol, String code) {
            this.symbol = symbol;
            this.code = code;
        }

        public String getSymbol() {
            return symbol;
        }

        public String getCode() {
            return code;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Dictionary)) return false;
            Dictionary that = (Dictionary) o;
            return symbol.equals(that.symbol);
        }

        @Override
        public int hashCode() {
            return Objects.hash(symbol);
        }
    }
}