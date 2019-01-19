package pl.kosiorski.service;

import pl.kosiorski.model.Activity;

import java.util.List;

public interface ActivityService {
  List<Activity> lastTen();

  Activity save(Activity activity);
}
