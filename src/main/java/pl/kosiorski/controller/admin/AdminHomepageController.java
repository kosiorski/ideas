package pl.kosiorski.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosiorski.model.User;
import pl.kosiorski.service.IdeaService;
import pl.kosiorski.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminHomepageController {
  private final UserService userService;

  @Autowired
  public AdminHomepageController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/home")
  public String adminHome(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findUserByLogin(auth.getName());
    model.addAttribute("userName", "Welcome " + user.getLogin() + " (" + user.getEmail() + ")");
    model.addAttribute("adminMessage", "Content Available Only for Users with Admin Role");
    model.addAttribute("admin/home");

    return "/admin/home";
  }
}
