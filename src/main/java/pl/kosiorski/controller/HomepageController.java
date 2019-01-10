package pl.kosiorski.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosiorski.model.User;
import pl.kosiorski.service.UserService;

@Controller
@RequestMapping(value = {"", "/homepage"})
public class HomepageController {

  private final UserService userService;

  public HomepageController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String userHome(Model model) {
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
