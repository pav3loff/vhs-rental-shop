package truenorth.vhsrentalshop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import truenorth.vhsrentalshop.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	boolean existsByUsername(String username);
	
	Optional<User> findByUsername(String username);

}
