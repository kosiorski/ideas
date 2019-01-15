package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import pl.kosiorski.model.BoredActivity;
import pl.kosiorski.model.User;
import pl.kosiorski.service.*;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = {"", "/homepage"})
public class HomepageController {

  private final UserService userService;
  private final IdeaService ideaService;
  private final CategoryService categoryService;
  private final LevelService levelService;
  private final ToolService toolService;
  private final ActivityService activityService;

  @Autowired
  public HomepageController(
      UserService userService,
      IdeaService ideaService,
      CategoryService categoryService,
      LevelService levelService,
      ToolService toolService,
      ActivityService activityService) {
    this.userService = userService;
    this.ideaService = ideaService;
    this.categoryService = categoryService;
    this.levelService = levelService;
    this.toolService = toolService;
    this.activityService = activityService;
  }

  private static BoredActivity getBoredActivity() {
    final String uri = "http://www.boredapi.com/api/activity/";

    RestTemplate restTemplate = new RestTemplate();
    String json = restTemplate.getForObject(uri, String.class);

    return new Gson().fromJson(json, BoredActivity.class);
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
  public String userHome(Model model) {

    model.addAttribute("ideas", ideaService.findAllActive());
    model.addAttribute("activities", activityService.lastTen());
    model.addAttribute("boredActivity", getBoredActivity());

    try {
      User user = userService.findCurrentLoggedUser();
      model.addAttribute(
          "userName",
          "Welcome " + user.getLogin() + " (" + user.getEmail() + ") Id:" + user.getId());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return "/homepage";
  }
}
