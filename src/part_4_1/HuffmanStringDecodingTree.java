package part_4_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HuffmanStringDecodingTree {
    private static final Node rootNode = new Node();
    private static String codedString;

    public static void main(String[] args) {
        huffmanStringDecodingTree();
    }

    public static void huffmanStringDecodingTree() {
        List<Node> dictionary = input();
        generateTree(dictionary);

        StringBuilder builder = new StringBuilder();

        if (codedString.length() == 1) {
            builder.append(rootNode.getSymbol());

        } else {
            Node currentNode = rootNode;
            for (char c : codedString.toCharArray()) {
                if (c == '0') {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode = currentNode.getRight();
                }

                if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                    builder.append(currentNode.getSymbol());
                    currentNode = rootNode;
                }
            }
        }
        System.out.println(builder.toString());
    }

    private static List<Node> input() {
        Scanner scanner = new Scanner(System.in);

        int symbolsCount = scanner.nextInt();
        int codedStringLength = scanner.nextInt();
        scanner.nextLine();
        List<Node> dictionary = new ArrayList<>(symbolsCount);
        for (int i = 0; i < symbolsCount; i++) {
            String[] letterCodes = scanner.nextLine().replaceAll(":", "").split("\\s+");
            dictionary.add(new Node(letterCodes[0], letterCodes[1]));
        }
        codedString = scanner.nextLine();
        scanner.close();
        return dictionary;
    }

    private static void generateTree(List<Node> dictionary) {
        for (Node node : dictionary) {
            rootNode.addSymbol(node.getSymbol());
            Node currentNode = rootNode;
            for (char c : node.getCode().toCharArray()) {
                switch (c) {
                    case '0':
                        if (currentNode.getLeft() == null) {
                            Node newNode = new Node();
                            currentNode.setLeft(newNode);
                            currentNode = newNode;
                        } else {
                            currentNode = currentNode.getLeft();
                        }
                        break;
                    case '1':
                        if (currentNode.getRight() == null) {
                            Node newNode = new Node();
                            currentNode.setRight(newNode);
                            currentNode = newNode;
                        } else {
                            currentNode = currentNode.getRight();
                        }
                }
                currentNode.addSymbol(node.getSymbol());
                currentNode.setCode(node.getCode());
            }
        }
    }

    static class Node {
        private String symbol;
        private String code;
        private Node left;
        private Node right;

        public Node() {
            symbol = "";
            code = symbol;
        }

        public Node(String symbol, String code) {
            this.symbol = symbol;
            this.code = code;
        }

        public String getSymbol() {
            return symbol;
        }

        public void addSymbol(String add) {
            symbol = symbol.concat(add);
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

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}