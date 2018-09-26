package chap01;

import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Method Reference (코드 넘겨주기)
 */
public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                                                new Apple(120, "red"),
                                                new Apple(155, "green")
                                                );

        /******************** Method Reference ********************/

        List<Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
        //System.out.println(greenApples);

        List<Apple> headyApples = filterApples(inventory, FilteringApples::isHeavyApple);
        //System.out.println(headyApples);

        /**********************************************************/


        /*************** Method Reference -> Lambda ***************/

        List<Apple> greenApples2 = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        //System.out.println(greenApples2);

        List<Apple> headyApples2 = filterApples(inventory, (Apple a) -> a.getWeight() > 150);
        //System.out.println(headyApples2);

        /**********************************************************/


        /************************* Stream *************************/

        // 순차 처리
        List<Apple> streamApple =
                inventory.stream()
                         .filter((Apple a) -> a.getWeight() > 110)
                         .collect(toList());

        // 병렬 처리
        List<Apple> parallelApple =
                inventory.parallelStream()
                         .filter((Apple a) -> a.getWeight() > 110)
                         .collect(toList());

        System.out.println(streamApple);
        System.out.println(parallelApple);

        /**********************************************************/
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple (int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }

    public enum Currency {
        EUR, USD, JPY, GBP, CHF
    }
}
