package pl.kosiorski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosiorski.model.Rating;

import java.util.LinkedList;


public interface RatingRepository extends JpaRepository<Rating, Long> {

    LinkedList<Rating> findAll();

}
