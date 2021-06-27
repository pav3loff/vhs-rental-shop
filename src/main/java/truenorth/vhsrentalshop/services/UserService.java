package truenorth.vhsrentalshop.services;

import java.util.List;

import truenorth.vhsrentalshop.model.User;
import truenorth.vhsrentalshop.model.UserDto;

public interface UserService {
	
	List<User> getAllUsers();
	
	User getUser(int id);
	
	User addUser(UserDto userDto);
	
	User updateUser(int id, UserDto userDto);
	
	boolean deleteUser(int id);

}
