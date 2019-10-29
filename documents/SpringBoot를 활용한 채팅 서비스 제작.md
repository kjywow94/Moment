# SpringBoot를 활용한 채팅 서비스 제작

1. 다음과 같은 의존성을 추가한다.

   ```XML
   <!-- Web Socket 설치 -->
   		<dependency>
   			<groupId>org.springframework.boot</groupId>
   			<artifactId>spring-boot-starter-websocket</artifactId>
   		</dependency>
   ```

2. 기본 패키지.config 경로에 WebSocketConfig.java를 생성한다.

   ```java
   package com.travelmaker.config;
   
   import org.springframework.context.annotation.Configuration;
   import org.springframework.messaging.simp.config.MessageBrokerRegistry;
   import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
   import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
   import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
   
   @Configuration
   @EnableWebSocketMessageBroker
   public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
   	@Override
   	public void configureMessageBroker(MessageBrokerRegistry registry) {
           //백엔드에서 보내는 요청 앞에 붙음
   		registry.enableSimpleBroker("/topic");
           
           //클라이언트에서 보내는 요청앞에 붙음
   		registry.setApplicationDestinationPrefixes("/app");
           
   	}
   
   	@Override
   	public void registerStompEndpoints(StompEndpointRegistry registry) {
           //setAllowedOrigins("*") : CORS 에러 방지
           //addEndpoint("/websocket") : /websocket이라는 주소로 소켓통신을 받겠다.
   		registry.addEndpoint("/websocket").setAllowedOrigins("*").withSockJS();
   	}
   }
   ```

3. Controller를 제작한다.

   ```java
   package com.travelmaker.controller;
   
   import org.springframework.messaging.handler.annotation.MessageMapping;
   import org.springframework.messaging.handler.annotation.SendTo;
   import org.springframework.stereotype.Controller;
   
   import com.travelmaker.dto.Chat;
   @Controller
   public class MessagingController {
   	@MessageMapping("/greetings")
       //해당 주소를 통해 통신
       
   	@SendTo("/topic/greetings")
       //해당 url을 구독하고 있는 클라이언트에게 메세지를 보낸다고 알려주는 설명용 어노테이션. 주석과 같은 역할을 하며 생략해도 무관
       
   	public String greeting(Chat message) throws Exception {
   		String nickname = message.getContext();
   		return nickname + "님이 들어왔습니다.";
   	}
   	
   	@MessageMapping("/chat")
   	@SendTo("/topic/chat")
   	public Chat outChat(Chat chat) throws Exception {
   		return chat;
   	}
   }
   
   ```

4. 클라이언트

   ```javascript
   var socket = new SockJS('http://localhost:9090/websocket');
       this.stompClient = Stomp.over(socket);
       // SockJS와 stomp client를 통해 연결을 시도.
       let scope = this;
       await this.stompClient.connect({}, function (frame) {
         scope.stompClient.subscribe('/topic/greetings', function (chat) {
           let msg = chat.body
           scope.chat.push(msg);
           console.log("HELLO MSG" + msg);
       });
       scope.stompClient.subscribe('/topic/chat', function (chat) {
         let msg = JSON.parse(chat.body);
         console.log(msg['context']);
         scope.chat.push(msg['context']);
       });
         let visit = {'UID' : 11, 'timestamp':new Date(), 'context': scope.$store.state.user.nickname, 'room_number':1};
         scope.stompClient.send("/app/greetings", {}, JSON.stringify(visit));
   ```

   

5. s

기본적으로 서버와 클라이언트의 통신은 JSON으로 이루어짐. 따라서 객체를 주고받을때 Parsing, Stringify를 통해 원하는 형태로 가공해야 한다.

PathVariable을 통해 멀티 채팅방 역시 구현이 가능하다. 카카오톡의 단체채팅방이 여러개 존재하는 것과 유사함.

