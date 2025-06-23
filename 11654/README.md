# 11654 - 아스키 코드
## 문제
- 알파벳 소문자, 대문자, 숫자 0-9중 하나가 주어졌을 때, 주어진 글자의 아스키 코드값을 출력하는 프로그램을 작성하시오.

## 입력
- 알파벳 소문자, 대문자, 숫자 0-9 중 하나가 첫째 줄에 주어진다.

## 출력
- 입력으로 주어진 글자의 아스키 코드 값을 출력한다.

## 접근 방식
- 입력 받은 char 값에서 '0' 뺄셈 연산을 하고 48 덧셈 연산을 해서 아스키 코드값 반환

````
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AsciiPrinter printer = new AsciiPrinter();
        Scanner sc = new Scanner(System.in);
        char input = sc.nextLine().charAt(0);
        int result = printer.printAsciiValue(input);
        System.out.println(result);
    }
}

public class AsciiPrinter {
    public int printAsciiValue(char input){
        return input-'0'+48;
    }
}
````

## 피드백
| 항목         | 현재 코드                                                                                           | 개선 포인트                                                            |
| ---------- |-------------------------------------------------------------------------------------------------| ----------------------------------------------------------------- |
| **기능 정확성** | `'0'`을 빼고 다시 `+ 48`을 해서 **결국 원래 아스키 값 그대로** 돌려줍니다. 문제 정답은 나오지만 불필요한 연산이에요. ('0'의 아스키 코드 값이 48임) | `(int) input` 한 줄이면 충분합니다.                                        |
| **구조**     | `public class`가 두 개 → 실제로는 파일을 두 개로 나누거나, `AsciiPrinter`를 `static` 중첩 클래스로 바꿔야 컴파일 경고/오류가 없습니다. | 학습용·단일 기능이라면 **클래스를 나누지 않고** `main` 안에서 바로 캐스팅하는 편이 더 단순합니다.      |
| **입력 처리**  | `nextLine()` → 줄 전체를 읽고 `charAt(0)` 사용. 빈 줄 입력 시 `StringIndexOutOfBoundsException` 가능성.         | **`Scanner.next()`** 또는 **`BufferedReader`** + `read()`가 더 안전합니다. |
| **자원 관리**  | `Scanner`를 닫지 않음. (작은 예제에선 큰 문제는 아니지만 습관화 필요)                                                   | `sc.close();` 추가 혹은 **try-with-resources** 사용 권장.                 |
| **성능**     | 간단한 문제에선 무시 가능하지만, `Scanner` 보다 `BufferedReader` + `read()`가 빠릅니다.                              | 알고리즘 문제 다수 풀 때는 `BufferedReader` 템플릿을 익혀 두면 좋아요.                  |

### 다시 풀이
````
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = br.read();
        System.out.println(input);
    }
}
````
### Scanner vs BufferedReader 비교
| 항목    | `Scanner`                       | `BufferedReader`             |
| ----- | ------------------------------- | ---------------------------- |
| 속도    | 느림 🐢                           | 빠름 ⚡                         |
| 사용법   | 직관적 (`nextInt()`, `nextLine()`) | 다소 불편 (`readLine()` 후 직접 파싱) |
| 반환값   | 원하는 타입 (int, String 등)          | 문자열 (String) or 아스키 코드 (int) |
| 예외 처리 | 내부 처리 → `throws` 필요 없음          | **`IOException` 반드시 명시**     |
| 공백 처리 | 알아서 해줌 (`next()`)               | 직접 `split()` 등으로 처리해야 함      |
| 권장 상황 | 연습용, 간단한 문제                     | 입력량 많거나 성능 중요할 때             |

### Scanner.next() vs nextLine() 차이
| 메서드          | 설명         | 예시 입력: `hello world`  |
| ------------ | ---------- | --------------------- |
| `next()`     | 공백 전까지만 읽음 | `"hello"`만 반환         |
| `nextLine()` | 한 줄 전체 읽음  | `"hello world"` 전체 반환 |

nextInt() 같은 메서드 이후 nextLine()을 바로 쓰면 빈 문자열 문제가 발생할 수 있음
→ 남아있는 개행 문자 때문

### BufferedReader.read() vs readLine()
| 메서드          | 반환 타입    | 설명                     |
| ------------ | -------- | ---------------------- |
| `read()`     | `int`    | 문자 하나를 아스키 코드(int)로 읽음 |
| `readLine()` | `String` | 한 줄 전체를 문자열로 읽음        |

````
char ch = (char) br.read();     // 'A'
String str = br.readLine();     // "Hello World"
````

### 파싱이란?
문자열을 내가 원하는 자료형으로 바꾸는 작업
````
String line = "3 5 7";
String[] tokens = line.split(" ");
int a = Integer.parseInt(tokens[0]); // 3
````
BufferedReader는 자동으로 숫자로 바꿔주지 않기 때문에
split + parseInt를 통해 직접 파싱해야 함

### 요약 포인트
- (int) 문자로 아스키 코드값 출력 가능

- Scanner는 편하지만 느림, BufferedReader는 빠르지만 파싱은 직접 해야 함

- read()는 문자 하나의 아스키 코드(int), readLine()은 한 줄 전체 문자열

- split()과 Integer.parseInt()는 파싱의 대표적인 예