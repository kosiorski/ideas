package pl.kosiorski.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
public class BoredActivity {

  String activity;
  String accessibility;
  String type;
  String participants;
  String price;
  String link;
  String key;
}
