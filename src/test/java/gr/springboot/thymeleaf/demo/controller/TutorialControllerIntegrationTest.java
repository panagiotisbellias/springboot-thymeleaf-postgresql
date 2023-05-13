package gr.springboot.thymeleaf.demo.controller;

import gr.springboot.thymeleaf.demo.entity.Tutorial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = TutorialController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TutorialControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testAllTutorials() {
        // TODO Fix integration test adding also sql files and refactoring controller
//        assertTrue(this.restTemplate.getForObject("http://localhost:" + port + "/tutorials", String.class)
//                .getBytes("tutorials").length == 9);
    }

    @Test
    void testAddTutorial() {
        // TODO Fix test
//        Tutorial tutorial = new Tutorial("Demo Title", "demo description", 1, false);
//        ResponseEntity<String> responseEntity = this.restTemplate
//                .postForEntity("http://localhost:" + port + "/tutorials/save", tutorial, String.class);
//        assertEquals(201, responseEntity.getStatusCodeValue());
    }

}
