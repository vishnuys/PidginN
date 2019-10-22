package OOAD;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestHandler {
	
	@Autowired
	private UserService user;
	
	@RequestMapping("/users")
    public List<UserTable> allUsers() {
        return user.GetAllUsers();
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/addUser")
    public void addUser(@RequestBody UserTable u) {
        user.AddUser(u);
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/login")
    public String login(@RequestParam("username") String uname, @RequestParam("password") String pwd) {
        List<UserTable> users = user.GetAllUsers();
        for(UserTable user:users) {
        	System.out.println(uname + " " + user.getUsername().toString());
        	System.out.println(pwd + " " + user.getPassword().toString());
        	if(user.getUsername().equals(uname) && user.getPassword().equals(pwd)) {
        		return "Success";
        	}
        }
        return "Failed";
    }
}
