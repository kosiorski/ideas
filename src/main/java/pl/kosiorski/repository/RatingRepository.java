package pl.kosiorski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosiorski.model.Rating;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

  List<Rating> findAll();

  List<Rating> findAllByIdeaId(Long id);
}
