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
//			System.out.println("0 - " + o[0]);
//			BigInteger abc= new BigInteger("0");
//			BigInteger abc1= new BigInteger(o[0]);
//			if(o[0] == abc) {
//        		System.out.println("dskajdn");
//        		usrdet = new UserDetails();
//        		break;
//        	}
//			System.out.println("dsadbjasbd");
//	        System.out.println("0 - " + o[0]);
//	        System.out.println("1 - " + o[1]);
//	        System.out.println("2 - " + o[2]);
//	        System.out.println("3 - " + o[3]);
//	        System.out.println("4 - " + o[4]);
//	        System.out.println("5 - " + o[5]);


			usrdet = new UserDetails();
			if((int)o[0] == 0) {
				break;
			}
			System.out.println("dajda");
			
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
		System.out.println(message.userMessageMapping.getIsDirectMessage());
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
        System.out.println("kjiwjiwj " + inuserID);
        //Pass the parameter values
//        int inuserID = userID;
        query.setParameter(1, inuserID);
        
        List<Object[]> s = query.getResultList();
        System.out.print(s);
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
        
        List<Object[]> s = query.getResultList();
        JSONArray ja = new JSONArray();
        JSONArray FinalJson = new JSONArray();
        
        JSONObject jsonObj = null;
        
        for (int i = 0; i < s.size(); i++) {
        	System.out.println(" i = "+i);
        	jsonObj = new JSONObject();
			Object o[] = s.get(i);
			Object temp[] = s.get(i);
	        System.out.println("1 0 - " + temp[0]);
	        System.out.println("1 1 - " + temp[1]);
	        System.out.println("1 2 - " + temp[2]);
	        System.out.println("1 3 - " + temp[3]);
	        System.out.println("1 4 - " + temp[4]);
	        System.out.println("1 5 - " + temp[5]);
	        System.out.println("1 6 - " + temp[6]);
	        System.out.println("1 7 - " + temp[7]);
	        System.out.println("1 8 - " + temp[8]);
	        System.out.println("1 9 - " + temp[9]);

			JSONObject chatmsg = new JSONObject();
			//check logged in user logic username
			
//			System.out.println("1");
			int j;
			int threadno=(int)o[8];
			for(j=i+1;j<s.size();j++) {
				chatmsg = new JSONObject();
				
				System.out.println(" j = "+ j);
				Object temp1[] = s.get(j);
		        System.out.println("2 0 - " + temp1[0]);
		        System.out.println("2 1 - " + temp1[1]);
		        System.out.println("2 2 - " + temp1[2]);
		        System.out.println("2 3 - " + temp1[3]);
		        System.out.println("2 4 - " + temp1[4]);
		        System.out.println("2 5 - " + temp1[5]);
		        System.out.println("2 6 - " + temp1[6]);
		        System.out.println("2 7 - " + temp1[7]);
		        System.out.println("2 8 - " + temp1[8]);
		        System.out.println("2 9 - " + temp1[9]);
				if(threadno != (int)temp1[8]) {
					break;
				}
				String recusername1 = (String)temp1[6]; //rec user name
				
				String senusername1 = (String)temp1[4];
				String msg1 = (String)temp1[0];
				String tran1 = (String)temp1[1];
				
				chatmsg.put("sender", senusername1);
				chatmsg.put("receiver", recusername1);
				chatmsg.put("message", msg1);
				chatmsg.put("translatedMessage", tran1);
				threadno = (int)temp1[8];
				chatmsg.put("threadno", threadno);
				System.out.println(temp1.toString());
				ja.put(chatmsg);
				jsonObj = new JSONObject();
			}
			
			i = j-1;
			o = s.get(i);
			System.out.println("after " + i);
			if((String)o[9] == (String)o[6]) {
				jsonObj.put("username",(String)o[4]);
				jsonObj.put("name",(String)o[5]);
			}
			else {
				jsonObj.put("username",(String)o[6]);
				jsonObj.put("name",(String)o[7]);
			}

			String recusername = (String)o[6]; //rec user name
			String recfirstname = (String)o[7]; //rec first name
			
			String senusername = (String)o[4];
			String msg = (String)o[0];
			String tran = (String)o[1];
			
			chatmsg.put("sender", senusername);
			chatmsg.put("receiver", recusername);
			chatmsg.put("message", msg);
			chatmsg.put("translatedMessage", tran);
			threadno = (int)o[8];
			ja.put(chatmsg);
			jsonObj.put("chatMessages", ja);
			FinalJson.put(jsonObj);
			ja = new JSONArray();
			
		}        
        return FinalJson.toString();
    }
	
}
