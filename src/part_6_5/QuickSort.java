package part_6_5;


import java.util.Arrays;
import java.util.HashMap;

public class QuickSort {
    private final int n = 1_000;     //50_000
    private final int m = 1_000;     //50_000
    /**
     * В первой строке задано два целых числа 1≤n≤50_000 и 1≤m≤50_000 — количество отрезков и
     * точек на прямой, соответственно.
     * Следующие n строк содержат по два целых числа a_i и b_i (a_i≤b_i) — координаты концов отрезков.
     * Последняя строка содержит m целых чисел — координаты точек.
     * Все координаты не превышают 10^8 по модулю.
     * Точка считается принадлежащей отрезку, если она находится внутри него или на границе.
     * Для каждой точки в порядке появления во вводе выведите, скольким отрезкам она принадлежит.
     * <p>
     * Input:
     * 2 3
     * 0 5
     * 7 10
     * 1 6 11
     * <p>
     * Output:
     * 1 0 0
     * <p>
     * 1. Заведите массив Х с началами отрезков и отсортируйте быстрой сортировкой
     * 2. Повторите шаг 1 для концов отрезков - массив У
     * 3. Напишите 2 функции для искомой точки А:
     * 1-я будет искать в массиве Х все элементы, которые меньше или равны(!) А и возвращать их количество N
     * 2-я будет искать в массиве У все элементы, которые строго (!) меньше А и возвращать их количество M
     * 4.  В цикле прогоните каждую точку из списка через обе функции -  ответом будет разность N - M
     * <p>
     * Tests on sorted descending data:
     * n= 1_000,      sort time= 1 ms,      points count= 1_000,   points time= 8 ms
     * n= 10_000,     sort time= 2 ms,      points count= 10_000,  points time= 31 ms
     * n= 50_000,     sort time= 15 ms,     points count= 50_000,  points time= 58 ms
     * <p>
     * n= 100_000,    sort time= 28 ms,     points count= 50_000,  points time= 65 ms
     * n= 1_000_000,  sort time= 91 ms,     points count= 50_000,  points time= 139 ms
     * n= 10_000_000, sort time= 821 ms,    points count= 50_000,  points time= 892 ms
     * <p>
     * n= 20_000_000, sort time= 1_673 ms,  points count= 50_000,   points time= 1_750 ms
     * n= 40_000_000, sort time= 3_835 ms,  points count= 50_000,   points time= 3_914 ms
     * n= 80_000_000, sort time= 6_103 ms,  points count= 50_000,   points time= 6_220 ms
     * <p>
     * n= 80_000_000, sort time= 6_832 ms,  points count= 100_000,  points time= 6_953 ms
     * n= 80_000_000, sort time= 6_115 ms,  points count= 500_000,  points time= 6_279 ms
     * <p>
     * Test on random data:
     * n= 1_000,      sort time= 1 ms,      points count= 1_000,   points time= 8 ms
     * n= 10_000,     sort time= 2 ms,      points count= 10_000,  points time= 17 ms
     * n= 50_000,     sort time= 10 ms,     points count= 50_000,  points time= 31 ms
     * <p>
     * n= 100_000,    sort time= 12 ms,     points count= 50_000,  points time= 34 ms
     * n= 1_000_000,  sort time= 67 ms,     points count= 50_000,  points time= 137 ms
     * n= 10_000_000, sort time= 498 ms,    points count= 50_000,   points time= 649 ms
     * <p>
     * n= 20_000_000, sort time= 952 ms,    points count= 50_000,   points time= 1_038 ms
     * n= 40_000_000, sort time= 1_727 ms,  points count= 50_000,   points time= 1_837 ms
     * n= 80_000_000, sort time= 3_625 ms,  points count= 50_000,   points time= 3_781 ms
     * n= 80_000_000, sort time= 3_510 ms,  points count= 500_000,  points time= 3_676 ms
     */
    private int[] startSegments;
    private int[] endSegments;
    private int[] points;
    private int[] sortedPoints;

    public static void main(String[] args) {
        new QuickSort().run();
    }

    private void input() {

        startSegments = new int[n];
        endSegments = new int[n];
        points = new int[m];

        for (int i = 0; i < n; i++) {
            startSegments[i] =
//                    100_000_000 - i;
                    (int) Math.round(Math.random() * 100);
            endSegments[i] =
//                    100_000_000 - i + 1;
                    startSegments[i] + (int) Math.round(Math.random() * 100);
        }
        for (int i = 0; i < m; i++) {
            points[i] =
//                    100_000_000 - i;
                    (int) Math.round(Math.random() * 300);
        }

//        Scanner scanner = new Scanner(System.in);
//
//        int countSegments = scanner.nextInt();
//        int countPoints = scanner.nextInt();
//
//        startSegments = new int[countSegments];
//        endSegments = new int[countSegments];
//        points = new int[countPoints];
//
//        for (int i = 0; i < countSegments; i++) {
//            startSegments[i] = scanner.nextInt();
//            endSegments[i] = scanner.nextInt();
//        }
//        for (int i = 0; i < countPoints; i++) {
//            points[i] = scanner.nextInt();
//        }
//
//        scanner.close();
    }

