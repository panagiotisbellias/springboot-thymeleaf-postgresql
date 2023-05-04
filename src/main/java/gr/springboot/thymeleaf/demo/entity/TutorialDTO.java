package gr.springboot.thymeleaf.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TutorialDTO {

    private Integer id;
    private String title;
    private String description;
    private int level;
    private boolean published;

    public TutorialDTO(String title, String description, int level, boolean published) {
        this.title = title;
        this.description = description;
        this.level = level;
        this.published = published;
    }
}