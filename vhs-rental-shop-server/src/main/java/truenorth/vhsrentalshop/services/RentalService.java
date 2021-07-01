package truenorth.vhsrentalshop.services;

import java.util.List;
import java.util.Optional;

import truenorth.vhsrentalshop.model.Rental;
import truenorth.vhsrentalshop.model.RentalDto;

public interface RentalService {
	
	List<Rental> getAllRentals();
	
	Rental getRental(int id);
	
	Rental addRental(RentalDto rentalDto);
	
	Rental returnRental(int id);
	
	boolean deleteRental(int id);

}
