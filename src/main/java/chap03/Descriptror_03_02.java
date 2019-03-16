package chap03;

/**
 * 어디에, 어떻게 람다 사용?
 */
public class Descriptror_03_02 {
    public static void main(String[] args) {
        // 람다 사용
        Runnable r1 = () -> System.out.println("Hello1");

        // 익명 클래스 사용
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello2");
            }
        };

        process(r1);
        process(r2);

        // 직접 전달된 람다 표현식으로 출력
        process(() -> System.out.println("Hello3"));
    }

    public static void process(Runnable r) {
        r.run();
    }
}
