package OOAD;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import OOAD.Entities.Message;
import OOAD.Entities.TranslatedMessage;
import OOAD.Entities.UserConnections;
import OOAD.Entities.UserMessageMapping;


@Controller
public class MessageController {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/chatroom")
	@SendTo("/topic/chatroom")
	public String broadcastToClients(String message, Principal principal) {
		return message.toUpperCase() + " by user:" + principal.getName();
	}
	
	@MessageMapping("/sendall")
	public void sendAllMessages(UserIdentityInfo userIdInfo, Principal principal) {		
		String result = "error";
		UserConnections userConn = new UserConnections(Integer.parseInt(userIdInfo.getUserID()), 0);
		try {
			result = FetchMessagesForUser(userConn);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		simpMessagingTemplate.convertAndSend("/user/" + userIdInfo.getUsername() + "/queue/allmessages", result);
	}
	
	@MessageMapping("/message")
	public void privateMessage(String messageJson, Principal principal) throws JsonParseException, JsonMappingException, IOException {
		
		System.out.println(messageJson);
		ObjectMapper mapper = new ObjectMapper();
		SenderMessage sendm = mapper.readValue(messageJson, SenderMessage.class);
		
		String translatedMessage;
		@SuppressWarnings("deprecation")
		Translate translate = TranslateOptions.newBuilder().setApiKey("AIzaSyAiRYBhdm3LPeKeq-ClxWERz_vAMPcX2Rw").build().getService();
		Translation translation = translate.translate(sendm.userMessage, TranslateOption.sourceLanguage(sendm.senderLang),
				TranslateOption.targetLanguage(sendm.recieverLang));
		translatedMessage = translation.getTranslatedText();
		
		Message msg = new Message();
		msg.userMessageMapping = new UserMessageMapping(Integer.parseInt(sendm.senderUserID), Integer.parseInt(sendm.recieverUserID));
		msg.translatedMessage = new TranslatedMessage(sendm.recieverLang, sendm.userMessage, translatedMessage);
		
		
		RecieverMessage recvm = new RecieverMessage(sendm, translatedMessage);
		simpMessagingTemplate.convertAndSend("/user/" + recvm.senderUserName + "/queue/private", recvm);
		simpMessagingTemplate.convertAndSend("/user/" + recvm.recieverUserName + "/queue/private", recvm);
		SaveMessage(msg);

	}
	
	public Boolean SaveMessage(Message message) {
		
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("savemessage"); 		
		
		
		
		TranslatedMessage translatedMessage = message.translatedMessage;
//		System.out.println(message.userMessageMapping.getIsDirectMessage());
		UserMessageMapping userMessageMapping = message.userMessageMapping;
		userMessageMapping.setSenderUserId(message.userMessageMapping.SenderUserId);
		userMessageMapping.setRecieverUserId(message.userMessageMapping.RecieverUserId);
		userMessageMapping.setGroupId(message.userMessageMapping.GroupId);

        //Declare the parameters in the same order
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Boolean.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
        
        //Pass the parameter values
        query.setParameter(1, userMessageMapping.getSenderUserId());
        query.setParameter(2, userMessageMapping.getRecieverUserId());
        query.setParameter(3, true);
        //query.setParameter(3, userMessageMapping.getIsDirectMessage());
        query.setParameter(4, userMessageMapping.getGroupId());
        query.setParameter(5, translatedMessage.getCultureCode());
        query.setParameter(6, translatedMessage.getSenderMessage());
        query.setParameter(7, translatedMessage.getTranslatedMessage());
        //Execute query
        query.execute();
		return true;
	}
	
	public String FetchMessagesForUser(UserConnections usrcon) throws JSONException {

        //"login" this is the name of your procedure
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("fetchmessagesforuser"); 

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);

        //Pass the parameter values
        query.setParameter(1, usrcon.getUserID());
        List<String> lstUseridWithMsgs = new ArrayList();
        
        List<Object[]> s = query.getResultList();
        JSONArray ja = new JSONArray();
        JSONArray FinalJson = new JSONArray();
        
        JSONObject jsonObj = null;
        
        for (int i = 0; i < s.size(); i++) {
        	jsonObj = new JSONObject();
			Object o[] = s.get(i);
			Object temp[] = s.get(i);
			JSONObject chatmsg = new JSONObject();
			//check logged in user logic username
			
			int j;
			int threadno=(int)o[8];
			for(j=i+1;j<s.size();j++) {
				chatmsg = new JSONObject();
				
				Object temp1[] = s.get(j);
				if(threadno != (int)temp1[8]) {
					break;
				}
				String recusername1 = (String)temp1[6]; //rec user name
				
				
				
				String senusername1 = (String)temp1[4];
				String msg1 = (String)temp1[0];
				String tran1 = (String)temp1[1];
				
				chatmsg.put("senderUserName", senusername1);
				chatmsg.put("recieverUserName", recusername1);
				chatmsg.put("userMessage", msg1);
				chatmsg.put("translatedMessage", tran1);
				threadno = (int)temp1[8];
				chatmsg.put("threadno", threadno);
				ja.put(chatmsg);
				jsonObj = new JSONObject();
			}
			
			i = j-1;
			o = s.get(i);
			if((String)o[9] == (String)o[6]) {
				jsonObj.put("username",(String)o[4]);
				jsonObj.put("name",(String)o[5]);
				jsonObj.put("userID",(int)o[12]);
				jsonObj.put("language",(String)o[13]);
				lstUseridWithMsgs.add((String)o[4]);
			}
			else {
				jsonObj.put("username",(String)o[6]);
				jsonObj.put("name",(String)o[7]);
				jsonObj.put("userID",(int)o[10]);
				jsonObj.put("language",(String)o[11]);
				lstUseridWithMsgs.add((String)o[6]);
			}
			String reclang = (String)o[11];//
			int recuserid = (int)o[10];
			
//			jsonObj.put("name",(String)o[5]);
//			jsonObj.put("name",(String)o[5]);

			String recusername = (String)o[6]; //rec user name
			String recfirstname = (String)o[7]; //rec first name
			
			
			String senusername = (String)o[4];
			String msg = (String)o[0];
			String tran = (String)o[1];
			
			chatmsg.put("senderUserName", senusername);
			chatmsg.put("recieverUserName", recusername);
			chatmsg.put("userMessage", msg);
			chatmsg.put("translatedMessage", tran);
			threadno = (int)o[8];
			ja.put(chatmsg);
			jsonObj.put("chatMessages", ja);
			FinalJson.put(jsonObj);
			ja = new JSONArray();
			
		}
        
        //fetch connections with no msgs
        StoredProcedureQuery query1 = entityManager.createStoredProcedureQuery("fetch_connections"); 

        query1.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);

        //Pass the parameter values
        query1.setParameter(1, usrcon.getUserID());
//        List<String> lstUseridWithMsgs = new ArrayList();
        
        List<Object[]> set1 = query1.getResultList();
        for (int i = 0; i < set1.size(); i++) {
			Object o[] = set1.get(i);
			if(lstUseridWithMsgs.contains((String)o[1])) {
				continue;
			}
			jsonObj = new JSONObject();
			jsonObj.put("username",(String)o[1]);
			jsonObj.put("name",(String)o[2]);
			jsonObj.put("userID",(int)o[0]);
			jsonObj.put("language",(String)o[4]);
			jsonObj.put("chatMessages", new JSONArray());
			FinalJson.put(jsonObj);
		}
        
        return FinalJson.toString();
    }
}
