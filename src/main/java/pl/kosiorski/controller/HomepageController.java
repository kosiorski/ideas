package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import pl.kosiorski.model.BoredActivity;
import pl.kosiorski.model.Idea;
import pl.kosiorski.model.User;
import pl.kosiorski.service.*;
import com.google.gson.Gson;

import java.util.List;

@Controller
@RequestMapping(value = {"", "/homepage"})
public class HomepageController {

  private final UserService userService;
  private final IdeaService ideaService;
  private final ActivityService activityService;

  @Autowired
  public HomepageController(
      UserService userService, IdeaService ideaService, ActivityService activityService) {
    this.userService = userService;
    this.ideaService = ideaService;
    this.activityService = activityService;
  }

  @ModelAttribute("currentUser")
  public User currentUser() {
    try {
      return userService.findCurrentLoggedUser();
    } catch (Exception e) {
      return null;
    }
  }

  @GetMapping
  public String userHome(Model model, @RequestParam(required = false) String sortBy) {

    List<Idea> ideas = null;

    if (sortBy != null) {
      switch (sortBy) {
        case "id":
          ideas = ideaService.findAllActive();
          break;

        case "rating":
          ideas = ideaService.findAllOrderByRating();
          break;

        case "name":
          ideas = ideaService.findAllOrderByName();
          break;

        case "easy":
          ideas = ideaService.findAllByLevelName(sortBy);

        case "medium":
          ideas = ideaService.findAllByLevelName(sortBy);
          break;

        case "hard":
          ideas = ideaService.findAllByLevelName(sortBy);
      }
    } else {
      ideas = ideaService.findAllActive();
    }

    model.addAttribute("ideas", ideas);
    model.addAttribute("activities", activityService.lastTen());
    model.addAttribute("boredActivity", getBoredActivity());

    try {
      User user = userService.findCurrentLoggedUser();
      model.addAttribute("userName", "logged as: " + user.getLogin());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return "/homepage";
  }

  private static BoredActivity getBoredActivity() {
    final String uri = "http://www.boredapi.com/api/activity/";

    RestTemplate restTemplate = new RestTemplate();
    String json = restTemplate.getForObject(uri, String.class);

    return new Gson().fromJson(json, BoredActivity.class);
  }
}
