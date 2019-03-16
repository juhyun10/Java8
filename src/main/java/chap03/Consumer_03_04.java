package chap03;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 함수형 인터페이스 : Consumer
 *
 *  인자를 받아서 소모만 하고 리턴은 안함
 *  제네릭 형식 T 객체를 받아서 void return 하는 accept 라는 추상 메서드 정의
 *  따로 정의할 필요없이 바로 사용 가능
 *
 * forEach와 람다 이용해서 리스트의 모든 항목 출력하는 예
 *
 * @author juhyun10
 * @since 2019.03.17
 */
public class Consumer_03_04 {
	public static void main(String[] args) {
		// Consumer의 accept를 구현하는 람다를 직접 넣음
		forEach(Arrays.asList(1,2,3,4,5), (Integer i) -> System.out.println(i));
	}
	
	/*@FunctionalInterface
	public interface Consume<T> {
		void accept(T t);
	}*/
	
	public static <T> void forEach(List<T> list, Consumer<T> c) {
		for (T i : list) {
			c.accept(i);
		}
	}
}
