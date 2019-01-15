package pl.kosiorski.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class BoredActivity {

  String activity;
  String accessibility;
  String type;
  String participants;
  String price;
  String link;
  String key;
}
