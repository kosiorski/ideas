package pl.kosiorski.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kosiorski.model.Activity;
import pl.kosiorski.model.Comment;
import pl.kosiorski.model.Idea;
import pl.kosiorski.service.ActivityService;
import pl.kosiorski.service.CommentService;

import javax.validation.Valid;

@Controller
@RequestMapping("/idea/comment")
public class CommentController {

  private final CommentService commentService;
  private final ActivityService activityService;

  @Autowired
  public CommentController(CommentService commentService, ActivityService activityService) {
    this.commentService = commentService;
    this.activityService = activityService;
  }

  @PostMapping("/add")
  public String addComment(@Valid Comment comment, BindingResult result) {

    Idea idea = comment.getIdea();

    if (result.hasErrors()) {
      return "idea/" + idea.getId();
    }

    commentService.save(comment);

    Activity activity = new Activity();
    activity.setContent(
        "User "
            + comment.getUser().getLogin()
            + " has added a comment to idea with id "
            + comment.getIdea().getId());
    activityService.save(activity);

    return "redirect:/idea/" + idea.getId();
  }

}
