package pl.kosiorski.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@Table(name = "levels")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "level_id")
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "level")
    private List<Idea> ideas;


}
