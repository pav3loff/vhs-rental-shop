package truenorth.vhsrentalshop.services;

import java.util.List;

import truenorth.vhsrentalshop.model.CreateUserDto;
import truenorth.vhsrentalshop.model.MyUserDto;
import truenorth.vhsrentalshop.model.Role;
import truenorth.vhsrentalshop.model.UpdateUserDto;

public interface UserService {
	
	List<MyUserDto> getAllUsers();
	
	MyUserDto getUser(String username);
	
	MyUserDto addUser(CreateUserDto createUserDto);
	
	MyUserDto updateUser(String username, UpdateUserDto updateUserDto);
	
	boolean deleteUser(String username);

}
