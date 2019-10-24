package OOAD;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketTextHandler extends TextWebSocketHandler {
	private WebSocketSession vishalSession;
	private WebSocketSession vishnuSession;
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) 
			throws InterruptedException, IOException, JSONException 
	{
		String username = session.getUri().getPath();
		if (username == "vishal")
		{
			if (vishalSession == null) {
				vishalSession = session;
				return;
			}
			
			String payload = message.getPayload();
			
			JSONObject jsonObject = new JSONObject(payload);
			vishnuSession.sendMessage(new TextMessage(jsonObject.get("user") + ""));
			
		}
		else if (username == "vishnu")
		{
			if (vishnuSession == null) {
				vishnuSession = session;
				return;
			}
			
			String payload = message.getPayload();
			
			JSONObject jsonObject = new JSONObject(payload);
			vishalSession.sendMessage(new TextMessage("Hi " + jsonObject.get("user") + " how may we help you?"));
		}
			
		
		String payload = message.getPayload();
		
		JSONObject jsonObject = new JSONObject(payload);
		session.sendMessage(new TextMessage(jsonObject.get("user") + ""));
	}
}
