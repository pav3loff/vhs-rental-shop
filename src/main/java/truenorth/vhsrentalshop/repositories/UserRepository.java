package truenorth.vhsrentalshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import truenorth.vhsrentalshop.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
