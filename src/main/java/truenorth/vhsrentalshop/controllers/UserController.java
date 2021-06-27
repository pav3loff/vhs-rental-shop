package truenorth.vhsrentalshop.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import truenorth.vhsrentalshop.model.User;
import truenorth.vhsrentalshop.model.UserDto;
import truenorth.vhsrentalshop.services.UserService;

@Controller
@RequestMapping("/api/users")
@Slf4j
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable int id) {
		User user = userService.getUser(id);
		
		if(user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(String.format("User with id %d not found!", id));
		}
		
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<?> addUser(@Valid @RequestBody UserDto userDto) {
		log.info(String.format("Add user: %s", userDto));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable int id, @Valid @RequestBody UserDto userDto) {
		log.info(String.format("Update user: %s", userDto));
		
		User user = userService.updateUser(id, userDto);
		
		if(user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(String.format("User with id %d not found!", id));
		}
		
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) {		
		if(userService.deleteUser(id)) {
			return ResponseEntity.noContent().build();
		} 
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(String.format("User with id %d not found!", id));
	}

}
