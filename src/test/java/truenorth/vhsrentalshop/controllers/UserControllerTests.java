package truenorth.vhsrentalshop.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import truenorth.vhsrentalshop.model.User;
import truenorth.vhsrentalshop.model.UserDto;
import truenorth.vhsrentalshop.services.UserService;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTests {
	
	@MockBean
	private UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	public void getAllUsersTest() throws Exception {
		User user1 = new User(1, "Test 1", "Test 1", new LinkedList<>());
		User user2 = new User(1, "Test 2", "Test 2", new LinkedList<>());
		
		List<User> users = new LinkedList<>();
		users.add(user1);
		users.add(user2);
		
		when(userService.getAllUsers()).thenReturn(users);
		
		mockMvc.perform(get("/api/users"))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(users)));
	}
	
	@Test
	public void getUserSuccessTest() throws Exception {
		User user = new User(1, "Test 1", "Test 1", new LinkedList<>());
		
		when(userService.getUser(1)).thenReturn(user);
		
		mockMvc.perform(get("/api/users/1"))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(user)));
		
	}
	
	@Test
	public void getNonExistingUserTest() throws Exception {
		when(userService.getUser(1)).thenReturn(null);
		
		mockMvc.perform(get("/api/users/1"))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void addUserSuccessTest() throws Exception {
		UserDto userDto = new UserDto("Test 1", "Test 1");
		User user = new User(1, "Test 1", "Test 1", new LinkedList<>());
		
		when(userService.addUser(userDto)).thenReturn(user);
		
		mockMvc.perform(post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userDto)))
			.andExpect(status().isCreated())
			.andExpect(content().json(objectMapper.writeValueAsString(user)));
	}
	
	@Test
	public void addUserWithInvalidFirstNameTest() throws Exception {
		UserDto userDto = new UserDto("", "Test 1");
		
		mockMvc.perform(post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userDto)))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void addUserWithInvalidLastNameTest() throws Exception {
		UserDto userDto = new UserDto("Test 1", "");
		
		mockMvc.perform(post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userDto)))
			.andExpect(status().isBadRequest());
	}
	
	@Test 
	public void updateUserSuccessTest() throws Exception {
		UserDto userDto = new UserDto("Test 1 Updated", "Test 1 Updated");
		User user = new User(1, "Test 1 Updated", "Test 1 Updated", new LinkedList<>());
		
		when(userService.updateUser(1, userDto)).thenReturn(user);
		
		mockMvc.perform(put("/api/users/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userDto)))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(user)));
	}
	
	@Test 
	public void updateUserWithInvalidFirstNameTest() throws Exception {
		UserDto userDto = new UserDto("", "Test 1 Updated");
		
		when(userService.updateUser(1, userDto)).thenReturn(null);
		
		mockMvc.perform(put("/api/users/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userDto)))
			.andExpect(status().isBadRequest());
	}
	
	@Test 
	public void updateUserWithInvalidLastNameTest() throws Exception {
		UserDto userDto = new UserDto("Test 1 Updated", "");
		
		when(userService.updateUser(1, userDto)).thenReturn(null);
		
		mockMvc.perform(put("/api/users/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userDto)))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void updateNonExistingUserTest() throws Exception {
		UserDto userDto = new UserDto("Test 1 Updated", "Test 1 Updated");
		
		when(userService.updateUser(1, userDto)).thenReturn(null);
		
		mockMvc.perform(put("/api/users/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userDto)))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void deleteUserSuccessTest() throws Exception {
		when(userService.deleteUser(1)).thenReturn(true);
		
		mockMvc.perform(delete("/api/users/1")).andExpect(status().isNoContent());
	}
	
	@Test
	public void deleteNonExistingUserTest() throws Exception {
		when(userService.deleteUser(1)).thenReturn(false);
		
		mockMvc.perform(delete("/api/users/1")).andExpect(status().isNotFound());
	}

}
