package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosiorski.model.*;
import pl.kosiorski.service.ActivityService;
import pl.kosiorski.service.IdeaService;
import pl.kosiorski.service.RatingService;
import pl.kosiorski.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/idea/rating")
class RatingController {
  private final IdeaService ideaService;
  private final UserService userService;
  private final RatingService ratingService;
  private final ActivityService activityService;

  @Autowired
  RatingController(
      IdeaService ideaService,
      UserService userService,
      RatingService ratingService,
      ActivityService activityService) {
    this.ideaService = ideaService;
    this.userService = userService;
    this.ratingService = ratingService;
    this.activityService = activityService;
  }

  @PostMapping("/add/{id}")
  public String addRating(
      @Valid Rating rating, BindingResult result, @PathVariable Long id, Principal principal) {

    if (result.hasErrors()) {
      return "idea/" + id;
    }

    Idea currentIdea = ideaService.findById(id);
    User currentUser = userService.findCurrentLoggedUser();

    rating.setUser(currentUser);
    rating.setIdea(currentIdea);

    ratingService.save(rating);

    double newIdeaRating = ratingService.countRating(id);
    currentIdea.setRating(newIdeaRating);
    currentIdea.setActive(true);

    System.out.println(currentIdea.getRating());

    ideaService.saveWithoutActions(currentIdea);

//    return "redirect:/idea/" + id;
    return "redirect:/";
  }
}
