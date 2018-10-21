package chap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * behavior parameterization (동작 파라미터화)
 */
public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                                              new Apple(155, "green"),
                                              new Apple(120, "red"));

        /******************** first try : green apple filtering ********************
         * If you want to filter the red color,
         * you have to add a method "filterRedApples".
         ***************************************************************************/

        List<Apple> greenApples = filterGreenApples(inventory);
        //System.out.println("greenApples : " + greenApples);

        /***************************************************************************/


        /******************** second try : color parameterization ********************
         * If you wnat to filter the weight,
         * you have to add a method "filterApplesByWeight" with weight criteria.
         ***************************************************************************/

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples2 = filterApplesByColor(inventory, "green");
        System.out.println(greenApples2);

        // [Apple{color='red', weight=120}]
        List<Apple> redApples2 = filterApplesByColor(inventory, "red");
        System.out.println(redApples2);

        /***************************************************************************/
    }

    /**
     * first try : green apple filtering
     *
     * @param inventory
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * second try : color parameterization
     *
     * @param inventory
     * @param color
     * @return
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * second try : color parameterization
     *
     * @param inventory
     * @param weight
     * @return
     */
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory){
            if(apple.getWeight() > weight){
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

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
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
}


