package blogappapis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blogappapis.entities.Category;
import blogappapis.entities.Post;
import blogappapis.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
	
}
