package truenorth.vhsrentalshop.controllers;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import truenorth.vhsrentalshop.model.Vhs;
import truenorth.vhsrentalshop.model.VhsDto;
import truenorth.vhsrentalshop.services.VhsService;

@WebMvcTest(controllers = VhsController.class)
public class VhsControllerTests {

	@MockBean
	private VhsService vhsService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	@WithMockUser(value = "test", roles = {"ADMIN"})
	public void getAllVhsesTest() throws Exception {
		Vhs vhs1 = new Vhs(1, "Test 1", "Test 1", 1990);
		Vhs vhs2 = new Vhs(2, "Test 2", "Test 2", 1991);
		
		List<Vhs> vhses = new LinkedList<>();
		vhses.add(vhs1);
		vhses.add(vhs2);
		
		when(vhsService.getAllVhses()).thenReturn(vhses);
		
		mockMvc.perform(get("/api/vhses"))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(vhses)));
	}
	
	@Test
	@WithMockUser(value = "test", roles = {"ADMIN"})
	public void getVhsSuccessTest() throws Exception {
		Vhs vhs = new Vhs(1, "Test 1", "Test 1", 1990);
		
		when(vhsService.getVhs(1)).thenReturn(vhs);
		
		mockMvc.perform(get("/api/vhses/1"))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(vhs)));
		
	}
	
	@Test
	@WithMockUser(value = "test", roles = {"ADMIN"})
	public void getNonExistingVhsTest() throws Exception {
		when(vhsService.getVhs(1)).thenReturn(null);
		
		mockMvc.perform(get("/api/vhses/1"))
			.andExpect(status().isNotFound());
	}
	
	@Test
	@WithMockUser(value = "test", roles = {"ADMIN"})
	public void addVhsSuccessTest() throws Exception {
		VhsDto vhsDto = new VhsDto("Test 1", "Test 1", 1990);
		Vhs vhs = new Vhs(1, "Test 1", "Test 1", 1990);
		
		when(vhsService.addVhs(vhsDto)).thenReturn(vhs);
		
		mockMvc.perform(post("/api/vhses")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(vhsDto)))
			.andExpect(status().isCreated())
			.andExpect(content().json(objectMapper.writeValueAsString(vhs)));
	}
	
	@Test
	@WithMockUser(value = "test", roles = {"ADMIN"})
	public void addVhsWithInvalidTitleTest() throws Exception {
		VhsDto vhsDto = new VhsDto("", "Test 1", 1990);
		
		mockMvc.perform(post("/api/vhses")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(vhsDto)))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	@WithMockUser(value = "test", roles = {"ADMIN"})
	public void addVhsWithInvalidYearTest() throws Exception {
		VhsDto vhsDto = new VhsDto("Test 1", "Test 1", 1);
		
		mockMvc.perform(post("/api/vhses")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(vhsDto)))
			.andExpect(status().isBadRequest());
	}
	
	@Test 
	@WithMockUser(value = "test", roles = {"ADMIN"})
	public void updateVhsSuccessTest() throws Exception {
		VhsDto vhsDto = new VhsDto("Test 1 Updated", "Test 1 Updated", 1991);
		Vhs vhs = new Vhs(1, "Test 1 Updated", "Test 1 Updated", 1991);
		
		when(vhsService.updateVhs(1, vhsDto)).thenReturn(vhs);
		
		mockMvc.perform(put("/api/vhses/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(vhsDto)))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(vhs)));
	}
	
	@Test 
	@WithMockUser(value = "test", roles = {"ADMIN"})
	public void updateVhsWithInvalidTitleTest() throws Exception {
		VhsDto vhsDto = new VhsDto("", "Test 1 Updated", 1991);
		
		when(vhsService.updateVhs(1, vhsDto)).thenReturn(null);
		
		mockMvc.perform(put("/api/vhses/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(vhsDto)))
			.andExpect(status().isBadRequest());
	}
	
	@Test 
	@WithMockUser(value = "test", roles = {"ADMIN"})
	public void updateVhsWithInvalidYearTest() throws Exception {
		VhsDto vhsDto = new VhsDto("Test 1 Updated", "Test 1 Updated", 1);
		
		when(vhsService.updateVhs(1, vhsDto)).thenReturn(null);
		
		mockMvc.perform(put("/api/vhses/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(vhsDto)))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	@WithMockUser(value = "test", roles = {"ADMIN"})
	public void updateNonExistingVhsTest() throws Exception {
		VhsDto vhsDto = new VhsDto("Test 1 Updated", "Test 1 Updated", 1991);
		
		when(vhsService.updateVhs(1, vhsDto)).thenReturn(null);
		
		mockMvc.perform(put("/api/vhses/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(vhsDto)))
			.andExpect(status().isNotFound());
	}
	
	@Test
	@WithMockUser(value = "test", roles = {"ADMIN"})
	public void deleteVhsSuccessTest() throws Exception {
		when(vhsService.deleteVhs(1)).thenReturn(true);
		
		mockMvc.perform(delete("/api/vhses/1")).andExpect(status().isNoContent());
	}
	
	@Test
	@WithMockUser(value = "test", roles = {"ADMIN"})
	public void deleteNonExistingVhsTest() throws Exception {
		when(vhsService.deleteVhs(1)).thenReturn(false);
		
		mockMvc.perform(delete("/api/vhses/1")).andExpect(status().isNotFound());
	}

	@Test
	@WithMockUser(value = "test")
	public void isVhsAvailableTest() throws Exception {
		when(vhsService.isAvailable(1)).thenReturn(true);

		mockMvc.perform(get("/api/vhses/1/available")).andExpect(status().isOk());
	}
	
}
