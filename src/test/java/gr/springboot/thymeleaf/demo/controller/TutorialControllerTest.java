package gr.springboot.thymeleaf.demo.controller;

import gr.springboot.thymeleaf.demo.entity.TutorialDTO;
import gr.springboot.thymeleaf.demo.repository.TutorialRepository;
import gr.springboot.thymeleaf.demo.util.TestsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TutorialControllerTest {

    @InjectMocks
    private TutorialController tutorialController;

    @Mock
    private TutorialRepository tutorialRepository;

    Model model = mock(Model.class);

    @BeforeEach
    void setUp() {
        when(tutorialRepository.findAll()).thenReturn(TestsUtil.addDemoTutorialInRepository(1));
    }

    @Test
    void getAllTest() {
        String tutorials = tutorialController.getAll(model, null);
        // TODO Add verifications
        assertEquals("tutorials", tutorials);
    }

    @Test
    void getAllByKeywordFoundTest() {
        String tutorials = tutorialController.getAll(model, "DemoTutorial1");
        // TODO Add verifications
        assertEquals("tutorials", tutorials);
    }

    @Test
    void getAllByKeywordNotFoundTest() {
        String tutorials = tutorialController.getAll(model, "DemoTutorial2");
        // TODO Add verifications
        assertEquals("tutorials", tutorials);
    }

    @Test
    void getAllExceptionTest() {
        when(tutorialRepository.findAll()).thenThrow(Exception.class);
        // TODO Assert exception
        tutorialController.getAll(model, null);
    }

    @Test
    void addTutorialTest() {
        String tutorial_form = tutorialController.addTutorial(model);
        // TODO Add verifications
        assertEquals("tutorial_form", tutorial_form);
    }

    @Test
    void saveTutorialTest() {
        TutorialDTO tutorialDTO = TestsUtil.mockedTutorialDTO();
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
        String tutorial_form = tutorialController.saveTutorial(tutorialDTO, redirectAttributes);
        // TODO Add verifications
        assertEquals("redirect:/tutorials", tutorial_form);
    }

    @Test
    void saveTutorialExceptionTest() {
        TutorialDTO tutorialDTO = TestsUtil.mockedTutorialDTO();
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
        when(tutorialRepository.save(TestsUtil.mockedTutorialEntity())).thenThrow(Exception.class);
        // TODO Assert exception
        tutorialController.saveTutorial(tutorialDTO, redirectAttributes);
    }

    @Test
    void editTutorialNotPresentTest() {

    }

    @Test
    void editTutorialPresentTest() {

    }

    @Test
    void editTutorialExceptionTest() {

    }

    @Test
    void deleteTutorialTest() {

    }

    @Test
    void deleteTutorialExceptionTest() {

    }

    @Test
    void updateTutorialPublishedStatusPublishedTest() {

    }

    @Test
    void updateTutorialPublishedStatusDisabledTest() {

    }

    @Test
    void updateTutorialPublishedStatusExceptionTest() {

    }

}
