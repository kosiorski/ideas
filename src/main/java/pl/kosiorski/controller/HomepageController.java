package pl.kosiorski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {

  @GetMapping
  public String userHome(Model model) {
    return "/homepage";
  }
}
