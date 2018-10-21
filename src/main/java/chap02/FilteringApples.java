package chap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * behavior parameterization (동작 파라미터화)
 *  filtering
 *  predicate
 */
public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                                              new Apple(155, "green"),
                                              new Apple(120, "red"));
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        /******************** first try : green apple filtering ********************
         * If you want to filter the red color,
         * you have to add a method "filterRedApples".
         ***************************************************************************/

        System.out.println("********* first try : green apple filtering *********");

        List<Apple> greenApples = filterGreenApples(inventory);
        //System.out.println("greenApples : " + greenApples);

        /***************************************************************************/


        /******************** second try : color parameterization ********************
         * If you wnat to filter the weight,
         * you have to add a method "filterApplesByWeight" with weight criteria.
         ***************************************************************************/

        System.out.println("********* second try : color parameterization *********");

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples2 = filterApplesByColor(inventory, "green");
        System.out.println(greenApples2);

        // [Apple{color='red', weight=120}]
        List<Apple> redApples2 = filterApplesByColor(inventory, "red");
        System.out.println(redApples2);

        /***************************************************************************/


        /******************** third try : filter by all possible attributes ********************
         * 형편없는 코드
         * 결국 여러 중복된 필터 메서드를 만들거나 아니면 모든 것을 처리하는 거대한 하나의 필터 메서드 구현해야함
         *
         * Bad code.
         * After all, u need to create a number of duplicate filter method,
         * or implement one huge filter method that handles everything.
         ***************************************************************************/

        System.out.println("********* third try : filter by all possible attributes *********");

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples3 = filterApples3(inventory, "green", 0, true);
        System.out.println(greenApples3);

        // [Apple{color='green', weight=155}]
        List<Apple> weightApples3 = filterApples3(inventory, "", 150, false);
        System.out.println(weightApples3);

        /***************************************************************************/

        /******************** fourth try : predicate ********************
         * 새로운 검색 조건이 생기면 ApplePredicate interface를 적절하게 구현하는 클래스만 만들면 됨.
         *
         * If u have new create search conditions,
         * u can just create a class that implements it properly.
         ****************************************************************/

        System.out.println("********* fourth try : predicate *********");

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples4 = filterApples4(inventory, new AppleColorPredicate());
        System.out.println("greenApples4 : " + greenApples4);

        // [Apple{color='green', weight=155}]
        List<Apple> heaveApples4 = filterApples4(inventory, new AppleHeavyWeightPredicate());
        System.out.println("heaveApples4 : " + heaveApples4);

        // []
        List<Apple> redAndHeavyApples4 = filterApples4(inventory, new AppleRedHeavyPredicate());
        System.out.println("redAndHeavyApples4 : " + redAndHeavyApples4);

        /***************************************************************************/


        /******************** quiz 2-1. prettyPrintApple ********************
         * 동작을 추상화해서 여러 요구사항에 대응하는 코드구현
         * (여러 클래스 구현하는 부분도 나중에 개선함)
         *
         * 사과 리스트를 인수로 받아 다양한 방법으로 문자열을 생성할 수 있도록 파라미터화된 prettyPrintApple 메서드 구현
         *
         * Implemented a parameterized prettyPrintApple method that accepts an apple list as an argument
         * and can generate string in a variety of ways.
         ****************************************************************/

        System.out.println("********* quiz 2-1. prettyPrintApple *********");

        prettyPrintApple(inventory, new AppleFancyFormatter());

        prettyPrintApple(inventory, new AppleSimpleFormatter());

        /***************************************************************************/


        /******************** fifth try : anonymous class ********************
         * 이전까지는 filterApples로 새로운 동작을 전달하려면 ApplePredicate interface를 구현하는 클래스를
         * 정의한 후에 인스턴스화해야 했음 -> 상당히 번거로운 작업
         *
         * 이러한 복잡한 과정을 익명 클래스를 사용해 클래스 선언과 동시에 인스턴스화함
         ****************************************************************/

        System.out.println("********* fifth try : anonymous class *********");

        // [Apple{color='red', weight=120}]
        List<Apple> redApples5 = filterApples4(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple a) {
                return "red".equals(a.getColor());
            }
        });

        System.out.println(redApples5);


        /******************** sixth try : Lambda ********************/

        System.out.println("********* sixth try : Lambda *********");

        // Lambda
        List<Apple> greenApplesLambda4 = filterApples4(inventory, (Apple a) -> "green".equals(a.getColor()));
        System.out.println("greenApplesLambda4 : " + greenApplesLambda4);


        /******************** seven try : Abstraction in list form ********************
         * Apple과 관련한 동작 뿐 아니라 다양한 물건에서 필터링이 작동하도록 리스트 형식을 추상화
         ****************************************************************/

        System.out.println("********* seven try : Abstraction in list form *********");

        // redApples7 : [Apple{color='red', weight=120}]
        List<Apple> redApples7 = filterApples7(inventory, (Apple a) -> "red".equals(a.getColor()));
        System.out.println("redApples7 : " + redApples7);

        // oddNumbers7 : [1, 3, 5, 7, 9]
        List<Integer> oddNumbers7 = filterApples7(numbers, (Integer i) -> i%2==1);
        System.out.println("oddNumbers7 : " + oddNumbers7);


        /******************** Comparator sort ********************
         * java 8의 List에는 sort 메서드 포함 (물론 Collections.sort도 존재)
         *
         * 익명클래스, Comparator를 구현해서 sort 메서드의 동작을 다양화
         * 람다 표현식으로 더 간단히 표현
         ****************************************************************/

        System.out.println("********* Comparator sort *********");

        // 익명클래스, Comparator를 구현해서 sort 메서드의 동작을 다양화 (무게가 적은 순으로 정렬)
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        System.out.println(inventory);

        // Lambda로 표현 (무게가 큰 순으로 정렬)
        inventory.sort((Apple a1, Apple a2) -> a2.getWeight().compareTo(a1.getWeight()));

        System.out.println(inventory);
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

    /**
     * third try : filter by all possible attributes (BAD CODE)
     *
     * @param inventory
     * @param color
     * @param weight
     * @param flag
     * @return
     */
    public static List<Apple> filterApples3(List<Apple> inventory, String color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ((flag && apple.getColor().equals(color) ||
                    (!flag && apple.getWeight() > weight))) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * fourth try : predicate
     *
     * @param inventory
     * @param p
     * @return
     */
    public static List<Apple> filterApples4(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            // predicate 객체로 사과 검사 조건을 캡슐화
            // encapsulate apple check condition with predicate object
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * fourth try : predicate
     *
     * 선택 조건을 결정하는 interface
     * The interface that determines the selection criteria.
     *
     * 사과 선택 전략을 캡슐화
     */
    interface ApplePredicate {
        public boolean test(Apple a);
    }

    /**
     * fourth try : predicate
     *
     * 150 이상의 사과만 선택하는 predicate
     * A predicate that selects only apples weight over 150.
     */
    static class AppleHeavyWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple a) {
            return a.getWeight() > 150;
        }
    }

    /**
     * fourth try : predicate
     *
     * 녹색 사과만 선택하는 predicate
     * A predicate that selects only green apples.
     */
    static class AppleColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple a) {
            return "green".equals(a.getColor());
        }
    }

    /**
     * fourth try : predicate
     */
    static class AppleRedHeavyPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple a) {
            return "red".equals(a.getColor())
                    && a.getWeight() > 150;
        }
    }

    /**
     * quiz 2-1. prettyPrintApple
     *
     * @param inventory
     * @param formatter
     */
    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);

            System.out.println(output);
        }
    }

    /**
     * quiz 2-1. prettyPrintApple
     *
     * Apple을 인수로 받아 정해진 형식의 문자열을 반환할 수단
     */
    public interface AppleFormatter {
        public String accept(Apple a);
    }

    /**
     * quiz 2-1. prettyPrintApple
     */
    public static class AppleFancyFormatter implements AppleFormatter {
        @Override
        public String accept(Apple a) {
            String str = a.getWeight() > 150 ? "heave" : "light";
            StringBuffer buf = new StringBuffer();
            buf.append("A ").append(str).append(" ").append(a.getColor()).append(" apple");

            return buf.toString();
        }
    }

    /**
     * quiz 2-1. prettyPrintApple
     */
    public static class AppleSimpleFormatter implements AppleFormatter {
        @Override
        public String accept(Apple a) {
            return "An Apple of " + a.getWeight() + "g.";
        }
    }

    /**
     * seven try : Abstraction in list form
     *
     * @param <T>
     */
    public interface Predicate7<T> {
        boolean test(T t);
    }

    /**
     * seven try : Abstraction in list form
     *
     * @param list
     * @param p
     * @param <T>
     * @return
     */
    public static <T> List<T> filterApples7(List<T> list, Predicate7<T> p) {
        // 형식 파라미터 T 등장
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
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


