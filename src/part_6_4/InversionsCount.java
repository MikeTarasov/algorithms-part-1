package part_6_4;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InversionsCount {
    /**
     * Первая строка содержит число 1≤n≤10^5, вторая — массив A[1…n], содержащий натуральные числа,
     * не превосходящие 10^9.
     * <p>
     * Необходимо посчитать число пар индексов 1≤i<j≤n, для которых A[i]>A[j].
     * (Такая пара элементов называется инверсией массива.
     * Количество инверсий в массиве является в некотором смысле его мерой неупорядоченности:
     * например, в упорядоченном по не убыванию массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
     * инверсию образуют каждые два элемента.) = кол-во необходимых перестановок при упорядочивании
     * <p>
     * Используем сортировку слиянием - версия без рекурсии -> выводим число перестановок
     * <p>
     * Input:
     * 5
     * 2 3 9 2 9
     * <p>
     * Output:
     * 2
     */
    private static int inversionCount;

    public static void main(String[] args) {
        iterativeMergeSort();
    }

    public static void iterativeMergeSort() {
        List<Integer> left = input();
        long start = System.currentTimeMillis();

        ArrayList<List<Integer>> stack = new ArrayList<>(left.size() + 1);
        for (Integer number : left) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(number);
            stack.add(list);
        }

        inversionCount = 0;
        int stackSize = stack.size();

        while (stackSize > 1) {
            int i = stackSize;
            while (i > 1) {
                i -= 2;

                List<Integer> right = stack.get(i + 1);
                stack.remove(i + 1);
                stackSize -= 1;

                left = stack.get(i);

                int resultSize = right.size() + left.size();
                List<Integer> result = new ArrayList<>(resultSize);
                stack.set(i, result);

                int leftIndex = 0;
                int rightIndex = 0;
                for (int j = 0; j < resultSize; j++) {

                    int leftNumber = left.get(leftIndex);
                    int rightNumber = right.get(rightIndex);

                    if (leftNumber <= rightNumber) {
                        result.add(leftNumber);
                        leftIndex++;
                    } else {
                        result.add(rightNumber);
                        rightIndex++;
                        inversionCount++;
                    }

                    if (leftIndex == left.size()) {
                        addRemains(rightIndex, right, result);
                        break;
                    }
                    if (rightIndex == right.size()) {
                        addRemains(leftIndex, left, result);
                        break;
                    }
                }
            }
        }
        System.out.println("Iterative count= " + stack.get(0).size() + " time= " + (System.currentTimeMillis() - start) + " ms");
        System.out.println(inversionCount); // инверсию считает не корректно - используем рекурсивный алгоритм
        System.out.println(stack);          // сортирует правильно
    }

    private static void addRemains(int index, List<Integer> half, List<Integer> result) {
        half.stream().skip(index).forEach(result::add);
    }

    private static ArrayList<Integer> input() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> input = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            input.add(scanner.nextInt());
        }
        scanner.close();
        return input;
    }
}
