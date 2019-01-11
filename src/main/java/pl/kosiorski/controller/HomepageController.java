package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosiorski.model.User;
import pl.kosiorski.service.CategoryService;
import pl.kosiorski.service.UserService;

@Controller
@RequestMapping(value = {"", "/homepage"})
public class HomepageController {

  private final UserService userService;
  private final CategoryService categoryService;

  @Autowired
  public HomepageController(UserService userService, CategoryService categoryService) {
    this.userService = userService;
    this.categoryService = categoryService;
  }

  @GetMapping
  public String userHome(Model model) {

    model.addAttribute("categories", categoryService.findAll());

    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      User user = userService.findUserByLogin(auth.getName());
      model.addAttribute(
          "userName",
          "Welcome " + user.getLogin() + " (" + user.getEmail() + ") Id:" + user.getId());
    } catch (Exception e) {

    }

    return "/homepage";
  }
}
