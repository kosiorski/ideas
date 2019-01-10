package pl.kosiorski.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "role_id")
  private Long id;

  private String role;
}
