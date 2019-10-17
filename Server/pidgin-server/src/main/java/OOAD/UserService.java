package OOAD;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userrepo;
	
	public List<User> GetAllUsers() {
		List<User> users = new ArrayList<>();
		userrepo.findAll().forEach(users::add);
		return users;
	}
	
	public void AddUser(User user) {
		userrepo.save(user);
	}

}
