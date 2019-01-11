package pl.kosiorski.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, ID extends Serializable> {

  T findById(ID id);

  T findByName(String name);

  List<T> findAll();

  T save(T entity);

  T update(T entity);

  void delete(T entity);
}
