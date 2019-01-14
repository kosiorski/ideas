package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.model.Comment;
import pl.kosiorski.repository.CommentRepository;
import pl.kosiorski.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
  private final CommentRepository commentRepository;

  @Autowired
  public CommentServiceImpl(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  @Override
  public List<Comment> findAllByIdeaId(Long id) {
    return commentRepository.findAllByIdeaIdOrderByCreatedDesc(id);
  }

  @Override
  public void save(Comment comment) {
    commentRepository.save(comment);
  }
}
