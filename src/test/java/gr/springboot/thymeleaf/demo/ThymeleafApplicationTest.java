package gr.springboot.thymeleaf.demo;

import gr.springboot.thymeleaf.demo.controller.TutorialController;
import gr.springboot.thymeleaf.demo.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ThymeleafApplicationTest {

	@Autowired
	private TutorialController tutorialController;

	@Autowired
	private TutorialRepository tutorialRepository;

	@Autowired
	private ThymeleafApplication thymeleafApplication;

	@Test
	void contextLoads() {
		assertThat(tutorialController).isNotNull();
		assertThat(tutorialRepository).isNotNull();
		assertThat(thymeleafApplication).isNotNull();
		ThymeleafApplication.main(new String[]{});
	}

}
