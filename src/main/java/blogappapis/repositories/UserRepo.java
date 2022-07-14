package blogappapis.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import blogappapis.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {

	
	Optional<User> findByEmail(String email);
}
