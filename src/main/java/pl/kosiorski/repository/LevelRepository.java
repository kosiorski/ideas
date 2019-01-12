package pl.kosiorski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kosiorski.model.Level;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {}
