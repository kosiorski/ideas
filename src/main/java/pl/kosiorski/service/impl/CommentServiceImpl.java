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
  public void save(Comment comment) {
    comment.setActive(true);
    commentRepository.save(comment);
  }

  @Override
  public List<Comment> findAllActiveByIdeaId(Long id) {
    return commentRepository.findAllByActiveTrueAndIdeaIdOrderByCreatedAsc(id);
  }

  @Override
  public List<Comment> findAllInactiveByIdeaId(Long id) {
    return commentRepository.findAllByActiveFalseAndIdeaIdOrderByCreatedAsc(id);
  }
}
