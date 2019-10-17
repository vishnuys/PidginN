package OOAD;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserTable, String> {

}
