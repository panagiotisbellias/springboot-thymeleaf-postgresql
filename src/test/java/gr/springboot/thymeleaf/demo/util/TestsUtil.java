package gr.springboot.thymeleaf.demo.util;

import gr.springboot.thymeleaf.demo.entity.Tutorial;
import gr.springboot.thymeleaf.demo.entity.TutorialDTO;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestsUtil {

    public static List<Tutorial> addDemoTutorialInRepository(int newTutorials) {
        ArrayList<Tutorial> tutorials = new ArrayList<>();
        for (int i=0; i<newTutorials; i++) {
            Tutorial tutorial = mockedTutorialEntity("DemoTutorial"+(i+1));
            tutorials.add(tutorial);
        }
        return tutorials;
    }

    public static TutorialDTO mockedTutorialDTO() {
        TutorialDTO tutorialDTO = mock(TutorialDTO.class);
        when(tutorialDTO.getId()).thenReturn(1);
        when(tutorialDTO.getTitle()).thenReturn("DemoTutorial1");
        when(tutorialDTO.getDescription()).thenReturn("DemoDescription");
        when(tutorialDTO.getLevel()).thenReturn(1);
        when(tutorialDTO.isPublished()).thenReturn(true);
        return tutorialDTO;
    }

    public static Tutorial mockedTutorialEntity(String title) {
        Tutorial tutorial = mock(Tutorial.class);
        // TODO Fix expectations that don't work
//        Integer integer = mock(Integer.class);
//        when(integer).thenReturn(1);
//        when(tutorial.getId()).thenReturn(integer);
//        when(tutorial.getId()).thenReturn(1);
//        when(tutorial.getTitle()).thenReturn(title);
//        when(tutorial.getDescription()).thenReturn("DemoDescription");
//        when(tutorial.getLevel()).thenReturn(1);
//        when(tutorial.isPublished()).thenReturn(true);
        return tutorial;
    }

}
