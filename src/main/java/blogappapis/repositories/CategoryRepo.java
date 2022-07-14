package blogappapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import blogappapis.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
