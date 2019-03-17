package chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * STEP 3. 람다 표현식을 통한 리스트 정렬
 *
 * @author juhyun10
 * @since 2019.03.17
 */
public class G_Sorting_03_LambdaExpression_03_07 {
	public static void main(String[] args) {
		List<Apple3> inventory = new ArrayList<>();
		inventory.addAll(Arrays.asList(new Apple3(80, "green"),
				new Apple3(155, "green"),
				new Apple3(120, "red")));
		
		
		// List의 sort 디스크립터 : void sort(Comparator<? super E> c) 이므로 Comparator의 추상 메서드 sort의 디스크립터를 보자
		// Comparator의 sort 디스크립터 : int compare(T o1, T o2) // (T,T) -> int
		
		inventory.sort((Apple3 a1, Apple3 a2) -> a1.getWeight().compareTo(a2.getWeight()));
		System.out.println(inventory);
		
		
		// 자바 컬파일러는 람다 표현식이 사용된 컨텍스트를 활용해서 람다의 파라미터 형식을 추론하므로 아래와 같이 줄일 수 있음
		inventory.sort((a3, a4) -> a3.getWeight().compareTo(a4.getWeight()));
		System.out.println(inventory);
		
		
		// Comparator는 Comparable 키를 추출해서 Comparator 객체로 만드는 Function 함수를 인수로 받는 정적 메서드 comparing 포함
		// Comparator<Apple3> c = Comparator.comparing((Apple3 a5) -> a5.getWeight());
		inventory.sort(Comparator.comparing((a6) -> a6.getWeight()));
		System.out.println(inventory);
	}
	
	public static class Apple3 {
		private Integer weight = 0;
		private String color = "";
		
		public Apple3(Integer weight, String color){
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
			return "Apple1{" +
					"color='" + color + '\'' +
					", weight=" + weight +
					'}';
		}
	}
}
