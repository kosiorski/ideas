package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosiorski.model.User;
import pl.kosiorski.service.*;

@Controller
@RequestMapping(value = {"", "/homepage"})
public class HomepageController {

  private final UserService userService;
  private final IdeaService ideaService;
  private final CategoryService categoryService;
  private final LevelService levelService;
  private final ToolService toolService;

  @Autowired
  public HomepageController(
          UserService userService,
          IdeaService ideaService, CategoryService categoryService,
          LevelService levelService,
          ToolService toolService) {
    this.userService = userService;
    this.ideaService = ideaService;
    this.categoryService = categoryService;
    this.levelService = levelService;
    this.toolService = toolService;
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


    // TODO INACTIVE to admin, active to homepage
        model.addAttribute("ideas", ideaService.findAllActive());

    try {
      User user = userService.findCurrentLoggedUser();
      model.addAttribute(
          "userName",
          "Welcome " + user.getLogin() + " (" + user.getEmail() + ") Id:" + user.getId());
    } catch (Exception e) {

    }

    return "/homepage";
  }
}
