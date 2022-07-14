package blogappapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import blogappapis.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

	
	
}
