package pl.kosiorski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kosiorski.model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findAllByActiveTrueAndIdeaIdOrderByCreatedAsc(Long id);

  List<Comment> findAllByActiveFalseAndIdeaIdOrderByCreatedAsc(Long id);


}
