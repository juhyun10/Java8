package chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * STEP 4. 메서드 레퍼런스를 통한 리스트 정렬
 *
 * @author juhyun10
 * @since 2019.03.17
 */
public class G_Sorting_04_MethodReference_03_07 {
	public static void main(String[] args) {
		List<Apple4> inventory = new ArrayList<>();
		inventory.addAll(Arrays.asList(new Apple4(80, "green"),
				new Apple4(155, "green"),
				new Apple4(120, "red")));
		
		inventory.sort(Comparator.comparing(Apple4::getWeight));
		System.out.println(inventory);
		
		// 역순으로 정렬
		inventory.sort(Comparator.comparing(Apple4::getWeight).reversed());
		System.out.println(inventory);
	}
	
	public static class Apple4 {
		private Integer weight = 0;
		private String color = "";
		
		public Apple4(Integer weight, String color){
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
