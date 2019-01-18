package pl.kosiorski.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

  @NotBlank(message = "*Please provide idea name")
  private String name;

  @Length(
      min = 10,
      message =
          "*Please describe your idea. The description should be at least 10 characters long.")
  @Column(length = 2000)
  private String description;

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
  @NotNull(message = "*Please provide idea category")
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
  @NotNull(message = "*Please provide idea difficulty")
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
