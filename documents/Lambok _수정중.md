# Lambok

DTO를 생성하며 필수적으로 필요한 생성자, `Getter`,` Setter`, `toString`, `equals`, `hashCode`등의 메소드를 자동 생성해주는 Dependendy.

```java
package com.travelmaker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class ChatList {
	@Getter @Setter
	private int id;
	@Getter @Setter
	private int uid;
	@Getter @Setter
	private int target;
	@Getter @Setter
	private int notification;
}
```

> Lambok에서 제공하는 어노테이션을 사용한 DTO의 모습



## @Getter, @Setter

클래스의 필드위에 해당 어노테이션을 작성하면 `Getter`, `Setter` 메소드를 자동으로 생성해줌

## @ToString

클래스 위에 해당 어노테이션을 작성하면 `toString `메소드를 자동으로 생성해줌.

`exclude` 키워드를 사용하면 제외하고 싶은 필드를 입력할 수 있음.

```java
@ToString(exclude = "password")
//password라는 필드는 toString 메소드에서 제외됨
```



## 생성자 자동 생성

클래스위에 다음과 같은 어노테이션을 작성하면 해당하는 생성자를 자동으로 작성해준다.

### @NoArgsConstructor

기본 생성자를 생성해주는 어노테이션

### @AllArgsConstructor

모든 필드값을 파라미터로 사용하는 생성자를 생성해주는 어노테이션

### @RequiredArgsConstructor

final으로 선언되었거나 `@NonNull` 어노테이션이 부착된 필드값만 파라미터로 사용하는 생성자를 생성해주는 어노테이션



## @EqualsAndHashCode 

`equals`, `hashCode` 메소드를 생성해주는 어노테이션

`callSuper` 키워드를 통해 부모클래스의 필드까지 고려하여 값을 반환함.

```java
@EqualsAndHashCode (callSuper = true)
```



## @Data

위의 모든 어노테이션을 자동으로 설정해주는 어노테이션