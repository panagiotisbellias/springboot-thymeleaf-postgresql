package gr.springboot.thymeleaf.demo.util;

import gr.springboot.thymeleaf.demo.entity.Tutorial;
import gr.springboot.thymeleaf.demo.entity.TutorialDTO;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class TestsUtil {

    public static List<Tutorial> addDemoTutorialInRepository(int newTutorials) {
        ArrayList<Tutorial> tutorials = new ArrayList<>();
        // TODO Add x=newTutorials tutorials in list
        return tutorials;
    }

    public static TutorialDTO mockedTutorialDTO() {
        TutorialDTO tutorialDTO = mock(TutorialDTO.class);
        //TODO Add expectations
        return tutorialDTO;
    }

    public static Tutorial mockedTutorialEntity() {
        Tutorial tutorial = mock(Tutorial.class);
        //TODO Add expectations
        return tutorial;
    }

}
