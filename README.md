#### 20210527(수)
- OOP:
- 객체 = 오브젝트 = 인스턴스 = 실행가능한 클래스
- 객체(Object)와 클래스? 클래스형 자료를 실행 가능하게 만든것을 오브젝트(객체)라고 함.
- 추상화(abstract): 오프라인 업무 -> 대표업무만 뽑아낸 클래스 = 추상클래스
- 변하지 않는 변수=상수=변수명을 대문자(원주율)PI=3.141592....
- 보통 상수변수는 클래스의 제일 상단 위치합니다.
- 기본형(정수자료형-소문자시작): byte<short<int<long, 10L(롱타입변수)
- 기본형(실수자료형-소수점,소문자시): float<double , 12.34f(실수형숫자)
- 기본형(문자형-'a'작은따옴표로 값을 입력해야 에러 나지않음.): char, 1개 문자만 입력가능.
- 문자형에서 유니코드 \u숫자 입니다.
- 기본형(불린형-참,거짓): boolean, (0|1)
- 참조형(대문자로 시작): 참조형 변수의 대표적인 변수 클래스입니다.
- 클래스변수(저장) -> 실행가능하게 됐을때, 인스턴스변수(메모리로드) 라고 합니다.
- 인스턴스라는 말보다는 오브젝트라는 말을 더 많이 사용합니다.
- String(문자열 클래스변수): 대문자로 시작.
- 상수변수를 명시적 만들때: final int MAX = 100;
- for-each 반복문전까지 실습.

#### 20210526(화)
- 스프링MVC프로젝트: ModelViewController의 약자 MVC구조(웹 프로그램 구조)
- src/test/java 폴더 만들었습니다. : 테스트작업은 이폴더에서 하라는 약속.
- src/main/java 폴더가  진행할 프로그램작업을 하는 폴더.
- javac HelloWorld.java -encoding UTF-8 (한글 내용도 컴파일됨)
- 위 java 컴파일러로 실행한 결과 = HelloWorld.class 실행파일 생성됨.
- 주의점) 클래스파일  실행은 패키지의 루트(최상위)에서 실행해야함.
- kr.or.test 패키지 root폴더 src/test/java 폴더에서 실행해야함.
- java kr.or.test.HelloWorld 클래스를 실행하게됨.
- 이클립스 나오기 25년 전, javac 로 컴파일해서 class 프로그램을 만들었다.
- 지금은 터미널에서 javac 사용하지않고 이클립스에서 바로 실행합니다.
- javac? 자바앱 컴파일러 -> 클래스 실행파일을 만듬.(자바환경JVM실행)
- 메이븐? maven : 웹프로그램 컴파일러 -> 웹프로그램(앱) 실행파일을 만듬.war(톰캣에서 실행)
- 메이븐이 컴파일할때, 자바소스만 컴파일하는게 아니고, 외부라이브러리로 가져와서 바인딩(묶음)하게 됨. = 패키징  = .war(와르)파일로 패키징.
- 메이븐이 관리하는 외부 라이브러리파일(lib) 목록을 저장하는 파일 pom.xml 입니다.
- pom.xml에서 자바버전을 1.6->1.8 변경 후 메이븐을 업데이트 합니다.
- 외부 라이브러리 파일을 참조하는 방식을 영어로 =  dependency
- jar파일? JavaArchive = jar 자바앱을 패키징한 파일.

#### 20210525(월) 작업
- 자바 기초 실습
- 자바 . java클래스파일을 컴파일해서 생성된.class파일 실행하는구조.
- 파이썬/자바스크립트는 인터프리터 방식을 실행
- 자바스크립트는 함수기반의 프로그래밍. = Function(함수)
- 자바는 객체지향 프로그래밍. (Object Oriented Program)
- 오브젝트 (객체) = 실행가능한 Class(클래스)
- 아스키, 유니코드(UnicodeTypeFormet-8)
- 아스키코드 IOT에서 데이터전송시 필수로 확인해야 합니다. 0을전송 ->48값을 받습니다.
- IOT프로그램시 하드웨어 값을 주고 받을때 if(var1 == 48) {구현내용}
- 공유기가 하위 가질수 있는 사설IP는 공유기자기IP + 255개(여유분) = 256개 인터넷이 가능.
- 영어인코딩은 아스키코드로 전부 표현가능.
- 단, 한글 인코딩, 중국어/일본어 인코딩 등등은 아스키코드로 전부 표현하지 못해서 유니코드 등장.
- Hex(16진수), Dec(10진수), Char(문자) = 127개 아스키문자의 크기 (컴문자=사람문자 1:1 매칭)
- Oct(8진수), Bit(2진)
- 아스키코드-7비트코드(127글자) -> ANSI코드-8비트(256글자) -> UniCode(65536글자)-UTF8
- UTF8mb4(이모지까지 포함: 이모티콘 + 이미지)
- 자바언어를 한다는 것은 컴파일 후 실행이 된다는 의미.->실습예정
- 자바스크립트(파이썬)는 그냥 실행해서 프로그램이 만들어집니다.->실습예정

