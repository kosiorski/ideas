package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosiorski.model.Activity;
import pl.kosiorski.model.Comment;
import pl.kosiorski.model.Idea;
import pl.kosiorski.model.User;
import pl.kosiorski.service.ActivityService;
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
  private final ActivityService activityService;

  @Autowired
  public CommentController(
      CommentService commentService,
      UserService userService,
      IdeaService ideaService,
      ActivityService activityService) {
    this.commentService = commentService;
    this.userService = userService;
    this.ideaService = ideaService;
    this.activityService = activityService;
  }

  @PostMapping("/add/{id}")
  public String addComment(@Valid Comment form, BindingResult result, @PathVariable Long id) {

    if (result.hasErrors()) {
      return "idea/" + id;
    }
    Comment comment = new Comment();
    comment.setContent(form.getContent());

    Idea currentIdea = ideaService.findById(id);
    comment.setIdea(currentIdea);
    User currentUser = userService.findCurrentLoggedUser();
    comment.setUser(currentUser);
    commentService.save(comment);

    Activity activity = new Activity();
    activity.setContent(
        "User " + currentUser.getLogin()
            + " has added a comment to idea with id " + currentIdea.getId());
    activityService.save(activity);

    return "redirect:/idea/" + id;
  }
}
