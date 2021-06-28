package truenorth.vhsrentalshop.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import truenorth.vhsrentalshop.model.CreateUserDto;
import truenorth.vhsrentalshop.model.MyUserDto;
import truenorth.vhsrentalshop.model.UpdateUserDto;
import truenorth.vhsrentalshop.services.UserService;

@Controller
@RequestMapping("/api/users")
@Slf4j
public class UserController {
	
	private UserService userService;
	
	private UserDetailsService userDetailsService;
	
	@Autowired
	public UserController(UserService userService, UserDetailsService userDetailsService) {
		this.userService = userService;
		this.userDetailsService = userDetailsService;
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/{username}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<?> getUser(@PathVariable String username, Principal principal) {
		String loggedUserUsername = principal.getName();
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(loggedUserUsername);
		
		if(!(loggedUserUsername.equals(username)) && 
				!(userDetails.getAuthorities().stream().anyMatch(
						authority -> authority.getAuthority().equals("ROLE_ADMIN")))) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		MyUserDto myUserDto = userService.getUser(username);
		
		if(myUserDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(String.format("User with username %s not found!", username));
		}
		
		return ResponseEntity.ok(myUserDto);
	}
	
	@PostMapping
	public ResponseEntity<?> addUser(@Valid @RequestBody CreateUserDto createUserDto) {
		try {
			MyUserDto myUserDto = userService.addUser(createUserDto);
			
			log.info(String.format("Add user: %s", createUserDto));
			
			return ResponseEntity.status(HttpStatus.CREATED).body(myUserDto);
		} catch(IllegalArgumentException exc) {
			return ResponseEntity.badRequest().body(exc.getMessage());
		}
	}
	
	@PutMapping("/{username}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<?> updateUser(@PathVariable String username, 
			@Valid @RequestBody UpdateUserDto updateUserDto, Principal principal) {
		if(!(principal.getName().equals(username))) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		MyUserDto myUserDto = userService.updateUser(username, updateUserDto);
		
		if(myUserDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(String.format("User with username %s not found!", username));
		}
		
		return ResponseEntity.ok(myUserDto);
	}
	
	@DeleteMapping("/{username}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<?> deleteUser(@PathVariable String username, Principal principal) {	
		if(!(principal.getName().equals(username))) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		if(userService.deleteUser(username)) {
			return ResponseEntity.noContent().build();
		} 
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(String.format("User with username %s not found!", username));
	}

}
