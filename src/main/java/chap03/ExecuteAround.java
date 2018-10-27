package chap03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Lambda 활용 : 실행 어라운드 패턴 execute around pattern
 */
public class ExecuteAround {

    public static void main(String[] args) throws IOException {

        System.out.println("********* general file read *********");
        System.out.println(System.getProperty("user.dir"));

        String str1 = processFile1();
        System.out.println(str1);

        System.out.println("********* Lambda uses *********");

        // STEP 1. 동작 파라미터화 Behavior parameterization
        // processFile2의 동작을 파라미터화

        // processFile2 메서드가 BufferedReader를 이용해서 다른 동작을 수행할 수 있도록 processFile2 메서드로 동작을 전달


        // STEP 2. 함수형 인터페이스를 이용해서 동작 전달 Behavior relay using functional interface
        // BufferedReader->String과 IOException을 throw할 수 있는 시그니처와 일치하는 함수형 인터페이스(BufferedReaderProcessor) 생성

        // 정의한 이 인터페이스를 processFile2 메서드의 인수로 전달 가능


        // STEP 3. 동작 실행
        // BufferedReaderProcessor에 정의된 process 메서드의 시그니처(BufferedReader->String)와 일치하는 람다를 processFile2에 전달 가능

        // 람다 표현식으로 함수형 인터페이스(BufferedReaderProcessor)의 추상 메서드(process) 구현을 직접 전달 가능!!
        // 전달된 코드는 함수형 인터페이스의 인스턴스로 전달된 코드와 같은 방식으로 처리


        // STEP 4. 람다 전달
        // 람다를 이용해서 다양한 동작을 processFile2에 전달
        String oneLine = processFile2((BufferedReader br)->br.readLine());
        String twoLine = processFile2((BufferedReader br)->br.readLine()+br.readLine());

        System.out.println(oneLine);
        System.out.println(twoLine);
    }

    /**
     * general file read
     *
     * @return
     * @throws IOException
     */
    public static String processFile1() throws IOException {
        // try-with-resources 구문 / 자원을 명시적으로 닫을 필요가 없으므로 코드가 간결해짐
        try (BufferedReader br = new BufferedReader(new FileReader("./src/main/java/chap03/data.txt"))) {
            return br.readLine();
        }
    }

    /**
     * STEP 2. 함수형 인터페이스를 이용해서 동작 전달 Behavior relay using functional interface
     * BufferedReader->String과 IOException을 throw할 수 있는 시그니처와 일치하는 함수형 인터페이스(BufferedReaderProcessor) 생성
     *
     * 정의한 이 인터페이스를 processFile2 메서드의 인수로 전달 가능
     */
    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }

    public static String processFile2(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("./src/main/java/chap03/data.txt"))) {
            return p.process(br);
        }
    }
}
