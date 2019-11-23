package OOAD;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
	
	@MessageMapping("/chatroom")
	public String broadcastToClients(String message, Principal principal){
		return message.toUpperCase() + " by user:" + principal.getName();
	}
}
