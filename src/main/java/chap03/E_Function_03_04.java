package chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 함수형 인터페이스 : Function (T apply())
 *
 *  제네릭 형식 T를 인수로 받아서 제네릭 형식 R 객체를 반환하는 apply 라는 추상 메서드 정의
 *  입력을 출력으로 매핑하는 람다 정의 시 Function 인터페이스 활용 가능
 *  (예를 들면 사과의 무게 정보를 추출하거나 문자열을 길이와 매핑)
 *
 *  String 리스트를 인수로 받아 각 String의 길이를 포함하는 Integer 리스트로 변환하는 map이라는 메서드 정의하는 예
 */
public class E_Function_03_04 {
	public static void main(String[] args) {
		List<Integer> list = map(
								Arrays.asList("lambda", "in", "action"),
								(String s) -> s.length()
								);      // Function의 apply를 직접 구현하는 람다
		
		System.out.println(list);       // [6, 2, 6]
	}
	
	/*@FunctionalInterface
	public interface Function<T, R> {
		R apply(T t);
	}*/
	
	public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
		List<R> result = new ArrayList<>();
		for (T s : list) {
			result.add(f.apply(s));
		}
		return result;
	}
}
