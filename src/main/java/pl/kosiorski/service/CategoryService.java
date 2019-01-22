package pl.kosiorski.service;

import pl.kosiorski.model.Category;

import java.util.List;

public interface CategoryService {

  List<Category> findAll();

  Category save(Category category);
}
