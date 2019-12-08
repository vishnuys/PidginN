package OOAD;

import java.util.ArrayList;
import java.math.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.ResultSet;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import OOAD.Entities.*;



@RestController
public class MySqlController {
	
	@RequestMapping(method = RequestMethod.POST, value="/sqlhi")
	public String sayHi() throws Exception {
		return "sjkhs";
	}
	
	@PersistenceContext
    private EntityManager entityManager;
	
	//methods
	@RequestMapping(method = RequestMethod.POST, value="/checkusercreds")
	public UserDetails CheckUserCreds(@RequestBody UserDetails usr) {
		
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("checkusercreds"); 

        //Declare the parameters in the same order
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);


        //Pass the parameter values
        query.setParameter(1, usr.getUsername());
        query.setParameter(2, usr.getPassword());
        
//        Boolean isValid = (Boolean) query.getOutputParameterValue(3);
        List<Object[]> s = query.getResultList();
        UserDetails usrdet = null;
        for (int i = 0; i < s.size(); i++) {
        	
        	
			Object o[] = s.get(i);

			usrdet = new UserDetails();
			if((int)o[0] == 0) {
				break;
			}
			usrdet.setUserID((int)o[0]);
			usrdet.setUsername((String)o[1]);
			usrdet.setFirstName((String)o[2]);
			usrdet.setLastName((String)o[3]);
			usrdet.setPreferredLanguage((String)o[4]);
			usrdet.setContactNo((String)o[5]);
		}

        //Execute query
        query.execute();
		return usrdet;

	}
	
	@RequestMapping(method = RequestMethod.POST, value="/fetchloggedinuserdetails")
	public UserDetails FetchLoggedInUserDetails(@RequestBody String username) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("fetchloggedinuserdetails");
		
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		query.setParameter(1, username);

        List<Object[]> s = query.getResultList();
        UserDetails usrdet = null;
        for (int i = 0; i < s.size(); i++) {
        	
			Object o[] = s.get(i);


			usrdet = new UserDetails();
			usrdet.setUserID((int)o[0]);
			usrdet.setUsername((String)o[1]);
			usrdet.setFirstName((String)o[2]);
			usrdet.setLastName((String)o[3]);
			usrdet.setPreferredLanguage((String)o[4]);
			usrdet.setContactNo((String)o[5]);
		}
        return usrdet;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/insertuser")
	public Boolean InserUser(@RequestBody UserDetails usr) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("create_user"); 

        //Declare the parameters in the same order
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);

        //Pass the parameter values
        query.setParameter(1, usr.getUsername());
        query.setParameter(2, usr.getFirstName());
        query.setParameter(3, usr.getLastName());
        query.setParameter(4, usr.getPassword());
        query.setParameter(5, usr.getPreferredLanguage());
        query.setParameter(6, usr.getContactNo());
        //Execute query
        query.execute();
		return true;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/updateuserlanguage")
	public Boolean UpdateUserLangauage(@RequestBody UserDetails usr) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("update_userlangauage"); 

        //Declare the parameters in the same order
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);

        //Pass the parameter values
        query.setParameter(1, usr.getUserID());
        query.setParameter(2, usr.getPreferredLanguage());

        //Execute query
        query.execute();
		return true;
		
	}
	
 
	@RequestMapping(method = RequestMethod.POST, value="/addconnection")
	public Boolean AddConnection(@RequestBody UserConnections usrcon) {
		
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("add_connection"); 

        //Declare the parameters in the same order
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);

        //Pass the parameter values
        query.setParameter(1, usrcon.getUserID());
        query.setParameter(2, usrcon.getConnectionUserID());
        //Execute query
        query.execute();
		return true;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/savemessage")
	public Boolean SaveMessage(@RequestBody Message message) {
		
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("savemessage"); 		
	
		TranslatedMessage translatedMessage = message.translatedMessage;
		
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
        query.setParameter(3, userMessageMapping.getIsDirectMessage());
        query.setParameter(4, userMessageMapping.getGroupId());
        query.setParameter(5, translatedMessage.getCultureCode());
        query.setParameter(6, translatedMessage.getSenderMessage());
        query.setParameter(7, translatedMessage.getTranslatedMessage());
        //Execute query
        query.execute();
		return true;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/fetchallusers")
    public List<UserDetails> FetchAllUsers() {

        //"login" this is the name of your procedure
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("fetch_all_users"); 

        List<Object[]> s = query.getResultList();
        List<UserDetails> lstusers = new ArrayList<UserDetails>();
        for (int i = 0; i < s.size(); i++) {
			Object o[] = s.get(i);
			UserDetails usrdet = new UserDetails();
			usrdet.setUserID((int)o[0]);
			usrdet.setUsername((String)o[1]);
			usrdet.setFirstName((String)o[2]);
			usrdet.setLastName((String)o[3]);
			usrdet.setPreferredLanguage((String)o[4]);
			usrdet.setContactNo((String)o[5]);
			lstusers.add(usrdet);
		}
        return lstusers;
    }

	
	@RequestMapping(method = RequestMethod.POST, value="/fetchallconnections")
    public List<UserDetails> FetchAllConnections(@RequestBody Integer inuserID) {

        //"login" this is the name of your procedure
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("fetch_connections");
//        int userID = 0;
//		try {
//			userID = a.getInt("userID");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        //Pass the parameter values
//        int inuserID = userID;
        query.setParameter(1, inuserID);
        
        List<Object[]> s = query.getResultList();
        List<UserDetails> lstusers = new ArrayList<UserDetails>();
        for (int i = 0; i < s.size(); i++) {
			Object o[] = s.get(i);
			UserDetails usrdet = new UserDetails();
			usrdet.setUserID((int)o[0]);
			usrdet.setUsername((String)o[1]);
			usrdet.setFirstName((String)o[2]);
			usrdet.setLastName((String)o[3]);
			usrdet.setPreferredLanguage((String)o[4]);
			usrdet.setContactNo((String)o[5]);
			lstusers.add(usrdet);
		}
        return lstusers;
    }
	
	@RequestMapping(method = RequestMethod.POST, value="/fetchmessageforuser")
    public String FetchMessagesForUser(@RequestBody UserConnections usrcon) throws JSONException {

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
			
			if(o[9].toString().equals(o[6].toString())) {
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
			int j;
			String reclang = (String)o[11];//
			int recuserid = (int)o[10];	

			String recusername = (String)o[6]; //rec user name
			String recfirstname = (String)o[7]; //rec first name
			
			
			String senusername = (String)o[4];
			String msg = (String)o[0];
			String tran = (String)o[1];
			
			chatmsg.put("senderUserName", senusername);
			chatmsg.put("recieverUserName", recusername);
			chatmsg.put("userMessage", msg);
			chatmsg.put("translatedMessage", tran);

			int threadno=(int)o[8];
			threadno = (int)o[8];
			ja.put(chatmsg);
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
				//jsonObj = new JSONObject();
			}
			
			i = j-1;
			o = s.get(i);
			
			
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
