package chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * STEP 1. 동작 파라미터화(코드 전달)를 통한 리스트 정렬 (자바 8 이전 버전)
 *
 * @author juhyun10
 * @since 2019.03.17
 */
public class G_Sorting_01_BehaviorParameterization_03_07 {
	
	public static void main(String...args) {
		List<Apple1> inventory = new ArrayList<>();
		inventory.addAll(Arrays.asList(new Apple1(80, "green"),
										new Apple1(155, "green"),
										new Apple1(120, "red")));
		
		// sort의 동작이 파라미터화됨.
		// sort에 전달된 정렬 전략에 따라 sort의 동작 달라짐
		inventory.sort(new AppleComparator());
		
		// [Apple1{color='green', weight=80}, Apple1{color='red', weight=120}, Apple1{color='green', weight=155}]
		System.out.println(inventory);
	}
	
	// int compare(T o1, T o2);
	public static class AppleComparator implements Comparator<Apple1> {
		public int compare(Apple1 a1, Apple1 a2) {
			return a1.getWeight().compareTo(a2.getWeight());
		}
	}
	
	public static class Apple1 {
		private Integer weight = 0;
		private String color = "";
		
		public Apple1(Integer weight, String color){
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


