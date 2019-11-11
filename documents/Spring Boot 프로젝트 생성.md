# Spring Boot 프로젝트 생성

참고 블로그 : https://offbyone.tistory.com/391

1. Package Explorer 우클릭 -> New -> Spring Starter Project 클릭

2. 아래 사진처럼 설정을 한다.

   ![1571035633121](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\1571035633121.png)

   > 스프링부트 프로젝트 생성 창
   >
   
   - Service URL : SpringBoot 데모 프로젝트를 다운받아올 주소. 변경하면 프로젝트 생성이 안될 수 있으므로 손대지 말자.
   - Name : 프로젝트 이름
   - Group : 도메인 명 입력(ex com.ssafy)
   - Artifact: Maven 아티팩트 명
   - Package : 프로젝트 디폴트 패키지
   - Version : 프로젝트 버전
   
   
   
   아래 Next버튼을 누르면 아래와 같은 화면이 나타난다.

![1571035651283](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\1571035651283.png)

> dependency 설정 페이지

Finish 버튼을 눌러 프로젝트 생성을 완료한다.



# enum

> 참고 블로그 : https://opentutorials.org/module/1226/8025

enum은 클래스처럼 보이게 하는 상수이다.

자바 1.5 버전 이전에는 클래스에 final static 키워드나 interface를 선언하여 상수로 정의하는 등의 불편한 방식을 사용했어야 했다. 이러한 부분을 개선하여 코드의 중복을 방지하기 위해 enum이 도입되었다.

```java

/**
 * 
 * <p> 성별 정보를 나타내는 enum
 * @author 김선일
 *
 */

public enum Gender {
	/**
	 * 남성 (Man)
	 */
	M,
	/**
	 * 여성 (Woman)
	 */
	W,
	/**
	 * 성별 정보없음
	 */
	X
}

```

> enum 생성

```java
		Enum<Authority> S1 = Authority.M;
		Enum<Authority> S2 = Authority.valueOf("M");
		Authority S3 = Authority.M;
```

> enum의 선언. S1과 S2, S3는 완전히 동일한 값이다.

```java
	S1.ordinal() // 열거된 순서를 정수값으로 반환. 배열과 동일하게 인덱스 적용
	S1.valueOf("String") //String에 해당하는 원소를 반환 (주어진 String과 일치하는 원소가 없는 경우 IllegalArgumentException 예외 발생)
    S1.values() // 모든 원소를 배열에 담아 반환.
```

> enum의 주요 메소드

toString 메소드를 사용하면 원소의 이름 그대로 반환받을 수 있다.



# 로깅

application.properties에 다음을 추가하면 사용할 수 있다.

> 출처: https://engkimbs.tistory.com/767 [새로비]
>
> blockchain-sub3 by BORACOIN - AUCTION B

```properties
# ===============================
# = LOGGING
# ===============================
# 패키지별 로깅 레벨 설정
logging.level.org.springframework.web=TRACE
logging.level.com.bcauction=DEBUG


# Logging pattern for the console
logging.pattern.console=%d{HH:mm:ss} %clr(%5p) [%c] %m%n

# Logging pattern for file
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
# 로그 파일 경로
logging.file = logs/travelMaker.log
```

