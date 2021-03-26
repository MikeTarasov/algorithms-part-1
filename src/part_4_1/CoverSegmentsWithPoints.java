package part_4_1;

import java.util.*;

public class CoverSegmentsWithPoints {
    /**
     * По данным n отрезкам необходимо найти множество точек минимального размера,
     * для которого каждый из отрезков содержит хотя бы одну из точек.
     * <p>
     * В первой строке дано число 1≤n≤100 отрезков.
     * Каждая из последующих nn строк содержит по два числа 0≤l≤r≤10^9, задающих начало и конец отрезка.
     * <p>
     * Выведите оптимальное число m точек и сами m точек.
     * Если таких множеств точек несколько, выведите любое из них.
     * <p>
     * Input1:
     * 3
     * 1 3
     * 2 5
     * 3 6
     * Output1:
     * 1
     * 3
     * <p>
     * Input 2:
     * 4
     * 4 7
     * 1 3
     * 2 5
     * 5 6
     * Output 2:
     * 2
     * 3 6
     */
    public static void main(String[] args) {
        coverSegmentsWithPoints();
    }

    public static void coverSegmentsWithPoints() {
        TreeSet<Integer> points = new TreeSet<>();
        List<Segment> segments = inputSegments();
        Collections.sort(segments);
        for (Segment segment : segments) {
            if (points.isEmpty()) {
                points.add(segment.getRightEnd());
                continue;
            }
            if (points.last() < segment.getLeftEnd()) {
                points.add(segment.getRightEnd());
            }
        }
        int size = points.size();
        System.out.println(size);
        if (size < 2) {
            System.out.println(size == 0 ? 0 : points.first());
        } else {
            points.forEach(point -> System.out.print(point + " "));
        }
    }

    private static List<Segment> inputSegments() {
        List<Segment> segments = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            segments.add(new Segment(scanner.nextInt(), scanner.nextInt()));
        }
        return segments;
    }

    static class Segment implements Comparable<Segment> {
        private final int leftEnd;
        private final int rightEnd;

        public Segment(int leftEnd, int rightEnd) {
            this.leftEnd = leftEnd;
            this.rightEnd = rightEnd;
        }

        public int getLeftEnd() {
            return leftEnd;
        }

        public int getRightEnd() {
            return rightEnd;
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(this.rightEnd, o.rightEnd);
        }
    }

    //public static void main(String[] args) {
    //        Scanner scanner = new Scanner(System.in);
    //        int segmentsCount = scanner.nextInt();
    //        int[][] segments = new int[segmentsCount][2];
    //        for (int i = 0; i < segmentsCount; i++) {
    //            segments[i][0] = scanner.nextInt();
    //            segments[i][1] = scanner.nextInt();
    //        }
    //        Arrays.sort(segments, Comparator.comparingInt(a -> a[1]));
    //
    //        int points = 0;
    //        int lastPoint = -1;
    //        StringBuilder pointsResult = new StringBuilder();
    //        for (int i = 0; i < segmentsCount; i++) {
    //            if (lastPoint < segments[i][0]) {
    //                lastPoint = segments[i][1];
    //                points++;
    //                pointsResult.append(lastPoint).append(" ");
    //            }
    //        }
    //        System.out.println(points);
    //        System.out.println(pointsResult);
    //    }
}