package truenorth.vhsrentalshop.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import truenorth.vhsrentalshop.model.Vhs;
import truenorth.vhsrentalshop.model.VhsDto;
import truenorth.vhsrentalshop.services.VhsService;

@Controller
@RequestMapping("/api/vhses")
@Slf4j
public class VhsController {
	
	private VhsService vhsService;
	
	@Autowired
	public VhsController(VhsService vhsService) {
		this.vhsService = vhsService;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllVhses() {
		return ResponseEntity.ok(vhsService.getAllVhses());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getVhs(@PathVariable int id) {
		Vhs vhs = vhsService.getVhs(id);
		
		if(vhs == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(String.format("Vhs with id %d not found!", id));
		}
		
		return ResponseEntity.ok(vhs);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addVhs(@Valid @RequestBody VhsDto vhsDto) {
		log.info(String.format("Add vhs: %s", vhsDto));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(vhsService.addVhs(vhsDto));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateVhs(@PathVariable int id, @Valid @RequestBody VhsDto vhsDto) {
		log.info(String.format("Update vhs: %s", vhsDto));
		
		Vhs vhs = vhsService.updateVhs(id, vhsDto);
		
		if(vhs == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(String.format("Vhs with id %d not found!", id));
		}
		
		return ResponseEntity.ok(vhs);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteVhs(@PathVariable int id) {
		log.info(String.format("Delete vhs with id %d", id));
		
		if(vhsService.deleteVhs(id)) {
			return ResponseEntity.noContent().build();
		} 
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(String.format("Vhs with id %d not found!", id));
	}

	@GetMapping("/{id}/available")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<?> isAvailable(@PathVariable int id) {
		return ResponseEntity.ok(vhsService.isAvailable(id));
	}

}
