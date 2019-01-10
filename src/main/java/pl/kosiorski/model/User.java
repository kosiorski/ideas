package pl.kosiorski.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @NotBlank(message = "*Please provide your login")
  @Column(unique = true)
  private String login;

  @Email(message = "*Please provide a valid Email")
  @NotBlank(message = "*Please provide an email")
  private String email;

  @Length(min = 5, message = "*Your password must have at least 5 characters")
  @NotBlank(message = "*Please provide your password")
  private String password;

  private int active;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  @OneToMany(mappedBy = "user")
  private List<Idea> ideas;

  @OneToMany(mappedBy = "user")
  private List<Comment> comments;

}
