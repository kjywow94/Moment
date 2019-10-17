# Swagger UI 설치

> Swagger UI 공식문서 : https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api

1. pom.xml에 다음 의존성 추가

   ```xml
   <!-- Swagger2 설치 -->
   		<dependency>
   			<groupId>io.springfox</groupId>
   			<artifactId>springfox-swagger2</artifactId>
   			<version>2.9.2</version>
   		</dependency>
   		<!-- Swagger UI 설치 -->
   		<dependency>
   			<groupId>io.springfox</groupId>
   			<artifactId>springfox-swagger-ui</artifactId>
   			<version>2.9.2</version>
   		</dependency>
   ```

2. {기본패키지}.config에 SwaggerConfig.java 생성

3. 다음의 소스코드를 참고하여 작성

   ```java
   package com.travelmaker.config;
   
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
   
   import springfox.documentation.builders.PathSelectors;
   import springfox.documentation.builders.RequestHandlerSelectors;
   import springfox.documentation.spi.DocumentationType;
   import springfox.documentation.spring.web.plugins.Docket;
   import springfox.documentation.swagger2.annotations.EnableSwagger2;
   
   @Configuration
   @EnableSwagger2
   public class SwaggerConfig {                                    
       @Bean
       public Docket api() { 
           return new Docket(DocumentationType.SWAGGER_2)  
             .select()                                  
             .apis(RequestHandlerSelectors.any())              
             .paths(PathSelectors.any())                          
             .build();                                           
       }
       
       public void addResourceHandlers(ResourceHandlerRegistry registry) {
           registry.addResourceHandler("swagger-ui.html")
             .addResourceLocations("classpath:/META-INF/resources/");
        
           registry.addResourceHandler("/webjars/**")
             .addResourceLocations("classpath:/META-INF/resources/webjars/");
       }
   }
   ```

   4. {스프링 주소}/swagger-ui.html 로 접속하여 사용

