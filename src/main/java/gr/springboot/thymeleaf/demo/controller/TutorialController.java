package gr.springboot.thymeleaf.demo.controller;

import gr.springboot.thymeleaf.demo.entity.Tutorial;
import gr.springboot.thymeleaf.demo.entity.TutorialDTO;
import gr.springboot.thymeleaf.demo.repository.TutorialRepository;
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

    private static final String MESSAGE = "message";
    private static final String REDIRECT_HOME_PAGE = "redirect:/tutorials";

    @Autowired
    private TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public String getAll(Model model, @Param("keyword") String keyword) {
        try {
            List<Tutorial> tutorials = new ArrayList<>();

            if (keyword == null) {
                tutorialRepository.findAll().forEach(tutorials::add);
            } else {
                tutorialRepository.findByTitleContainingIgnoreCase(keyword).forEach(tutorials::add);
                model.addAttribute("keyword", keyword);
            }

            model.addAttribute("tutorials", tutorials);
        } catch (Exception e) {
            model.addAttribute(MESSAGE, e.getMessage());
        }

        return "tutorials";
    }

    @GetMapping("/tutorials/new")
    public String addTutorial(Model model) {
        Tutorial tutorial = new Tutorial();
        tutorial.setPublished(true);

        model.addAttribute("tutorial", tutorial);
        model.addAttribute("pageTitle", "Create new Tutorial");

        return "tutorial_form";
    }

    @PostMapping("/tutorials/save")
    public String saveTutorial(TutorialDTO tutorialDTO, RedirectAttributes redirectAttributes) {
        try {
            Tutorial tutorial = new Tutorial();
            tutorial.setId(tutorialDTO.getId());
            tutorial.setLevel(tutorialDTO.getLevel());
            tutorial.setTitle(tutorialDTO.getTitle());
            tutorial.setPublished(tutorialDTO.isPublished());
            tutorial.setDescription(tutorialDTO.getDescription());
            tutorialRepository.save(tutorial);

            redirectAttributes.addFlashAttribute(MESSAGE, "The Tutorial has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute(MESSAGE, e.getMessage());
        }

        return REDIRECT_HOME_PAGE;
    }

    @GetMapping("/tutorials/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            tutorialRepository.findById(id).ifPresent(tutorial -> {
                model.addAttribute("tutorial", tutorial);
                model.addAttribute("pageTitle", "Edit Tutorial (ID: " + id + ")");
            });
            return "tutorial_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(MESSAGE, e.getMessage());

            return REDIRECT_HOME_PAGE;
        }
    }

    @GetMapping("/tutorials/delete/{id}")
    public String deleteTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            tutorialRepository.deleteById(id);

            redirectAttributes.addFlashAttribute(MESSAGE, "The Tutorial with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(MESSAGE, e.getMessage());
        }

        return REDIRECT_HOME_PAGE;
    }

    @GetMapping("/tutorials/{id}/published/{status}")
    public String updateTutorialPublishedStatus(@PathVariable("id") Integer id, @PathVariable("status") boolean published,
                                                Model model, RedirectAttributes redirectAttributes) {
        try {
            tutorialRepository.updatePublishedStatus(id, published);

            String status = published ? "published" : "disabled";
            String message = "The Tutorial id=" + id + " has been " + status;

            redirectAttributes.addFlashAttribute(MESSAGE, message);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(MESSAGE, e.getMessage());
        }

        return REDIRECT_HOME_PAGE;
    }
}