package chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 함수형 인터페이스 : Predicate
 *
 *  test라는 추상 메서드를 정의하며, test는 제네릭 형식 T의 객체를 인수로 받아 boolean으로 return
 *  따로 정의할 필요없이 바로 사용 가능
 *
 * 배열에 빈 값이 아닌 값만 찾아내는 예
 *
 * @author juhyun10
 * @since 2019.03.16
 */
public class Predicate_03_04 {
	public static void main(String[] args) {
		List<String> listOfStrings = Arrays.asList("a", "b", "", "d");
		
		Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
		List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
		
		System.out.println(nonEmpty);       // [a, b, d]
	}
	
	/*@FunctionalInterface
	public interface Predicated<T> {
		boolean test(T t);
	}*/
	
	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> results = new ArrayList<>();
		for (T s : list) {
			if (p.test(s)) {
				results.add(s);
			}
		}
		return results;
	}
}


