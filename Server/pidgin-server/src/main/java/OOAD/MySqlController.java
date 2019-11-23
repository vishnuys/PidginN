package OOAD;

import java.util.ArrayList;
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
	
	//not working
	@RequestMapping(method = RequestMethod.POST, value="/savemessage")
	public Boolean SaveMessage(@RequestBody JSONObject a) {
		
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("add_connection"); 
		JSONObject jsonTranslatedMessage = null,jsonUserMessageMapping = null;
		System.out.println(a);
		try {
			jsonTranslatedMessage = a.getJSONObject("translatedMessage");
			jsonUserMessageMapping = a.getJSONObject("userMessageMapping");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonTranslatedMessage);
		System.out.println(jsonUserMessageMapping);
		
		
		
		TranslatedMessage translatedMessage = new TranslatedMessage();
		UserMessageMapping userMessageMapping = new UserMessageMapping();
        //Declare the parameters in the same order
//        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter(3, Boolean.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
//
//        //Pass the parameter values
//        query.setParameter(1, userMessageMapping.getSenderUserId());
//        query.setParameter(2, userMessageMapping.getRecieverUserId());
//        query.setParameter(3, userMessageMapping.getIsDirectMessage());
//        query.setParameter(4, userMessageMapping.getGroupId());
//        query.setParameter(5, translatedMessage.getCultureCode());
//        query.setParameter(6, translatedMessage.getSenderMessage());
//        query.setParameter(7, translatedMessage.getTranslatedMessage());
//        //Execute query
//        query.execute();
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
	
	//not working
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
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);

        //Pass the parameter values
        query.setParameter(1, usrcon.getUserID());
        query.setParameter(2, usrcon.getConnectionUserID());
        
        List<Object[]> s = query.getResultList();
        JSONArray ja = new JSONArray();
        JSONObject jsonObj = null;
        for (int i = 0; i < s.size(); i++) {
			Object o[] = s.get(i);
			String sender = (String)o[0];
			String rec = (String)o[1];
			String msg = (String)o[2];
			String trmsg = (String)o[3];
			jsonObj = new JSONObject();
			jsonObj.put("sender",sender);
			jsonObj.put("receiver",rec);
			jsonObj.put("message",msg);
			jsonObj.put("translatedMessage",trmsg);
			System.out.println(jsonObj);
			ja.put(jsonObj);
			
		}
        JSONObject j = new JSONObject();
        j.put("chatMessages", ja);
        
        return j.toString();
    }
	
}
