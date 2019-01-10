package pl.kosiorski.model;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;

@Entity
@Data
@Table(name = "repos")
public class RepoUrl {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "repo_id")
  private Long id;

  @URL private String url;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
