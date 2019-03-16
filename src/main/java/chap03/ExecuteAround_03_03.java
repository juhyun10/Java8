package chap03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Lambda 활용 : 실행 어라운드 패턴 execute around pattern
 *
 * 람다와 동작 파라미터화로 유연하고 간결한 코드 구현
 *
 * @author juhyun10
 * @since 2019.03.16
 */
public class ExecuteAround_03_03 {

    public static void main(String[] args) throws IOException {

        System.out.println("********* 일반적인 파일 읽기 *********");
        System.out.println(System.getProperty("user.dir"));

        String str1 = fileReadOneLine();
        System.out.println(str1);

        /******************** STEP 1. 동작 파라미터화 Behavior parameterization ********************
         * fileReadOneLine 동작을 파라미터화
         * fileReadOneLine 메서드가 BufferedReader를 이용해서 다른 동작을 수행할 수 있도록 fileReadOneLine 메서드로 동작을 전달
         ***************************************************************************/


        /******************** STEP 2. 함수형 인터페이스를 이용해서 동작 전달 Behavior relay using functional interface ********************
         * 함수형 인터페이스 자리에 람다 사용. 따라서 아래처럼
         * BufferedReader->String과 IOException을 throw할 수 있는 시그니처와 일치하는 함수형 인터페이스(BufferedReaderProcessor) 생성
         * 정의한 이 인터페이스를 fileReadOneLine 메서드의 인수로 전달 가능
         ***************************************************************************/


        /******************** STEP 3. 동작 실행 ********************
         * BufferedReaderProcessor에 정의된 process 메서드의 시그니처(BufferedReader->String)와 일치하는 람다를 processFile에 전달 가능
         *
         * 람다 표현식으로 함수형 인터페이스(BufferedReaderProcessor)의 추상 메서드(process) 구현을 직접 전달 가능!!
         * 전달된 코드는 함수형 인터페이스의 인스턴스로 전달된 코드와 같은 방식으로 처리
         ***************************************************************************/


        /******************** STEP 4. 람다 전달 ********************
         * 람다를 이용해서 다양한 동작을 processFile 에 전달
         ***************************************************************************/

        // 함수형 인터페이스인 BufferedReaderProcessor의 process의 시그니처와 일치하는 람다를 전달
        
        // 한 행 처리
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        
        // 두 행 처리
        String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());
    
        System.out.println(oneLine);
        System.out.println(twoLines);

    }

    /**
     * 일반적인 파일 읽기
     *  한번에 한 줄만 읽을 수 있음
     */
    public static String fileReadOneLine() throws IOException {
        // try-with-resources 구문 / 자원을 명시적으로 닫을 필요가 없으므로 코드가 간결해짐
        try (BufferedReader br = new BufferedReader(new FileReader("./src/main/java/chap03/data.txt"))) {
            return br.readLine();
        }
    }

    /**
     * STEP 2. 함수형 인터페이스를 이용해서 동작 전달 Behavior relay using functional interface
     */
    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }

    /**
     * STEP 3. 동작 실행
     */
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        // try-with-resources 구문 / 자원을 명시적으로 닫을 필요가 없으므로 코드가 간결해짐
        try (BufferedReader br = new BufferedReader(new FileReader("./src/main/java/chap03/data.txt"))) {
            return p.process(br);
        }
    }
}
