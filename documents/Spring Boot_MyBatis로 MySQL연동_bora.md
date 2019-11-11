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

  - userMapper.xml 파일 생성
  
  ~~~ 
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
  <mapper namespace="com.travelmaker.dao.UserMapper">
  	<!-- 회원 전체 조회 -->
  	<select id="selectAllUser" resultType="user">
  		SELECT * FROM user;
  	</select>
  	
  	<!-- 정상 회원 전체 조회 -->
  	<select id="selectNotRemovedAllUser" resultType="user">
  		SELECT * FROM user WHERE is_remove = 'N';
  	</select>
  	
  	<!-- 로그인&회원조회 -->
  	<select id="selectOneUser" parameterType="user" resultType="user">
  		SELECT * FROM user WHERE email=#{email} and password=#{password} and is_remove = #{isRemove};
  	</select>
  		
  	<!-- 회원가입 -->
  	<insert id="insertUser" parameterType="user">
  		INSERT INTO user(birthday, gender, nickname, user_name, phone
                     , email, password, sns_1, sns_2, taste_1, taste_2
                     , taste_3, taste_4, taste_5, taste_6, taste_7, about) 
  			   VALUES(#{birthday}, #{gender}, #{nickname}, #{userName}, #{phone}
  	                   , #{email}, #{password}, #{sns1}, #{sns2}, #{taste1}, #{taste2}
  	                   , #{taste3}, #{taste4}, #{taste5}, #{taste6}, #{taste7}, #{about});
  	</insert>
  	
  	<!-- 회원수정/회원삭제 -->
  	<update id="updateUser" parameterType="user">
  		UPDATE user SET birthday = #{birthday} , phone = #{phone} , email = #{email} , password = #{password}
  			     , sns_1 = #{sns1}  , sns_2 = #{sns2} , taste_1 = #{taste1}  , taste_2 = #{taste2} , taste_3  = #{taste3}  
  			     , taste_4  = #{taste4}  , taste_5 = #{taste5}  , taste_6 = #{taste6}  , taste_7 = #{taste7}  , about = #{about}
  			     , is_remove = #{isRemove}
  		 WHERE id = #{id};
  	</update>
  	
  	<!-- 회원탈퇴(보류) -->	
  	<delete id="deleteUser" parameterType="int">
  		DELETE FROM USER WHERE id = #{id};
  	</delete>
  </mapper>
  
  ~~~



- [[DAO\] DAO, DTO, Entity Class의 차이]: https://gmlwjd9405.github.io/2018/12/25/difference-dao-dto-entity.html	"[DAO] DAO, DTO, Entity Class의 차이"

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

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelmaker.dto.User;
import com.travelmaker.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;

	//회원 전체 조회
	@RequestMapping(value = "/userAllAdmin", method = RequestMethod.GET)
	@ApiOperation(value = "회원전체조회")
	public List<User> selectAllUser() throws Exception {
		return userService.selectAllUser();
	}
	
	//정상 회원 전체 조회
	@RequestMapping(value = "/userAll", method = RequestMethod.GET)
	@ApiOperation(value = "정상 회원 전체 조회")
	public List<User> selectNotRemovedAllUser() throws Exception {
		return userService.selectNotRemovedAllUser();
	}
	
	//로그인&회원조회
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value = "로그인&회원조회")
	public User userSelect(@RequestBody User user) throws Exception {
		return userService.selectOneUser(user);
	}
	
	//회원가입
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ApiOperation(value = "회원가입")
	public int userInsert(@RequestBody User user) throws Exception {
		return userService.insertUser(user);
	}
	
	//회원수정/회원삭제
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	@ApiOperation(value = "회원수정/삭제")
	public int userUpdate(@RequestBody User user) throws Exception {
		return userService.updateUser(user);
	}
	
	//회원삭제
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	@ApiOperation(value = "회원삭제")
	public int userDelect(@RequestParam int id) throws Exception {
		return userService.deleteUser(id);
	}
}

~~~



- com.travelmaker.service에 UserService.java 추가

~~~ 
package com.travelmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelmaker.dao.UserMapper;
import com.travelmaker.dto.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	//회원 전체 조회
	public List<User> selectAllUser(){
		return userMapper.selectAllUser();
	}
	
	//정상 회원 전체 조회
	public List<User> selectNotRemovedAllUser(){
		return userMapper.selectNotRemovedAllUser();
	}
	
	//로그인&회원조회
	public User selectOneUser(User user) {
		return userMapper.selectOneUser(user);
	}
	
	//회원가입
	public int insertUser(User user) {
		return userMapper.insertUser(user);
	}
	
	//회원수정/회원삭제
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}
	
	//회원탈퇴(보류)
	public int deleteUser(int id) {
		return userMapper.deleteUser(id);
	}
}

~~~



- com.travelmaker.dao 에 인터페이스 UserMapper.java 추가
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
	//회원 전체 조회
	public List<User> selectAllUser();
	//정상 회원 전체 조회 
	public List<User> selectNotRemovedAllUser();
	//로그인/회원조회
	public User selectOneUser(User user);
	//회원가입
	public int insertUser(User user);
	//회원수정/회원삭제
	public int updateUser(User user);
	//회원탈퇴(보류)
	public int deleteUser(int id);
}

~~~



### MVC 모델 2

![1571122413286](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\1571122413286.png)

> - Model : 모델은 애플리케이션의 정보, 즉 데이터를 나타낸다.
>
> - View : 뷰는 사용자에게 보여주는 인터페이스, 즉 화면을 이야기한다. 자바 웹 애플리케이션에서는 JSP를 의미한다.
>
> - Controller : 컨트롤러는 비지니스 로직과 모델의 상호동작의 조정 역할을 한다. MVC2에서는 서블릿이 흐름을 제어하는 컨트롤러 역할을 수행한다. 