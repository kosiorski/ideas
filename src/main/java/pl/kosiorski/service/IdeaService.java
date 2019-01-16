package pl.kosiorski.service;

import pl.kosiorski.model.Idea;

import java.util.List;
import java.util.Optional;

public interface IdeaService {

  Idea findById(Long id);

  Idea findByName(String name);

  List<Idea> findAllActive();

  List<Idea> findAllInactive();

  Idea save(Idea idea);

  Idea saveWithoutActions(Idea idea);

  Idea update(Idea idea);

  void delete(Idea idea);

  void changeActive(Long id);
}
