package blogappapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import blogappapis.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
