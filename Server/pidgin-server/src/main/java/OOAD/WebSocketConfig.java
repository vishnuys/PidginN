package OOAD;

import java.util.Collection;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chatroom");
//		.withSockJS();
	}
	
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.setApplicationDestinationPrefixes("/app")
			  .enableSimpleBroker("/topic","/queue");
	}
	
	public class MyAuthenticationRequest implements Authentication {
    	 
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final String username;
  	 
  	public MyAuthenticationRequest(String username) {
  	  this.username = username;
  	}
  	@Override
  	public boolean isAuthenticated() {
  	  return false;//important that this is false
  	}
		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return username;
		}
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Object getCredentials() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Object getDetails() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Object getPrincipal() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}
    }
    
    @Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(new ChannelInterceptor() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				MessageHeaders headers = message.getHeaders();
				MultiValueMap<String, String> multiValueMap = headers.get(StompHeaderAccessor.NATIVE_HEADERS,MultiValueMap.class);
				StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
				if (StompCommand.CONNECT.equals(accessor.getCommand())) {
					String username = multiValueMap.getFirst("username");
					Authentication user = new MyAuthenticationRequest(username) ; // access authentication header(s)
					SecurityContextHolder.getContext().setAuthentication(user);
					accessor.setUser(user);
				}
				return message;
			}
		});
	}
	
}
