package OOAD;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chatroom");
//		.withSockJS();
//		.setHandshakeHandler(new UsernameHandshakeHandler());
	}
	
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.setApplicationDestinationPrefixes("/app")
			  .enableSimpleBroker("/topic","/queue");
	}
	
//	private class UsernameHandshakeHandler extends DefaultHandshakeHandler {
//		protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
//				Map<String, Object> attributes) {
//			String username = "user-" + (int) (Math.random()*5 + 5);
//			return new UsernamePasswordAuthenticationToken(username, null);
//		}
//	}
}
