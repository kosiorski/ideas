package pl.kosiorski.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
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
