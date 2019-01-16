package pl.kosiorski.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "ideas")
public class Idea {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idea_id")
  private Long id;

  @NotBlank private String name;

  @NotBlank private String description;

  @CreationTimestamp private LocalDateTime created;

  @UpdateTimestamp private LocalDateTime updated;

  private double rating;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "idea")
  private List<Comment> comments;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToMany
  @JoinTable(
      name = "idea_tool",
      joinColumns = @JoinColumn(name = "idea_id"),
      inverseJoinColumns = @JoinColumn(name = "tool_id"))
  private List<Tool> tools = new ArrayList<>();

  @OneToMany(mappedBy = "idea")
  private List<Rating> ratings;

  @ManyToOne
  @JoinColumn(name = "level_id")
  private Level level;

  private boolean active;

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public double getRating() {
    return round(rating, 2);
  }

  private static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}
