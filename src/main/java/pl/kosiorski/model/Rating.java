package pl.kosiorski.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ratings")
public class Rating {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rating_id")
  private Long id;

  @OneToOne(mappedBy = "rating")
  private Idea idea;

  private double value;
}
