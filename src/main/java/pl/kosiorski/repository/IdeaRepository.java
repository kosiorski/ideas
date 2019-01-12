package pl.kosiorski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kosiorski.model.Idea;

import java.util.List;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {
    Idea findByName(String name);

    List<Idea> findAllByActiveContains(int value);
}
