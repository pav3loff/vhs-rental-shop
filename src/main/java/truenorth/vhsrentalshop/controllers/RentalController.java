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
import truenorth.vhsrentalshop.model.Rental;
import truenorth.vhsrentalshop.model.RentalDto;
import truenorth.vhsrentalshop.services.RentalService;

@Controller
@RequestMapping("/api/rentals")
@Slf4j
public class RentalController {
	
	private RentalService rentalService;
	
	@Autowired
	public RentalController(RentalService rentalService) {
		this.rentalService = rentalService;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllRentals() {
		return ResponseEntity.ok(rentalService.getAllRentals());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getRental(@PathVariable int id) {
		Rental rental = rentalService.getRental(id);
		
		if(rental == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(String.format("Rental with id %d not found!", id));
		}
		
		return ResponseEntity.ok(rental);
	}
	
	@PostMapping
	public ResponseEntity<?> addRental(@Valid @RequestBody RentalDto rentalDto) {
		log.info(String.format("Add rental: %s", rentalDto));
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(rentalService.addRental(rentalDto));
		} catch(IllegalArgumentException exc) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> returnRental(@PathVariable int id) {
		log.info(String.format("Return rental with id %d", id));
		
		Rental rental = rentalService.returnRental(id);
		
		if(rental == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(String.format("Rental with id %d not found!", id));
		}
		
		return ResponseEntity.ok(rental);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRental(@PathVariable int id) {
		log.info(String.format("Delete rental with id %d", id));
		
		if(rentalService.deleteRental(id)) {
			return ResponseEntity.noContent().build();
		} 
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(String.format("Rental with id %d not found!", id));
	}

}
