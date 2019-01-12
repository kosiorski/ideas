package pl.kosiorski.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(
    name = "ratings",
    uniqueConstraints = @UniqueConstraint(columnNames = {"idea_id", "user_id"}))
public class Rating {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rating_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "idea_id")
  private Idea idea;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  private int value;
}
