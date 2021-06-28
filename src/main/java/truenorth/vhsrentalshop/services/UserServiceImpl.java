package truenorth.vhsrentalshop.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import truenorth.vhsrentalshop.model.Role;
import truenorth.vhsrentalshop.model.UpdateUserDto;
import truenorth.vhsrentalshop.model.User;
import truenorth.vhsrentalshop.model.CreateUserDto;
import truenorth.vhsrentalshop.model.MyUserDto;
import truenorth.vhsrentalshop.repositories.UserRepository;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<MyUserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<MyUserDto> userDtos = new LinkedList<>();
		
		for(User user : users) {
			userDtos.add(new MyUserDto(user.getUsername(), user.getFirstName(), 
					user.getLastName(), user.getRentals()));
		}
		
		return userDtos;
	}

	@Override
	public MyUserDto getUser(String username) {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			
			return new MyUserDto(user.getUsername(), user.getFirstName(), user.getLastName(), user.getRentals());
		}
		
		return null;
	}

	@Override
	public MyUserDto addUser(CreateUserDto createUserDto) {
		String username = createUserDto.getUsername();
		
		if(userRepository.existsByUsername(username)) {
			throw new IllegalArgumentException(String.format("User with username %s already exists!", username));
		}
		
		User user = new User(username, passwordEncoder.encode(createUserDto.getPassword()), 
				createUserDto.getFirstName(), createUserDto.getLastName(), new LinkedList<>(), Role.USER);
		
		userRepository.save(user);
		
		return new MyUserDto(user.getUsername(), user.getFirstName(), user.getLastName(), user.getRentals());
	}

	@Override
	public MyUserDto updateUser(String username, UpdateUserDto updateUserDto) {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			
			log.info(String.format("Update user: %s", user.toString()));
			
			user.setFirstName(updateUserDto.getFirstName());
			user.setLastName(updateUserDto.getLastName());
			user.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));
			
			userRepository.saveAndFlush(user);
			
			return new MyUserDto(user.getUsername(), user.getFirstName(), user.getLastName(), user.getRentals());
		}
		
		return null;
	}

	@Override
	public boolean deleteUser(String username) {
		if(userRepository.existsByUsername(username)) {
			User user = userRepository.findByUsername(username).get();
			
			log.info(String.format("Delete user: %s", user.toString()));
			
			userRepository.deleteById(user.getId());
			
			return true;
		}
		
		return false;
	}

}
