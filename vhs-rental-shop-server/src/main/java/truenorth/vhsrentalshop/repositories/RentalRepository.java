package truenorth.vhsrentalshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import truenorth.vhsrentalshop.model.Rental;

import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    Optional<Rental> findByVhsId(int vhsId);

}
