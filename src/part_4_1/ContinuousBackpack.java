package part_4_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class ContinuousBackpack {
    /**
     * Первая строка содержит количество предметов 1≤n≤10^3 и вместимость рюкзака 0≤W≤2⋅10^6.
     * Каждая из следующих n строк задаёт стоимость 0≤ci≤2⋅10^6 и объём 0<wi≤2⋅10^6 предмета (n, W, ci, wi — целые числа).
     * <p>
     * Выведите максимальную стоимость частей предметов (от каждого предмета можно отделить любую часть,
     * стоимость и объём при этом пропорционально уменьшатся), помещающихся в данный рюкзак,
     * с точностью не менее трёх знаков после запятой.
     * <p>
     * Input:
     * 3 50
     * 60 20
     * 100 50
     * 120 30
     * <p>
     * Output:
     * 180.000
     */
    private static double weight;
    private static double resultPrice;

    public static void main(String[] args) {
        continuousBackpack();
    }

    private static void continuousBackpack() {
        List<Item> items = inputItems();
        Collections.sort(items);
        for (Item item : items) {
            if (item.getWeight() <= weight) {
                resultPrice += item.getPrice();
                weight -= item.getWeight();
            } else {
                double part = item.getWeight() / weight;
                resultPrice += item.getPrice() / part;
                break;
            }
        }
        System.out.printf("%.3f", resultPrice);
    }

    private static List<Item> inputItems() {
        List<Item> items = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        weight = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            items.add(new Item(scanner.nextInt(), scanner.nextInt()));
        }
        return items;
    }

    static class Item implements Comparable<Item> {
        private final int price;
        private final int weight;

        public Item(int price, int weight) {
            this.price = price;
            this.weight = weight;
        }

        public int getPrice() {
            return price;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Item o) {
            return Integer.compare(this.weight * o.getPrice(), this.price * o.getWeight());
        }
    }
}