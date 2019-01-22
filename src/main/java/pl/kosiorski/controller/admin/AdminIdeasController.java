package pl.kosiorski.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosiorski.model.User;
import pl.kosiorski.service.IdeaService;
import pl.kosiorski.service.UserService;

@Controller
@RequestMapping("/admin/idea")
public class AdminIdeasController {
  private final IdeaService ideaService;

  @Autowired
  public AdminIdeasController(IdeaService ideaService) {
    this.ideaService = ideaService;
  }

  @GetMapping(value = "/active")
  public String activeIdeas(Model model) {
    model.addAttribute("active", ideaService.findAllActive());

    return "/admin/idea/active";
  }

  @GetMapping(value = "/inactive")
  public String inactiveIdeas(Model model) {
    model.addAttribute("inactive", ideaService.findAllInactive());

    return "/admin/idea/inactive";
  }

  @GetMapping(path = "/active/change-status/{id}")
  public String changeActive(@PathVariable Long id) {
    ideaService.changeActive(id);

    return "redirect:/admin/idea/active";
  }

  @GetMapping(path = "/inactive/change-status/{id}")
  public String changeInctive(@PathVariable Long id) {
    ideaService.changeActive(id);

    return "redirect:/admin/idea/inactive";
  }
}
