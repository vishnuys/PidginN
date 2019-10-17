package OOAD;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@EnableAutoConfiguration
@Controller
public class testController {
	
	@Autowired
	private UserService user;
 
    @RequestMapping("/test")
    public String viewHome() {
        return "test.html";
    }
    
    @RequestMapping("/users")
    public List<User> allUsers() {
        return user.GetAllUsers();
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/addUser")
    public void addUser(@RequestBody User u) {
        user.AddUser(u);
    }
}