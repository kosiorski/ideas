package pl.kosiorski.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kosiorski.model.Activity;
import pl.kosiorski.model.User;
import pl.kosiorski.service.ActivityService;
import pl.kosiorski.service.UserService;

@Controller
public class LoginController {

  private final UserService userService;
  private final ActivityService activityService;

  @Autowired
  public LoginController(UserService userService, ActivityService activityService) {
    this.userService = userService;
    this.activityService = activityService;
  }

  @GetMapping(value = {"/login"})
  public String login() {
    return "/login";
  }

  @GetMapping(value = "/registration")
  public String registration(Model model) {
    User user = new User();
    model.addAttribute("user", user);
    model.addAttribute("registration");
    return "/registration";
  }

  @PostMapping(value = "/registration")
  public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
    User userExists = userService.findUserByLogin(user.getLogin());
    if (userExists != null) {
      bindingResult.rejectValue(
          "login", "error.user", "There is already a user registered with this login");
    }
    if (bindingResult.hasErrors()) {
      model.addAttribute("registration");
    } else {
      userService.saveUser(user);
      model.addAttribute("successMessage", "User has been registered successfully");
      model.addAttribute("user", new User());
      model.addAttribute("registration");

      Activity activity = new Activity();
      activity.setContent("A new user with login " + user.getLogin() + " has registered");
      activityService.save(activity);
    }
    return "/registration";
  }
}
