package pl.kosiorski.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tools")
public class Tool {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "tool_id")
  private Long id;

  @NotBlank
  private String name;

  @ManyToMany(mappedBy = "tools")
  private List<Idea> ideas = new ArrayList<>();
}
