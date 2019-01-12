package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.model.Idea;
import pl.kosiorski.repository.IdeaRepository;
import pl.kosiorski.service.IdeaService;
import pl.kosiorski.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class IdeaServiceImpl implements IdeaService {

  private final IdeaRepository ideaRepository;
  private final UserService userService;

  @Autowired
  public IdeaServiceImpl(IdeaRepository ideaRepository, UserService userService) {
    this.ideaRepository = ideaRepository;
    this.userService = userService;
  }

  @Override
  public Idea findById(Long id) {
    return ideaRepository.findById(id).get();
  }

  @Override
  public Idea findByName(String name) {
    return ideaRepository.findByName(name);
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
  public Idea save(Idea idea) {
    idea.setActive(false);
    idea.setRating(0.0);
    return ideaRepository.save(idea);
  }

  @Override
  public Idea update(Idea idea) {
    idea.setActive(false);
    idea.setUser(userService.findCurrentLoggedUser());
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
