package gr.springboot.thymeleaf.demo.controller;

import gr.springboot.thymeleaf.demo.entity.Tutorial;
import gr.springboot.thymeleaf.demo.entity.TutorialDTO;
import gr.springboot.thymeleaf.demo.repository.TutorialRepository;
import gr.springboot.thymeleaf.demo.util.TestsUtil;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TutorialControllerTest {

    @InjectMocks
    private TutorialController tutorialController;

    @Mock
    private TutorialRepository tutorialRepository;

    Model model = mock(Model.class);
    RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

    @BeforeEach
    void setUp() {
        when(tutorialRepository.findAll()).thenReturn(TestsUtil.addDemoTutorialInRepository(1));
    }

    @Test
    void getAllTest() {
        String tutorials = tutorialController.getAll(model, null);
        verify(tutorialRepository, times(1)).findAll();
        verify(tutorialRepository, times(0)).findByTitleContainingIgnoreCase(anyString());
        assertEquals("tutorials", tutorials);
    }

//    @Ignore("Exception org.mockito.exceptions.misusing.UnfinishedStubbingException is thrown")
    @Test
    void getAllByKeywordTest() {
        String tutorials = tutorialController.getAll(model, "DemoTutorial1");
        verify(tutorialRepository, times(0)).findAll();
        verify(tutorialRepository, times(1)).findByTitleContainingIgnoreCase(anyString());
        assertEquals("tutorials", tutorials);
    }

//    @Test
//    void getAllExceptionTest() {
//        when(tutorialRepository.findAll()).thenThrow(Exception.class);
//        Assertions.assertThrows(Exception.class, () -> {
//            tutorialController.getAll(model, null);
//        });
//    }

    @Test
    void addTutorialTest() {
        String tutorial_form = tutorialController.addTutorial(model);
        assertEquals("tutorial_form", tutorial_form);
    }

    @Test
    void saveTutorialTest() {
        TutorialDTO tutorialDTO = TestsUtil.mockedTutorialDTO();
        String tutorial_form = tutorialController.saveTutorial(tutorialDTO, redirectAttributes);
        verify(tutorialRepository, times(1)).save(any(Tutorial.class));
        assertEquals("redirect:/tutorials", tutorial_form);
    }

//    @Test
//    void saveTutorialExceptionTest() {
//        TutorialDTO tutorialDTO = TestsUtil.mockedTutorialDTO();
//        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
//        when(tutorialRepository.save(TestsUtil.mockedTutorialEntity(tutorialDTO.getTitle()))).thenThrow(Exception.class);
//        Assertions.assertThrows(Exception.class, () -> {
//            tutorialController.saveTutorial(tutorialDTO, redirectAttributes);
//        });
//    }

    @Test
    void editTutorialNotPresentTest() {
        String tutorial_form = tutorialController.editTutorial(2, model, redirectAttributes);
        verify(tutorialRepository, times(0)).findById(1);
        verify(tutorialRepository, times(1)).findById(2);
        assertEquals("tutorial_form", tutorial_form);
    }

    @Test
    void editTutorialPresentTest() {
        String tutorial_form = tutorialController.editTutorial(1, model, redirectAttributes);
        verify(tutorialRepository, times(1)).findById(1);
        verify(tutorialRepository, times(0)).findById(2);
        assertEquals("tutorial_form", tutorial_form);
    }

//    @Test
//    void editTutorialExceptionTest() {
//        when(model.addAttribute(eq("tutorial"), any(Tutorial.class))).thenThrow(Exception.class);
//        Assertions.assertThrows(Exception.class, () -> {
//            tutorialController.editTutorial(1, model, redirectAttributes);
//        });
//    }

    @Test
    void deleteTutorialTest() {
        String tutorial_form = tutorialController.deleteTutorial(1, model, redirectAttributes);
        verify(tutorialRepository, times(1)).deleteById(1);
        assertEquals("redirect:/tutorials", tutorial_form);
    }

//    @Test
//    void deleteTutorialExceptionTest() {
//        doThrow(Exception.class).when(tutorialRepository).deleteById(2);
//        Assertions.assertThrows(Exception.class, () -> {
//            tutorialController.deleteTutorial(2, model, redirectAttributes);
//        });
//    }

    @Test
    void updateTutorialPublishedStatusPublishedTest() {
        String tutorial_form = tutorialController.updateTutorialPublishedStatus(1, true, model, redirectAttributes);
        verify(tutorialRepository, times(1)).updatePublishedStatus(1, true);
        assertEquals("redirect:/tutorials", tutorial_form);
    }

    @Test
    void updateTutorialPublishedStatusDisabledTest() {
        String tutorial_form = tutorialController.updateTutorialPublishedStatus(1, false, model, redirectAttributes);
        verify(tutorialRepository, times(1)).updatePublishedStatus(1, false);
        assertEquals("redirect:/tutorials", tutorial_form);
    }

//    @Test
//    void updateTutorialPublishedStatusExceptionTest() {
//        doThrow(Exception.class).when(tutorialRepository).updatePublishedStatus(1, true);
//        Assertions.assertThrows(Exception.class, () -> {
//            tutorialController.updateTutorialPublishedStatus(1, true, model, redirectAttributes);
//        });
//    }

}
