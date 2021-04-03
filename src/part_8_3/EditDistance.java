package part_8_3;


import java.util.Scanner;

public class EditDistance {
    /**
     * Вычислите расстояние редактирования двух данных непустых строк длины не более 10^2, содержащих
     * строчные буквы латинского алфавита.
     * <p>
     * Input 1:
     * ab
     * ab
     * <p>
     * Output 1:
     * 0
     * <p>
     * Input 2:
     * short
     * ports
     * <p>
     * Output 2:
     * 3
     */
    private String first;
    private String second;

    public static void main(String[] args) {
        new EditDistance().run();
    }

    public void run() {
        input();
        countEditDistance();
    }

    private void countEditDistance() {
        char[] firstChars;
        char[] secondChars;

        if (first.length() <= second.length()) {
            firstChars = first.toCharArray();
            secondChars = second.toCharArray();
        } else {
            firstChars = second.toCharArray();
            secondChars = first.toCharArray();
        }

        int rows = firstChars.length + 1;
        int[] previousRow = new int[rows];

        for (int i = 0; i < rows; i++) {
            previousRow[i] = i;
        }

        for (int i = 1; i < secondChars.length + 1; i++) {
            int[] currentRow = new int[rows];
            currentRow[0] = i;
            for (int j = 1; j < rows; j++) {
                int left = currentRow[j - 1] + 1;
                int up = previousRow[j] + 1;
                int diff = firstChars[j - 1] == secondChars[i - 1] ? 0 : 1;
                int diag = previousRow[j - 1] + diff;
                currentRow[j] = Math.min(Math.min(left, up), diag);
            }
            previousRow = currentRow;
        }

        System.out.println(previousRow[rows - 1]);
    }

    private void input() {
        Scanner scanner = new Scanner(System.in);
        first = scanner.nextLine();
        second = scanner.nextLine();
        scanner.close();
    }
}