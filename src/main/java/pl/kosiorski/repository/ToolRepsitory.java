package pl.kosiorski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kosiorski.model.Tool;

@Repository
public interface ToolRepsitory extends JpaRepository<Tool, Long> {}
