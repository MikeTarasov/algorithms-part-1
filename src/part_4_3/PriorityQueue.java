package part_4_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PriorityQueue {
    /**
     * Первая строка входа содержит число операций 1≤n≤10^5.
     * Каждая из последующих n строк задают операцию одного из следующих двух типов:
     * <p>
     * Insert x, где 0≤x≤10^9 — целое число;
     * ExtractMax.
     * Первая операция добавляет число x в очередь с приоритетами, вторая — извлекает максимальное число и выводит его.
     * <p>
     * Input:
     * 6
     * Insert 200
     * Insert 10
     * ExtractMax
     * Insert 5
     * Insert 500
     * ExtractMax
     * <p>
     * Output:
     * 200
     * 500
     * <p>
     * Binary Tree {1,2,3,4,5,6,7} -> Array {0,1,2,3,4,5,6}
     * max ->            1
     * 2       3
     * min ->      4   5   6   7
     */
    private static final List<Integer> queue = new ArrayList<>();

    public static void main(String[] args) {
        priorityQueue();
    }

    public static void priorityQueue() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        for (int i = 0; i < n; i++) {
            String command = scanner.nextLine();
            if (command.equals("ExtractMax")) {
                extractMax();
            } else {
                insert(command);
            }
        }
        scanner.close();
    }

    private static void extractMax() {
        if (queue.size() > 1) {
            Integer max = queue.get(0);
            int lastIndex = queue.size() - 1;
            shift(0, lastIndex);
            queue.remove(lastIndex);
            validateInsert(0);
            System.out.println(max);
        } else if (queue.size() == 1) {
            System.out.println(queue.get(0));
            queue.remove(0);
        }
    }

    private static void insert(String command) {
        int number = Integer.parseInt(command.substring(7));
        queue.add(number);
        validateInsert(queue.size() - 1);
    }

    private static void validateInsert(int index) {
        int current = queue.get(index);

        int parentIndex = (index + 1) / 2 - 1;  // i/2
        if (isIndexPresent(parentIndex) && queue.get(parentIndex) < current) {
            shiftUp(index, parentIndex);
            return;
        }

        int leftIndex = 2 * (index + 1) - 1;    // 2*i
        int rightIndex = 2 * (index + 1);       // 2*i+1

        boolean isLeftPresent = isIndexPresent(leftIndex);
        boolean isRightPresent = isIndexPresent(rightIndex);

        if ((isLeftPresent && current < queue.get(leftIndex)) || (isRightPresent && current < queue.get(rightIndex))) {

            if (isLeftPresent && isRightPresent) {
                shiftDown(queue.get(leftIndex) > queue.get(rightIndex) ? leftIndex : rightIndex, index);
            } else if (isLeftPresent) {
                shiftDown(leftIndex, index);
            } else {
                shiftDown(rightIndex, index);
            }
        }
    }

    private static boolean isIndexPresent(int index) {
        return index < queue.size() && index >= 0;
    }

    private static void shiftUp(int indexChild, int indexParent) {
        shift(indexChild, indexParent);
        validateInsert(indexParent);
    }

    private static void shiftDown(int indexChild, int indexParent) {
        shift(indexChild, indexParent);
        validateInsert(indexChild);
    }

    private static void shift(int indexChild, int indexParent) {
        int temp = queue.get(indexChild);
        queue.set(indexChild, queue.get(indexParent));
        queue.set(indexParent, temp);
    }
}