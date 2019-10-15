# SPRING BOOT mybatis로 mysql 연동

- src/main/resource/application.properties 소스 추가

~~~
# set datasource
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://192.168.100.68:3306/boracoin?allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC
spring.datasource.username=boracoin1
spring.datasource.password=ssafy

# set mybatis
mybatis.config-location=classpath:mybatis-config.xml

#port
server.port=9090
~~~



- src/main/resource 경로에 mybatis-config.xml 추가

  ~~~ 
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
  	<typeAliases>
  		<typeAlias type="com.travelmaker.domain.User" alias="user" />
  	</typeAliases>
  	
  	<mappers>
  		<mapper resource="mapper/userMapper.xml" />
  	</mappers>
  </configuration>
  
  ~~~

  

- src/main/resource 경로에 mapper 폴더 추가

  ~~~ 
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.travelmaker.dao.UserMapper">
  	<select id="selectAllUser" resultType="user">
  		SELECT * FROM user;
  	</select>
  	
  	<!-- <select id="searchOnceUser" parameterType="String" resultType="user">
  		SELECT * FROM user WHERE email=#{email};
  	</select>
  
  	<delete id="deleteUser" parameterType="String">
  		DELETE FROM user
  		WHERE email=#{email};
  	</delete>
  
  	<update id="updateUser" parameterType="user">
  		UPDATE user set
  		authority=#{authority} WHERE email=#{email};
  	</update>
  
  	<insert id="insertUser" parameterType="user">
  		INSERT INTO user values (#{email}, default, #{authority}, 0);
  	</insert>
  	
  	<select id="countUserAdmin" resultType="int">
  		SELECT count(*) FROM user WHERE authority ='admin'
  	</select> -->
  </mapper>
  ~~~



[[DAO\] DAO, DTO, Entity Class의 차이]: https://gmlwjd9405.github.io/2018/12/25/difference-dao-dto-entity.html	"[DAO] DAO, DTO, Entity Class의 차이"

- com.travelmaker.dto  에 dto(Data Transfer Object) 추가 --> DB getter, setter
  - 데이터를 오브젝트로 변환
  - 계층간 데이터 교환을 위한 객체(Java Beans)이다.
    DB에서 데이터를 얻어 Service나 Controller 등으터 보낼 때 사용하는 객체를 말한다.
  - 즉, DB의 데이터가 Presentation Logic Tier로 넘어오게 될 때는 DTO의 모습으로 바껴서 오고가는 것이다.
  - 로직을 갖고 있지 않는 순수한 데이터 객체이며, getter/setter 메서드만을 갖는다.
    하지만 DB에서 꺼낸 값을 임의로 변경할 필요가 없기 때문에 DTO클래스에는 setter가 없다

- 참고 VO(Value Object) vs DTO
  VO는 DTO와 동일한 개념이지만 read only 속성을 갖는다.
  VO는 특정한 비즈니스 값을 담는 객체이고, DTO는 Layer간의 통신 용도로 오고가는 객체를 말한다.


- com.travelmaker.controller경로에 UserController.java 생성
  - Controller 
    - 해당 요청 url에 따라 적절한 view와 mapping 처리
    - 대신 API 서비스로 사용하는 경우는 @ResponseBody를 사용하여 객체를 반환한다.

~~~ 
package com.travelmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travelmaker.dto.User;
import com.travelmaker.service.UserService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/userAll", method = RequestMethod.GET)
	public List<User> userAll() throws Exception {
		return userService.selectAllUser();
	}
	
//	@RequestMapping(value = "/userSelect", method = RequestMethod.GET)
//	public UserDto userSelect(@RequestParam String email) throws Exception {
//		return userService.searchOnceUser(email);
//	}
//
//	@RequestMapping(value = "/userDelete", method = RequestMethod.DELETE)
//	public HashMap<String, Object> userDelect(@RequestParam String email) throws Exception {
//		int res = userService.deleteUser(email);
//		HashMap<String, Object> result = new HashMap<>();
//		if (res > 0) {
//			result.put("state", "1");
//		} else {
//			result.put("state", "-1");
//		}
//		return result;
//	}
//
//	@RequestMapping(value = "/userUpdate", method = RequestMethod.PUT)
//	public HashMap<String, Object> userUpdate(@RequestBody UserDto user) throws Exception {
//		String user_authority = userService.searchOnceUser(user.getEmail()).getAuthority();
//		int res = -1;
//		HashMap<String, Object> result = new HashMap<>();
//		result.put("state", "-1");
//		if (user_authority.equals("admin")) {
//			int admin_num = userService.countUserAdmin();
//			if (admin_num == 1) {
//				return result;
//			}
//		}
//		user.setEmail(user.getEmail());
//		user.setAuthority(user.getAuthority());
//		res = userService.updateUser(user);
//		if (res > 0) {
//			result.put("state", "1");
//		}
//		return result;
//
//	}
//
//	@RequestMapping(value = "/userInsert", method = RequestMethod.POST)
//	public HashMap<String, Object> userInsert(@RequestBody UserDto user) throws Exception {
//		int res = userService.insertUser(user);
//		HashMap<String, Object> result = new HashMap<>();
//		if (res > 0) {
//			result.put("state", "1");
//		} else {
//			result.put("state", "-1");
//		}
//		return result;
//	}

}
~~~



