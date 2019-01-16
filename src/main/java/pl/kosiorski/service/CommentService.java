package pl.kosiorski.service;

import pl.kosiorski.model.Comment;

import java.util.List;

public interface CommentService {

  void save(Comment comment);

  public List<Comment> findAllActiveByIdeaId(Long id);

  public List<Comment> findAllInactiveByIdeaId(Long id);
}
