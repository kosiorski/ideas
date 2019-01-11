package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.model.Category;
import pl.kosiorski.repository.CategoryRepository;
import pl.kosiorski.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public Category findById(Long id) {
    return categoryRepository.findById(id).get();
  }

  @Override
  public Category findByName(String name) {
    return categoryRepository.findByName(name);
  }

  @Override
  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  @Override
  public Category save(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public Category update(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public void delete(Category category) {
    categoryRepository.delete(category);
  }
}
