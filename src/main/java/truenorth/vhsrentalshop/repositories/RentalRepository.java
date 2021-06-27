package truenorth.vhsrentalshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import truenorth.vhsrentalshop.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

}