- com.travelmaker.service에 UserService.java 추가

~~~ 
package com.travelmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmaker.dao.UserMapper;
import com.travelmaker.dto.User;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;

	public List<User> selectAllUser(){
		return userMapper.selectAllUser();
	}
	
//	public UserDto searchOnceUser(String eamil) {
//		return userMapper.searchOnceUser(eamil);
//	}
//	
//	public int deleteUser(String email) {
//		return userMapper.deleteUser(email);
//	}
//	
//	public int updateUser(UserDto user) {
//		return userMapper.updateUser(user);
//	}
//	
//	public int insertUser(UserDto user) {
//		return userMapper.insertUser(user);
//	}
//	public int countUserAdmin() {
//		return userMapper.countUserAdmin();
//	}

}
~~~



- com.travelmaker.dao 에 인터페이스 UserMapper.java 추가

~~~ 
package com.travelmaker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.User;

@Mapper
public interface UserMapper {
	public List<User> selectAllUser();
	
//	public UserDto selectOnceUser(String eamil);
//	public int deleteUser(String email);
//	public int updateUser(UserDto user);
//	public int insertUser(UserDto user);
//	public int countUserAdmin();

}
~~~



- com.travelmaker.dao에 UserMapper.java 추가
  - 계층간 데이터 교환을 위한 객체(Java Beans)이다.
  - Service와 DB를 연결하는 고리의 역할을 한다.
  - SQL를 사용(개발자가 직접 코딩)하여 DB에 접근한 후 적절한 CRUD API를 제공

~~~ 
package com.travelmaker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.User;

@Mapper
public interface UserMapper {
	public List<User> selectAllUser();
	
//	public UserDto selectOnceUser(String eamil);
//	public int deleteUser(String email);
//	public int updateUser(UserDto user);
//	public int insertUser(UserDto user);
//	public int countUserAdmin();

}
~~~





### MVC 모델 2

![1571122413286](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\1571122413286.png)

> - Model : 모델은 애플리케이션의 정보, 즉 데이터를 나타낸다.
>
> - View : 뷰는 사용자에게 보여주는 인터페이스, 즉 화면을 이야기한다. 자바 웹 애플리케이션에서는 JSP를 의미한다.
>
> - Controller : 컨트롤러는 비지니스 로직과 모델의 상호동작의 조정 역할을 한다. MVC2에서는 서블릿이 흐름을 제어하는 컨트롤러 역할을 수행한다. 