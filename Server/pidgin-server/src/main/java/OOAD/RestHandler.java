package OOAD;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

}
