package pl.kosiorski.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id")
  private Long id;

  @NotBlank(message = "your comment is empty")
  private String content;

  @CreationTimestamp
  private LocalDateTime created;

  @ManyToOne
  @JoinColumn(name = "idea_id")
  private Idea idea;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  private boolean active;

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }
}