    public void run() {
        input();
        long start = System.currentTimeMillis();

        quickSortRecursive(startSegments, 0, startSegments.length - 1);
        quickSortRecursive(endSegments, 0, endSegments.length - 1);
        sortedPoints = Arrays.copyOf(points, points.length);
        quickSortRecursive(sortedPoints, 0, sortedPoints.length - 1);
        long sortTime = (System.currentTimeMillis() - start) / 3;

        pointsInSegments();
        System.out.println();
        System.out.println("n= " + n + ", sort time= " + sortTime + " ms, points count= " + m + " points time= " + (System.currentTimeMillis() - sortTime * 2 - start) + " ms");

//        System.out.println(Arrays.toString(startSegments));
//        System.out.println(Arrays.toString(endSegments));
//        System.out.println(Arrays.toString(points));
    }

    private void pointsInSegments() {
        HashMap<Integer, Indexes> map = new HashMap<>(points.length);
        for (int point : points) {
            map.put(point, new Indexes());
        }

        for (int i = 0; i < sortedPoints.length; i++) {

            Indexes currentIndexes = map.get(sortedPoints[i]);
            if (currentIndexes.getStartIndex() > 0) {
                continue;
            }

            Indexes previousIndexes = i > 0 ? map.get(sortedPoints[i - 1]) : currentIndexes;

            int startIndex = previousIndexes.getStartIndex();
            for (int j = startIndex; j < startSegments.length; j++) {
                if (startSegments[j] <= sortedPoints[i]) {
                    startIndex++;
                    continue;
                }
                break;
            }
            currentIndexes.setStartIndex(startIndex);

            int endIndex = previousIndexes.getEndIndex();
            for (int j = endIndex; j < endSegments.length; j++) {
                if (endSegments[j] < sortedPoints[i]) {
                    endIndex++;
                    continue;
                }
                break;
            }
            currentIndexes.setEndIndex(endIndex);
        }

        for (int point : points) {
            System.out.print(map.get(point).getPointsCount() + " ");
        }
    }

    private void quickSortRecursive(int[] array, int left, int right) {
        if (left < right) {
            int[] middle = quickSort(array, left, right);
            quickSortRecursive(array, left, middle[0] - 1);
            quickSortRecursive(array, middle[1] + 1, right);
        }
    }

    private int[] quickSort(int[] array, int left, int right) {
        int[] referenceIndexes = new int[2];
        referenceIndexes[0] = left;
        referenceIndexes[1] = left;

        int length = right - left + 1;
        if (length < 3) {
            if (length == 2 && array[left] > array[right]) {
                switchElements(array, left, right);
                if (array[left] == array[right]) {
                    referenceIndexes[1] = right;
                }
            }
            return referenceIndexes;
        }

        int[] equals = new int[length];
        int[] less = new int[length];
        int[] more = new int[length];

        int middle = (left + right) / 2;
        switchElements(array, left, middle);
        equals[0] = array[left];

        int equalsIndex = 0;
        int lessIndex = 0;
        int moreIndex = 0;
        int reference = equals[0];
        for (int i = left + 1; i <= right; i++) {
            int current = array[i];
            if (current > reference) {
                more[moreIndex] = current;
                moreIndex++;
            } else if (current < reference) {
                less[lessIndex] = current;
                lessIndex++;
            } else {
                equalsIndex++;
                equals[equalsIndex] = current;
            }
        }

        referenceIndexes[0] = left + lessIndex;
        referenceIndexes[1] = referenceIndexes[0] + equalsIndex;

        System.arraycopy(less, 0, array, left, lessIndex);
        System.arraycopy(equals, 0, array, referenceIndexes[0], equalsIndex + 1);
        System.arraycopy(more, 0, array, referenceIndexes[1] + 1, moreIndex);

        return referenceIndexes;
    }

    private void switchElements(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    static class Indexes {
        private int startIndex;
        private int endIndex;

        public Indexes() {
        }

        public int getStartIndex() {
            return startIndex;
        }

        public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }

        public void setEndIndex(int endIndex) {
            this.endIndex = endIndex;
        }

        public int getPointsCount() {
            return startIndex - endIndex;
        }
    }
}