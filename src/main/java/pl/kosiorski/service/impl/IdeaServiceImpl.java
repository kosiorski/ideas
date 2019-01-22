package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.model.Idea;
import pl.kosiorski.repository.IdeaRepository;
import pl.kosiorski.service.IdeaService;
import pl.kosiorski.service.UserService;

import java.util.List;

@Service
public class IdeaServiceImpl implements IdeaService {

  private final IdeaRepository ideaRepository;

  @Autowired
  public IdeaServiceImpl(IdeaRepository ideaRepository) {
    this.ideaRepository = ideaRepository;
  }

  @Override
  public Idea findById(Long id) {
    return ideaRepository.findById(id).get();
  }

  @Override
  public List<Idea> findAllActive() {
    return ideaRepository.findAllByActiveTrue();
  }

  @Override
  public List<Idea> findAllInactive() {
    return ideaRepository.findAllByActiveFalse();
  }

  @Override
  public List<Idea> findAllOrderByName() {
    return ideaRepository.findAllByActiveTrueOrderByNameAsc();
  }

  @Override
  public List<Idea> findAllOrderByRating() {
    return ideaRepository.findAllByActiveTrueOrderByRatingDesc();
  }

  @Override
  public List<Idea> findAllByLevelName(String levelName) {

    List<Idea> filteredIdeas = null;
    switch (levelName) {
      case "easy":
        filteredIdeas = ideaRepository.findAllByActiveTrueAndLevelName("EASY");
        break;

      case "medium":
        filteredIdeas = ideaRepository.findAllByActiveTrueAndLevelName("MEDIUM");
        break;

      case "hard":
        filteredIdeas = ideaRepository.findAllByActiveTrueAndLevelName("HARD");
        break;

      default:
        filteredIdeas = ideaRepository.findAll();
        break;
    }
    return filteredIdeas;
  }

  @Override
  public List<Idea> findAllByUserId(Long id) {
    return ideaRepository.findAllByUserId(id);
  }

  @Override
  public Idea save(Idea idea) {
    idea.setActive(false);
    idea.setRating(0.0);
    return ideaRepository.save(idea);
  }

  @Override
  public Idea saveWithoutActions(Idea idea) {
    return ideaRepository.save(idea);
  }

  @Override
  public void delete(Idea idea) {
    ideaRepository.delete(idea);
  }

  @Override
  public void changeActive(Long id) {

    Idea idea = ideaRepository.findById(id).get();
    idea.setActive(!idea.getActive());
    ideaRepository.save(idea);
  }
}
