package chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 함수형 인터페이스 : Predicate
 *
 * 배열에 빈 값이 아닌 값만 찾아내기
 *
 * @author juhyun10
 * @since 2019.03.16
 */
public class Predicate {
	public static void main(String[] args) {
		List<String> listOfStrings = Arrays.asList("a", "b", "", "d");
		
		Predicated<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
		List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
		
		System.out.println(nonEmpty);
	}
	
	@FunctionalInterface
	public interface Predicated<T> {
		boolean test(T t);
	}
	
	public static <T> List<T> filter(List<T> list, Predicated<T> p) {
		List<T> results = new ArrayList<>();
		for (T s : list) {
			if (p.test(s)) {
				results.add(s);
			}
		}
		return results;
	}
}


