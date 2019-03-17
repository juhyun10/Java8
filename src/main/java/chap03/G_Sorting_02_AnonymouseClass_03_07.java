package chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * STEP 2. 익명 클래스를 통한 리스트 정렬 (자바 8 이전 버전)
 *
 *  한번만 사용할 Comparator를 STEP 1처럼 구현하는 것보다는 익명 클래스를 사용하는 것이 좋음
 *
 * @author juhyun10
 * @since 2019.03.17
 */
public class G_Sorting_02_AnonymouseClass_03_07 {
	
	public static void main(String[] args) {
		List<Apple2> inventory = new ArrayList<>();
		inventory.addAll(Arrays.asList(new Apple2(80, "green"),
				new Apple2(155, "green"),
				new Apple2(120, "red")));
		
		// 한번만 사용할 Comparator를 STEP 1처럼 구현하는 것보다는 익명 클래스를 사용하는 것이 좋음
		inventory.sort(new Comparator<Apple2>() {
			@Override
			public int compare(Apple2 o1, Apple2 o2) {
				return o2.getWeight().compareTo(o1.getWeight());
			}
		});
		
		// [Apple1{color='green', weight=155}, Apple1{color='red', weight=120}, Apple1{color='green', weight=80}]
		System.out.println(inventory);
	}
	
	public static class Apple2 {
		private Integer weight = 0;
		private String color = "";
		
		public Apple2(Integer weight, String color){
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
