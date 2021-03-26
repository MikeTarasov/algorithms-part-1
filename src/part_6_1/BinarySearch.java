package part_6_1;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinarySearch {
    /**
     * В первой строке даны целое число 1≤n≤10^5 и массив A[1…n] из n различных натуральных чисел, не превышающих 10^9,
     * в порядке возрастания, во второй — целое число 1≤k≤10^5 и k натуральных чисел, не превышающих 10^9.
     * <p>
     * Для каждого i от 1 до k необходимо вывести индекс 1≤j≤n, для которого A[j]=bi, или −1, если такого j нет.
     * <p>
     * Input:
     * 5 1 5 8 12 13
     * 5 8 1 23 1 11
     * <p>
     * Output:
     * 3 1 -1 1 -1
     */
    private static int[] sortedArray;
    private static int[] unsortedArray;

    public static void main(String[] args) {
        binarySearch();
    }

    public static void binarySearch() {
        input();
        List<Integer> results = new ArrayList<>(unsortedArray.length);
        for (int searched : unsortedArray) {
            int startIndex = 0;
            int endIndex = sortedArray.length - 1;
            while (true) {
                int middleIndex = (startIndex + endIndex) / 2;
                int compare = Integer.compare(searched, sortedArray[middleIndex]);
                if (compare == 0) {
                    results.add(middleIndex + 1);
                    break;
                }
                if (startIndex >= endIndex) {
                    results.add(-1);
                    break;
                }
                if (compare > 0) {
                    startIndex = middleIndex + 1;
                } else {
                    endIndex = middleIndex - 1;
                }
            }
        }
        results.forEach(e -> System.out.print(e + " "));
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        sortedArray = fillArray(scanner);
        unsortedArray = fillArray(scanner);
        scanner.close();
    }

    private static int[] fillArray(Scanner scanner) {
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }
}