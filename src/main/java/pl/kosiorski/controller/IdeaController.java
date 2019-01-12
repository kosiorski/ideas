package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kosiorski.model.Idea;
import pl.kosiorski.service.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/idea")
public class IdeaController {

  private final IdeaService ideaService;
  private final UserService userService;
  private final CategoryService categoryService;
  private final LevelService levelService;
  private final ToolService toolService;

  @Autowired
  public IdeaController(
      IdeaService ideaService,
      UserService userService,
      CategoryService categoryService,
      LevelService levelService,
      ToolService toolService) {
    this.ideaService = ideaService;
    this.userService = userService;
    this.categoryService = categoryService;
    this.levelService = levelService;
    this.toolService = toolService;
  }

  @ModelAttribute
  public void modelAttributes(Model model) {
    model.addAttribute("categories", categoryService.findAll());
    model.addAttribute("levels", levelService.findAll());
    model.addAttribute("tools", toolService.findAll());
  }

  @GetMapping("/add")
  public String ideaForm(Model model) {
    Idea idea = new Idea();
    model.addAttribute("idea", idea);
    return "forms/idea";
  }

  @PostMapping("/add")
  //  @Secured({ "ADMIN", "USER" })
  public String addIdea(@Valid Idea idea, BindingResult result) {

    if (result.hasErrors()) {
      return "forms/idea";
    }
    idea.setUser(userService.findCurrentLoggedUser());
    ideaService.save(idea);

    return "redirect:/" ;
  }
}