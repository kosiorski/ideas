package pl.kosiorski.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private Long id;

  @NotBlank private String name;

  private String description;

  @OneToMany(mappedBy = "category")
  private List<Idea> ideas;
}
