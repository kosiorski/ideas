package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosiorski.model.*;
import pl.kosiorski.service.ActivityService;
import pl.kosiorski.service.IdeaService;
import pl.kosiorski.service.RatingService;

import javax.validation.Valid;

@Controller
@RequestMapping("/idea/rating")
class RatingController {
  private final IdeaService ideaService;
  private final RatingService ratingService;
  private final ActivityService activityService;

  @Autowired
  RatingController(
      IdeaService ideaService, RatingService ratingService, ActivityService activityService) {
    this.ideaService = ideaService;
    this.ratingService = ratingService;
    this.activityService = activityService;
  }

  @PostMapping("/add")
  public String addRating(@Valid Rating rating, BindingResult result) {

    Idea idea = rating.getIdea();

    if (result.hasErrors()) {
      return "idea/" + idea.getId();
    }

    try {
      ratingService.save(rating);
    }
    // when a user tries to vote a second time for the same idea
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    double newIdeaRating = ratingService.countRating(idea);
    idea.setRating(newIdeaRating);
    ideaService.saveWithoutActions(idea);

    Activity activity = new Activity();
    activity.setContent(
        "User "
            + rating.getUser().getLogin()
            + " has rated the idea with id "
            + rating.getIdea().getId()
            + " a "
            + rating.getValue());
    activityService.save(activity);

    return "redirect:/idea/" + idea.getId();
  }
}
