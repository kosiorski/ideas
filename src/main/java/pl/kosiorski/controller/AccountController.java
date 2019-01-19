package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosiorski.model.User;
import pl.kosiorski.service.IdeaService;
import pl.kosiorski.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
  private final UserService userService;
  private final IdeaService ideaService;

  @Autowired
  public AccountController(UserService userService, IdeaService ideaService) {
    this.userService = userService;
    this.ideaService = ideaService;
  }

  @GetMapping
  public String showAccount(Model model) {
    User currentLoggedUser = userService.findCurrentLoggedUser();

    model.addAttribute("ideas", ideaService.findAllByUserId(currentLoggedUser.getId()));

    return "/user/account";
  }
}
