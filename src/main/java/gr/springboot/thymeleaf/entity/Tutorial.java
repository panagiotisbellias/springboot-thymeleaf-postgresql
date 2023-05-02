package gr.springboot.thymeleaf.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tutorials")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(length = 128, nullable = false)
    private String title;

    @Column(length = 256)
    private String description;

    @Column(nullable = false)
    private int level;

    @Column
    private boolean published;

    public Tutorial(String title, String description, int level, boolean published) {
        this.title = title;
        this.description = description;
        this.level = level;
        this.published = published;
    }
}