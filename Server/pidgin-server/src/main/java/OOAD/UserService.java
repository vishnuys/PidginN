package OOAD;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userrepo;
	
	public List<UserTable> GetAllUsers() {
		List<UserTable> users = new ArrayList<>();
		userrepo.findAll().forEach(users::add);
		return users;
	}
	
	public void AddUser(UserTable user) {
		userrepo.save(user);
	}

}
