package part_4_1;

import java.util.*;

public class HuffmanStringCoding {
    /**
     * По данной непустой строке s длины не более 10^4, состоящей из строчных букв латинского алфавита,
     * постройте оптимальный беспрефиксный код.
     * <p>
     * В первой строке выведите количество различных букв k, встречающихся в строке,
     * и размер получившейся закодированной строки.
     * В следующих k строках запишите коды букв в формате "letter: code".
     * В последней строке выведите закодированную строку.
     * <p>
     * Input 2:
     * abacabad
     * <p>
     * Output 2:
     * 4 14
     * a: 0
     * b: 10
     * c: 110
     * d: 111
     * 01001100100111
     *
     * Test:
     * n= 1_000 time= 70 ms
     * n= 10_000 time= 97 ms -> x1.4
     * n= 100_000 time= 193 ms -> x2
     *
     * Test without print:
     * n= 1_000 time= 48 ms
     * n= 10_000 time= 82 ms -> x1.7
     * n= 100_000 time= 145 ms -> x1.8
     * n= 1_000_000 time= 475 ms -> x3.3
     * n= 10_000_000 time= 2_706 ms -> x5.7
     * n= 100_000_000 time= 30_143 ms -> x11.1
     */
    private static final int count = 5_000_000;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        huffmanStringCoding();
        System.out.println("\nn= " + count + " time= " + (System.currentTimeMillis() - start) + " ms");
    }

    public static void huffmanStringCoding() {
        String input = input();

        List<Letter> letters = parseString(input);

        letters.sort(Comparator.comparing(Letter::getFrequency).reversed());

        Letter rootNode = makeBinaryTree(letters);
        fillingCodes(rootNode, "");

        String resultCode = codeString(letters, input);

//        System.out.println(letters.size() + " " + resultCode.length());
//        letters.forEach(letter -> System.out.println(letter.getLetter().concat(": ").concat(letter.getCode())));
//        System.out.println(resultCode);
    }

    private static String input() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append((char) Math.round(97 + Math.random() * (122 - 97)));
        }
//        System.out.println(builder.toString());
        return builder.toString();
//        return new Scanner(System.in).nextLine();
    }

    private static List<Letter> parseString(String input) {
        List<Letter> letters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            Letter letter = new Letter(c);
            if (letters.contains(letter)) {
                letter = letters.get(letters.indexOf(letter));
                letter.increaseFrequency();
            } else {
                letters.add(letter);
            }
        }
        return letters;
    }

    private static Letter makeBinaryTree(List<Letter> letters) {
        Set<Letter> queue = new HashSet<>(letters);
        Letter rootNode = null;
        if (letters.size() == 1) {
            rootNode = letters.get(0);
        }
        while (queue.size() > 1) {
            Letter first = getLowestElement(queue);
            Letter second = getLowestElement(queue);
            Letter nodeLetter = new Letter(first, second);
            queue.add(nodeLetter);
            rootNode = nodeLetter;
        }
        return rootNode;
    }

    private static Letter getLowestElement(Set<Letter> queue) {
        Letter letter = queue.stream().min((e1, e2) -> {
            int compare = Integer.compare(e1.getFrequency(), e2.getFrequency());
            if (compare == 0) {
                return Integer.compare(e1.getLetter().length(), e2.getLetter().length());
            }
            return compare;
        }).orElseThrow();
        queue.remove(letter);
        return letter;
    }

    private static void fillingCodes(Letter node, String prefix) {
        if (node.getLeft() != null) {
            fillingCodes(node.getLeft(), prefix.concat("1"));
        }

        if (node.getRight() != null) {
            fillingCodes(node.getRight(), prefix.concat("0"));
        }

        if (node.getLeft() == node.getRight()) {
            if (prefix.isEmpty()) {
                prefix = "0";
            }
            node.setCode(prefix);
        }
    }

    private static String codeString(List<Letter> letters, String input) {
        Map<String, String> charCodes = new HashMap<>();
        for (Letter letter : letters) {
            charCodes.put(letter.getLetter(), letter.getCode());
        }

        StringBuilder builder = new StringBuilder();
        for (char c : input.toCharArray()) {
            builder.append(charCodes.get(c + ""));
        }
        return builder.toString();
    }

    static class Letter {
        private final String letter;
        private int frequency;
        private String code;
        private Letter left;
        private Letter right;

        public Letter(char letter) {
            this.letter = String.valueOf(letter);
            frequency = 1;
            code = "";
        }

        public Letter(Letter right, Letter left) {
            letter = right.getLetter().concat(left.getLetter());
            frequency = right.getFrequency() + left.getFrequency();
            this.left = left;
            this.right = right;
            code = "";
        }

        public String getLetter() {
            return letter;
        }

        public int getFrequency() {
            return frequency;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Letter getLeft() {
            return left;
        }

        public Letter getRight() {
            return right;
        }

        public void increaseFrequency() {
            frequency++;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Letter)) return false;
            Letter letter1 = (Letter) o;
            return letter.equals(letter1.letter);
        }

        @Override
        public int hashCode() {
            return Objects.hash(letter);
        }
    }
}