package part_4_2;

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
    private static final int count = 10_000;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        huffmanStringCoding();
        System.out.println("\nn= " + count + " time= " + (System.currentTimeMillis() - start) + " ms");
    }

    public static void huffmanStringCoding() {
        String input = input();

        List<Node> nodes = parseString(input);

        nodes.sort(Comparator.comparing(Node::getFrequency).reversed());

        Node rootNode = makeBinaryTree(nodes);
        fillingCodes(rootNode, "");

        String resultCode = codeString(nodes, input);

        System.out.println(nodes.size() + " " + resultCode.length());
        nodes.forEach(letter -> System.out.println(letter.getSymbol().concat(": ").concat(letter.getCode())));
        System.out.println(resultCode);
    }

    private static String input() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append((char) Math.round(97 + Math.random() * (122 - 97)));
        }
        return builder.toString();
//        return new Scanner(System.in).nextLine();
    }

    private static List<Node> parseString(String input) {
        List<Node> nodes = new ArrayList<>();
        for (char c : input.toCharArray()) {
            Node node = new Node(c);
            if (nodes.contains(node)) {
                node = nodes.get(nodes.indexOf(node));
                node.increaseFrequency();
            } else {
                nodes.add(node);
            }
        }
        return nodes;
    }

    private static Node makeBinaryTree(List<Node> nodes) {
        Set<Node> queue = new HashSet<>(nodes);
        Node rootNode = null;
        if (nodes.size() == 1) {
            rootNode = nodes.get(0);
        }
        while (queue.size() > 1) {
            Node first = getLowestElement(queue);
            Node second = getLowestElement(queue);
            Node nodeNode = new Node(first, second);
            queue.add(nodeNode);
            rootNode = nodeNode;
        }
        return rootNode;
    }

    private static Node getLowestElement(Set<Node> queue) {
        Node node = queue.stream().min((e1, e2) -> {
            int compare = Integer.compare(e1.getFrequency(), e2.getFrequency());
            if (compare == 0) {
                return Integer.compare(e1.getSymbol().length(), e2.getSymbol().length());
            }
            return compare;
        }).orElseThrow();
        queue.remove(node);
        return node;
    }

    private static void fillingCodes(Node node, String prefix) {
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

    private static String codeString(List<Node> nodes, String input) {
        Map<String, String> charCodes = new HashMap<>();
        for (Node node : nodes) {
            charCodes.put(node.getSymbol(), node.getCode());
        }

        StringBuilder builder = new StringBuilder();
        for (char c : input.toCharArray()) {
            builder.append(charCodes.get(c + ""));
        }
        return builder.toString();
    }

    static class Node {
        private final String symbol;
        private int frequency;
        private String code;
        private Node left;
        private Node right;

        public Node(char symbol) {
            this.symbol = String.valueOf(symbol);
            frequency = 1;
            code = "";
        }

        public Node(Node right, Node left) {
            symbol = right.getSymbol().concat(left.getSymbol());
            frequency = right.getFrequency() + left.getFrequency();
            this.left = left;
            this.right = right;
            code = "";
        }

        public String getSymbol() {
            return symbol;
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

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void increaseFrequency() {
            frequency++;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node1 = (Node) o;
            return symbol.equals(node1.symbol);
        }

        @Override
        public int hashCode() {
            return Objects.hash(symbol);
        }
    }
}