package pl.kosiorski.service;

import pl.kosiorski.model.Category;

import java.util.List;

public interface CategoryService {

  Category findById(Long id);

  Category findByName(String name);

  List<Category> findAll();

  Category save(Category category);

  Category update(Category category);

  void delete(Category category);
}
