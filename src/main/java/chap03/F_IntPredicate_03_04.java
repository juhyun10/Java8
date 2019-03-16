package chap03;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * 함수형 인터페이스 : 기본형 특화 (IntPredicate)
 *
 *  자바8에서는 기본형을 입출력으로 사용하는 상황에서 오토박싱 동작을 피할 수 있도록 특별한 버전의 함수형 인터페이스 제공
 *  IntPredicate, DoublePredicate, IntConsumer, LongBinaryOperator, IntFunction, ToIntFunction<T>, IntToDoubleFunction 등등...
 *
 *  변환과정은 비용 소모
 *  boxing한 값은 기본형을 감싸는 래퍼이며, 힙에 저장됨.
 *  따라서 boxing한 값은 메모리를 더 소비하며 기본형을 가져올 때도 메모리를 탐색하는 과정이 필요
 *
 *  boxing : 기본형(int, double, byte, char..)등을 참조형(Byte, Integer, Object, List..)등으로 변환하는 기능
 *  unboxing : 참조형을 기본형으로 변환하는 기능
 *  autoboxing : boxing과 unboxing이 자동으로 이루어지는 기능
 */
public class F_IntPredicate_03_04 {
	public static void main(String[] args) {
		IntPredicate evenNumbers = (int i) -> i % 2 == 0;
		System.out.println(evenNumbers.test(1000));     // boxing 없음
		
		Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;
		System.out.println(oddNumbers.test(1000));      // boxing
	}
	
	/*
	@FunctionalInterface
	public interface IntPredicate {
		boolean test(int t);
	}
	
	@FunctionalInterface
	public interface Predicate<T> {
		boolean test(T t);
	}*/
	
}
