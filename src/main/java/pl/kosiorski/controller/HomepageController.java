package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosiorski.model.User;
import pl.kosiorski.service.CategoryService;
import pl.kosiorski.service.IdeaService;
import pl.kosiorski.service.UserService;

@Controller
@RequestMapping(value = {"", "/homepage"})
public class HomepageController {

  private final UserService userService;
  private final CategoryService categoryService;
  private final IdeaService ideaService;

  @Autowired
  public HomepageController(UserService userService, CategoryService categoryService, IdeaService ideaService) {
    this.userService = userService;
    this.categoryService = categoryService;
    this.ideaService = ideaService;
  }

  @GetMapping
  public String userHome(Model model) {

    model.addAttribute("categories", categoryService.findAll());
    //TODO INACTIVE to admin, active to homepage
    model.addAttribute("ideas", ideaService.findAllInactive());

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
