package pl.kosiorski.service;

import pl.kosiorski.model.Comment;
import pl.kosiorski.model.User;

import java.util.List;

public interface CommentService {

  List<Comment> findAllByIdeaId(Long id);

  void save(Comment comment);

}
