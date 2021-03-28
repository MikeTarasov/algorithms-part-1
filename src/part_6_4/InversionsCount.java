package part_6_4;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

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
     * <p>
     * Tests: * - приведение к размеру 2^n
     * <p>
     * Iterative ArrayList:   count = 1_000,     time = 17 ms
     * Iterative ArrayList:   count = 10_000,    time = 80 ms -> x4.7
     * Iterative ArrayList:   count = 100_000,   time = 787 ms -> x9.8
     * Iterative ArrayList:   count = 1_000_000, time = 33_429 ms -> x
     * <p>
     * Iterative LinkedList:  count = 1_000,     time = 7 ms
     * Iterative LinkedList:  count = 10_000,    time = 50 ms -> x7.1
     * Iterative LinkedList:  count = 100_000,   time = 271 ms -> x5.4
     * Iterative LinkedList:  count = 1_000_000, time = 810 ms -> x3
     * <p>
     * Iterative LinkedList*: count = 1_024,     time = 24 ms
     * Iterative LinkedList*: count = 16_384,    time = 83 ms -> x3.5
     * Iterative LinkedList*: count = 131_072,   time = 497 ms -> x6
     * Iterative LinkedList*: count = 1_048_576, time = 952 ms -> x
     * Iterative LinkedList*: count = 2_097_152, time = 2_643 ms -> x1.8
     * Iterative LinkedList*: count = 4_194_304, time = 6_490 ms -> x2.5
     * Iterative LinkedList*: count = 8_388_608, time = 13_852 ms -> x2.1
     * <p>
     * Recursive:             count = 1_000,     time = 1 ms
     * Recursive:             count = 10_000,    time = 3 ms -> x3
     * Recursive:             count = 100_000,   time = 57 ms -> x19
     * Recursive:             count = 1_000_000, time = 158 ms -> x2.7
     * <p>
     * Recursive:             count = 2_000_000, time = 391 ms -> x2.5
     * Recursive:             count = 4_000_000, time = 722 ms -> x1.8
     * Recursive:             count = 8_000_000, time = 1_386 ms -> x1.9
     */
    private static long inversionCount;

    public static void main(String[] args) {
        int n = 1_000_000;
        int[] array = new int[n];
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int random = (int) Math.round(Math.random() * 100);
            list.add(random);
            array[i] = random;
        }
        System.out.println("Input ready ");
        iterativeMergeSortStackArrayList(list);
        iterativeMergeSortStackLinkedList(list);

        inversionCount = 0;
        long start = System.currentTimeMillis();
        mergeSortRecursive(array, 0, n - 1);
        System.out.println("Recursive: count = " + n + ", time = " + (System.currentTimeMillis() - start) + " ms");
        System.out.println(inversionCount);
//        System.out.println(result);
    }


    private static int[] mergeSortRecursive(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            return merge(
                    mergeSortRecursive(array, left, middle),
                    mergeSortRecursive(array, middle + 1, right));
        }
        return new int[]{array[left]};
    }

    private static int[] merge(int[] leftArray, int[] rightArray) {
        int leftIndex = 0;
        int rightIndex = 0;
        int[] resultArray = new int[leftArray.length + rightArray.length];

        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {

            if (rightArray[rightIndex] < leftArray[leftIndex]) {

                inversionCount += leftArray.length - leftIndex;
                resultArray[leftIndex + rightIndex] = rightArray[rightIndex];
                rightIndex++;

            } else {

                resultArray[leftIndex + rightIndex] = leftArray[leftIndex];
                leftIndex++;
            }
        }

        if (leftIndex < leftArray.length) {
            System.arraycopy(leftArray, leftIndex,
                    resultArray, leftIndex + rightIndex, leftArray.length - leftIndex);
        } else {
            System.arraycopy(rightArray, rightIndex,
                    resultArray, leftIndex + rightIndex, rightArray.length - rightIndex);
        }

        return resultArray;
    }

    public static void iterativeMergeSortStackLinkedList(List<Integer> left) {

        long start = System.currentTimeMillis();
        inversionCount = 0;
        LinkedList<List<Integer>> stack = new LinkedList<>();

//        List<Integer> left = input();

        List<Integer> right;

        //приводим размер к 2^n
        int size = left.size();

        boolean isCorrect = true;
        int temp = size;
        while (true) {
            if (temp % 2 != 0) {
                isCorrect = false;
                break;
            }
            temp = temp / 2;
            if (temp == 1) {
                break;
            }
        }

        int n = isCorrect ? 0 : 1;
        while (size > 1) {
            size = size / 2;
            n++;
        }

        size = IntStream.rangeClosed(1, n).map(value -> 2).reduce(1, (a, b) -> a * b);


        for (Integer integer : left) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(integer);
            stack.add(list);
        }

        for (int i = left.size(); i < size; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(Integer.MAX_VALUE);
            stack.add(list);
        }

        while (stack.size() > 1) {

            left = stack.poll();
            right = stack.poll();

            int resultSize = right.size() + left.size();
            List<Integer> result = new ArrayList<>(resultSize);
            stack.add(result);

            int leftIndex = 0;
            int rightIndex = 0;
            for (int j = 0; j < resultSize; j++) {

                int leftNumber = left.get(leftIndex);
                int rightNumber = right.get(rightIndex);

                if (leftNumber > rightNumber) {
                    inversionCount += left.size() - leftIndex;
                    result.add(rightNumber);
                    rightIndex++;
                } else {
                    result.add(leftNumber);
                    leftIndex++;
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

        // инверсию считает корректно только при размере исходного массива 2^n
        System.out.println("Iterative LinkedList: count = " + stack.get(0).size() + ", time = " + (System.currentTimeMillis() - start) + " ms");
        System.out.println(inversionCount);
//        System.out.println(stack);          // сортирует правильно
    }

    public static void iterativeMergeSortStackArrayList(List<Integer> left) {
//        List<Integer> left = input();
        List<Integer> right;
        long start = System.currentTimeMillis();

        ArrayList<List<Integer>> stack = new ArrayList<>(left.size());
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

                right = stack.get(i + 1);
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

                    if (leftNumber > rightNumber) {
                        inversionCount += left.size() - leftIndex;
                        result.add(rightNumber);
                        rightIndex++;
                    } else {
                        result.add(leftNumber);
                        leftIndex++;
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
        System.out.println("Iterative ArrayList: count = " + stack.get(0).size() + ", time = " + (System.currentTimeMillis() - start) + " ms");
        System.out.println(inversionCount); // инверсию считает корректно
//        System.out.println(stack);          // сортирует правильно
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
