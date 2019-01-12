package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.model.Level;
import pl.kosiorski.repository.LevelRepository;
import pl.kosiorski.service.LevelService;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {

  private final LevelRepository levelRepository;

  @Autowired
  public LevelServiceImpl(LevelRepository levelRepository) {
    this.levelRepository = levelRepository;
  }

  @Override
  public List<Level> findAll() {
    return levelRepository.findAll();
  }
}
