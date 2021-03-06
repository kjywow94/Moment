package com.bcauction.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = { "*" })
@Controller
public class MessagingController {
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@MessageMapping("/greetings")
	@SendTo("/topic/greetings/{roomNumber}")
	public void greeting(Chat chat) throws Exception {
		messagingTemplate.convertAndSend("/topic/greetings/" + chat.getRoomNumber(), chat);
	}
	
	@MessageMapping("/goodbye")
	@SendTo("/topic/goodbye/{roomNumber}")
	public void goodbye(Chat chat) throws Exception {
		messagingTemplate.convertAndSend("/topic/goodbye/" + chat.getRoomNumber(), chat);
		
	}
	
	@MessageMapping("/chat")
	@SendTo("/topic/chat/{roomNumber}")
	public void outChat(Chat chat) throws Exception {
		messagingTemplate.convertAndSend("/topic/chat/" + chat.getRoomNumber(), chat);
	}
	
	@MessageMapping("/privateChat")
	@SendTo("/topic/privateChat/{uid}")
	public void privateChat(Chat chat) throws Exception {
		messagingTemplate.convertAndSend("/topic/privateChat/" + chat.getTarget(), chat);
	}
}
