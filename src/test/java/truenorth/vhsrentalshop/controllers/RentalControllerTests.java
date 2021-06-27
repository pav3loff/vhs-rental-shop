package truenorth.vhsrentalshop.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import truenorth.vhsrentalshop.model.Rental;
import truenorth.vhsrentalshop.model.RentalDto;
import truenorth.vhsrentalshop.model.User;
import truenorth.vhsrentalshop.model.Vhs;
import truenorth.vhsrentalshop.services.RentalService;

@WebMvcTest(controllers = RentalController.class)
public class RentalControllerTests {
	
	@MockBean
	private RentalService rentalService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	@BeforeEach
	public void init() {
		objectMapper = new ObjectMapper();
		
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
	
	@Test
	public void getAllRentalsTest() throws Exception {
		User user1 = new User(1, "Test 1", "Test 1", new LinkedList<>());
		User user2 = new User(1, "Test 2", "Test 2", new LinkedList<>());
		
		Vhs vhs1 = new Vhs(1, "Test 1", "Test 1", 1990);
		Vhs vhs2 = new Vhs(2, "Test 2", "Test 2", 1991);
		
		LocalDateTime dateRented = LocalDateTime.of(2020, 1, 1, 1, 1);
		LocalDateTime dueDate = LocalDateTime.of(2020, 2, 1, 1, 1);
		
		Rental rental1 = new Rental(1, vhs1, user1, dateRented, dueDate, null, BigDecimal.valueOf(0));
		Rental rental2 = new Rental(1, vhs2, user2, dateRented, dueDate, null, BigDecimal.valueOf(0));
		
		List<Rental> rentals = new LinkedList<>();
		rentals.add(rental1);
		rentals.add(rental2);
		
		when(rentalService.getAllRentals()).thenReturn(rentals);
		
		mockMvc.perform(get("/api/rentals"))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(rentals)));
	}
	
	@Test
	public void getRentalSuccessTest() throws Exception {
		User user = new User(1, "Test 1", "Test 1", new LinkedList<>());
		
		Vhs vhs = new Vhs(1, "Test 1", "Test 1", 1990);
		
		LocalDateTime dateRented = LocalDateTime.of(2020, 1, 1, 1, 1);
		LocalDateTime dueDate = LocalDateTime.of(2020, 2, 1, 1, 1);
		
		Rental rental = new Rental(1, vhs, user, dateRented, dueDate, null, BigDecimal.valueOf(0));
		
		when(rentalService.getRental(1)).thenReturn(rental);
		
		mockMvc.perform(get("/api/rentals/1"))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(rental)));
	}
	
	@Test
	public void getNonExistingRentalTest() throws Exception {
		when(rentalService.getRental(1)).thenReturn(null);
		
		mockMvc.perform(get("/api/rentals/1"))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void addRentalSuccessTest() throws Exception {
		User user = new User(1, "Test 1", "Test 1", new LinkedList<>());
		
		Vhs vhs = new Vhs(1, "Test 1", "Test 1", 1990);
		
		RentalDto rentalDto = new RentalDto(1, 1);
		LocalDateTime dateRented = LocalDateTime.of(2020, 1, 1, 1, 1);
		LocalDateTime dueDate = LocalDateTime.of(2020, 2, 1, 1, 1);
		
		Rental rental = new Rental(1, vhs, user, dateRented, dueDate, null, BigDecimal.valueOf(0));
		
		when(rentalService.addRental(rentalDto)).thenReturn(rental);
		
		mockMvc.perform(post("/api/rentals")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(rentalDto)))
			.andExpect(status().isCreated())
			.andExpect(content().json(objectMapper.writeValueAsString(rental)));
	}
	
	@Test
	public void addRentalWithNonExistingUserTest() throws Exception {
		RentalDto rentalDto = new RentalDto(1, 1);
		
		when(rentalService.addRental(rentalDto)).thenThrow(new IllegalArgumentException());
		
		mockMvc.perform(post("/api/rentals")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(rentalDto)))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void addRentalWithNonExistingVhsTest() throws Exception {
		RentalDto rentalDto = new RentalDto(1, 1);
		
		when(rentalService.addRental(rentalDto)).thenThrow(new IllegalArgumentException());
		
		mockMvc.perform(post("/api/rentals")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(rentalDto)))
			.andExpect(status().isNotFound());
	}
	
	@Test 
	public void returnRentalTest() throws Exception {
		User user = new User(1, "Test 1", "Test 1", new LinkedList<>());
		
		Vhs vhs = new Vhs(1, "Test 1", "Test 1", 1990);
		
		LocalDateTime dateRented = LocalDateTime.of(2020, 1, 1, 1, 1);
		LocalDateTime dueDate = LocalDateTime.of(2020, 2, 1, 1, 1);
		LocalDateTime dateReturned = LocalDateTime.of(2020, 1, 10, 1, 1);
		
		Rental rental = new Rental(1, vhs, user, dateRented, dueDate, dateReturned, BigDecimal.valueOf(0));
		
		when(rentalService.returnRental(1)).thenReturn(rental);
		
		mockMvc.perform(put("/api/rentals/1")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(rental)));
	}
	
	@Test
	public void returnNonExistingRentalTest() throws Exception {
		when(rentalService.returnRental(1)).thenReturn(null);
		
		mockMvc.perform(put("/api/rentals/1")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void deleteRentalSuccessTest() throws Exception {
		when(rentalService.deleteRental(1)).thenReturn(true);
		
		mockMvc.perform(delete("/api/rentals/1")).andExpect(status().isNoContent());
	}
	
	@Test
	public void deleteNonExistingRentalTest() throws Exception {
		when(rentalService.deleteRental(1)).thenReturn(false);
		
		mockMvc.perform(delete("/api/rentals/1")).andExpect(status().isNotFound());
	}

}
