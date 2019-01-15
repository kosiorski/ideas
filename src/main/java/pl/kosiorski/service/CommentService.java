package pl.kosiorski.service;

import pl.kosiorski.model.Comment;
import pl.kosiorski.model.Idea;

import java.util.List;

public interface CommentService {

  //  List<Comment> findAllByIdeaId(Long id);

  void save(Comment comment);

  public List<Comment> findAllActiveByIdeaId(Long id);

  public List<Comment> findAllInactiveByIdeaId(Long id);
}
