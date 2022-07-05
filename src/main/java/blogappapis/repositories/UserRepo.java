package blogappapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import blogappapis.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {

}
