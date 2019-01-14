package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosiorski.model.Comment;
import pl.kosiorski.service.CommentService;
import pl.kosiorski.service.IdeaService;
import pl.kosiorski.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/idea/comment")
public class CommentController {

  private final CommentService commentService;
  private final UserService userService;
  private final IdeaService ideaService;

  @Autowired
  public CommentController(
      CommentService commentService, UserService userService, IdeaService ideaService) {
    this.commentService = commentService;
    this.userService = userService;
    this.ideaService = ideaService;
  }

  @PostMapping("/add/{id}")
  public String addComment(@Valid Comment form, BindingResult result, @PathVariable Long id) {

    if (result.hasErrors()) {
      return "idea/" + id;
    }
    Comment comment = new Comment();
    comment.setContent(form.getContent());

    comment.setIdea(ideaService.findById(id));
    comment.setUser(userService.findCurrentLoggedUser());

    System.out.println(comment.getId());

    commentService.save(comment);

    return "redirect:/idea/" + id;
  }
}
