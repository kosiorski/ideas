package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kosiorski.model.*;
import pl.kosiorski.service.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/idea")
public class IdeaController {

  private final IdeaService ideaService;
  private final UserService userService;
  private final CategoryService categoryService;
  private final LevelService levelService;
  private final ToolService toolService;
  private final RatingService ratingService;
  private final CommentService commentService;
  private final ActivityService activityService;

  @Autowired
  public IdeaController(
      IdeaService ideaService,
      UserService userService,
      CategoryService categoryService,
      LevelService levelService,
      ToolService toolService,
      RatingService ratingService,
      CommentService commentService,
      ActivityService activityService) {
    this.ideaService = ideaService;
    this.userService = userService;
    this.categoryService = categoryService;
    this.levelService = levelService;
    this.toolService = toolService;
    this.ratingService = ratingService;
    this.commentService = commentService;
    this.activityService = activityService;
  }

  @ModelAttribute
  public void modelAttributes(Model model) {
    model.addAttribute("categories", categoryService.findAll());
    model.addAttribute("levels", levelService.findAll());
    model.addAttribute("tools", toolService.findAll());
  }

  @GetMapping("/add")
  public String ideaForm(Model model) {
    Idea idea = new Idea();
    model.addAttribute("idea", idea);
    return "forms/idea";
  }

  @PostMapping("/add")
  //  @Secured({ "ADMIN", "USER" })
  public String addIdea(@Valid Idea idea, BindingResult result) {

    if (result.hasErrors()) {
      return "forms/idea";
    }
    User currentUser = userService.findCurrentLoggedUser();
    idea.setUser(currentUser);
    ideaService.save(idea);

    Activity activity = new Activity();
    activity.setContent("User " + currentUser.getLogin() + " has added a new idea");
    activityService.save(activity);

    return "redirect:/";
  }

  @GetMapping("/{id}")
  public String projectDetails(@PathVariable Long id, Model model) {

    Comment comment = new Comment();
    Rating rating = new Rating();



    model.addAttribute("comment", comment);
    model.addAttribute("rating", rating);
    model.addAttribute("idea", ideaService.findById(id));
    model.addAttribute("comments", commentService.findAllActiveByIdeaId(id));

    return "idea/details";
  }
}
