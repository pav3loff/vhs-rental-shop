package truenorth.vhsrentalshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import truenorth.vhsrentalshop.model.CreateUserDto;
import truenorth.vhsrentalshop.model.MyUserDto;
import truenorth.vhsrentalshop.model.UpdateUserDto;
import truenorth.vhsrentalshop.services.UserService;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTests {
	
	@MockBean
	private UserService userService;

	@MockBean
	private SecurityContext securityContext;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	@WithMockUser(value = "test", roles = {"ADMIN"})
	public void getAllUsersTest() throws Exception {
		MyUserDto myUserDto1 = new MyUserDto("test1", "Test 1", "Test 1", new LinkedList<>());
		MyUserDto myUserDto2 = new MyUserDto("test2", "Test 2", "Test 2", new LinkedList<>());
		
		List<MyUserDto> userDtos = new LinkedList<>();
		userDtos.add(myUserDto1);
		userDtos.add(myUserDto2);
		
		when(userService.getAllUsers()).thenReturn(userDtos);
		
		mockMvc.perform(get("/api/users"))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(userDtos)));
	}
	
	@Test
	@WithMockUser(value = "test")
	public void getUserSuccessTest() throws Exception {
		MyUserDto myUserDto = new MyUserDto("test", "Test 1", "Test 1", new LinkedList<>());

		when(userService.getUser("test")).thenReturn(myUserDto);
		
		mockMvc.perform(get("/api/users/test").principal(() -> "test"))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(myUserDto)));
	}
	
	@Test
	@WithMockUser(value = "test")
	public void getNonExistingUserTest() throws Exception {
		when(userService.getUser("test")).thenReturn(null);
		
		mockMvc.perform(get("/api/users/test").principal(() -> "test"))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void addUserSuccessTest() throws Exception {
		CreateUserDto createUserDto = new CreateUserDto("Test 1", "Test 1", "test", "pass");
		MyUserDto myUserDto = new MyUserDto("test", "Test 1", "Test 1", new LinkedList<>());
		
		when(userService.addUser(createUserDto)).thenReturn(myUserDto);
		
		mockMvc.perform(post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(createUserDto)))
			.andExpect(status().isCreated())
			.andExpect(content().json(objectMapper.writeValueAsString(myUserDto)));
	}
	
	@Test
	public void addUserWithInvalidFirstNameTest() throws Exception {
		CreateUserDto createUserDto = new CreateUserDto("", "Test 1", "test", "pass");
		
		mockMvc.perform(post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(createUserDto)))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void addUserWithInvalidLastNameTest() throws Exception {
		CreateUserDto createUserDto = new CreateUserDto("Test 1", "", "test", "pass");
		
		mockMvc.perform(post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(createUserDto)))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void addUserWithInvalidUsernameTest() throws Exception {
		CreateUserDto createUserDto = new CreateUserDto("Test 1", "Test 1", "", "pass");
		
		mockMvc.perform(post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(createUserDto)))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void addUserWithInvalidPasswordTest() throws Exception {
		CreateUserDto createUserDto = new CreateUserDto("Test 1", "Test 1", "test", "");
		
		mockMvc.perform(post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(createUserDto)))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void addUserWithExistingUsernameTest() throws Exception {
		CreateUserDto createUserDto = new CreateUserDto("Test 1", "Test 1", "test", "pass");
		
		when(userService.addUser(createUserDto)).thenThrow(new IllegalArgumentException());
		
		mockMvc.perform(post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(createUserDto)))
			.andExpect(status().isBadRequest());
	}
	
	@Test 
	@WithMockUser(value = "test")
	public void updateUserSuccessTest() throws Exception {
		UpdateUserDto updateUserDto = new UpdateUserDto("Test 1", "Test 1", "pass");
		MyUserDto myUserDto = new MyUserDto("test", "Test 1", "Test 1", new LinkedList<>());
		
		when(userService.updateUser("test", updateUserDto)).thenReturn(myUserDto);
		
		mockMvc.perform(put("/api/users/test")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updateUserDto)))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(myUserDto)));
	}
	
	@Test 
	@WithMockUser(value = "test")
	public void updateUserWithInvalidFirstNameTest() throws Exception {
		UpdateUserDto updateUserDto = new UpdateUserDto("", "Test 1", "pass");
		
		when(userService.updateUser("test", updateUserDto)).thenReturn(null);
		
		mockMvc.perform(put("/api/users/test")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updateUserDto)))
			.andExpect(status().isBadRequest());
	}
	
	@Test 
	@WithMockUser(value = "test")
	public void updateUserWithInvalidLastNameTest() throws Exception {
		UpdateUserDto updateUserDto = new UpdateUserDto("Test 1", "", "pass");
		
		when(userService.updateUser("test", updateUserDto)).thenReturn(null);
		
		mockMvc.perform(put("/api/users/test")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updateUserDto)))
			.andExpect(status().isBadRequest());
	}
	
	@Test 
	@WithMockUser(value = "test")
	public void updateUserWithInvalidPasswordTest() throws Exception {
		UpdateUserDto updateUserDto = new UpdateUserDto("Test 1", "Test 1", "");
		
		when(userService.updateUser("test", updateUserDto)).thenReturn(null);
		
		mockMvc.perform(put("/api/users/test")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updateUserDto)))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	@WithMockUser(value = "test")
	public void updateNonExistingUserTest() throws Exception {
		UpdateUserDto updateUserDto = new UpdateUserDto("Test 1", "Test 1", "pass");
		
		when(userService.updateUser("test", updateUserDto)).thenReturn(null);
		
		mockMvc.perform(put("/api/users/test")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updateUserDto)))
			.andExpect(status().isNotFound());
	}
	
	@Test
	@WithMockUser(value = "test")
	public void deleteUserSuccessTest() throws Exception {
		when(userService.deleteUser("test")).thenReturn(true);
		
		mockMvc.perform(delete("/api/users/test")).andExpect(status().isNoContent());
	}
	
	@Test
	@WithMockUser(value = "test")
	public void deleteNonExistingUserTest() throws Exception {
		when(userService.deleteUser("test")).thenReturn(false);
		
		mockMvc.perform(delete("/api/users/test")).andExpect(status().isNotFound());
	}

}
