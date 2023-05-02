package gr.springboot.thymeleaf.controller;

import gr.springboot.thymeleaf.entity.Tutorial;
import gr.springboot.thymeleaf.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TutorialController {

    @Autowired
    private TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public String getAll(Model model, @Param("keyword") String keyword) {
        try {
            List<Tutorial> tutorials = new ArrayList<Tutorial>();
            tutorialRepository.findAll().forEach(tutorials::add);

            model.addAttribute("tutorials", tutorials);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "tutorials";
    }

    @GetMapping("/tutorials/new")
    public String addTutorial(Model model) {
//  TODO  ...

        return "tutorial_form";
    }

    @PostMapping("/tutorials/save")
    public String saveTutorial(Tutorial tutorial, RedirectAttributes redirectAttributes) {
//  TODO  ...

        return "redirect:/tutorials";
    }

    @GetMapping("/tutorials/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
//  TODO  ...

        return "tutorial_form";
    }

    @GetMapping("/tutorials/delete/{id}")
    public String deleteTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
//  TODO  ...

        return "redirect:/tutorials";
    }

    @GetMapping("/tutorials/{id}/published/{status}")
    public String updateTutorialPublishedStatus(@PathVariable("id") Integer id, @PathVariable("status") boolean published,
                                                Model model, RedirectAttributes redirectAttributes) {
//  TODO  ...

        return "redirect:/tutorials";
    }
}