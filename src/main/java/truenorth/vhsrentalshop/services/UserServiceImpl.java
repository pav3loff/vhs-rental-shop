package truenorth.vhsrentalshop.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import truenorth.vhsrentalshop.model.User;
import truenorth.vhsrentalshop.model.UserDto;
import truenorth.vhsrentalshop.repositories.UserRepository;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(int id) {
		Optional<User> optionalUser = userRepository.findById(id);
		
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}
		
		return null;
	}

	@Override
	public User addUser(UserDto userDto) {
		User user = new User(userDto.getFirstName(), userDto.getLastName(), new LinkedList<>());
		
		return userRepository.save(user);
	}

	@Override
	public User updateUser(int id, UserDto userDto) {
		Optional<User> optionalUser = userRepository.findById(id);
		
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			
			return userRepository.saveAndFlush(user);
		}
		
		return null;
	}

	@Override
	public boolean deleteUser(int id) {
		if(userRepository.existsById(id)) {
			User user = userRepository.findById(id).get();
			
			log.info(String.format("Delete user: %s", user.toString()));
			
			userRepository.deleteById(id);
			
			return true;
		}
		
		return false;
	}

}
