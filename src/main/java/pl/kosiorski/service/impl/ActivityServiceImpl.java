package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.model.Activity;
import pl.kosiorski.repository.ActivityRepository;
import pl.kosiorski.service.ActivityService;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

  private final ActivityRepository activityRepository;

  @Autowired
  public ActivityServiceImpl(ActivityRepository activityRepository) {
    this.activityRepository = activityRepository;
  }

  @Override
  public List<Activity> lastTen() {
    return activityRepository.findFirst10ByOrderByCreatedDesc();
  }

  @Override
  public Activity save(Activity activity) {
    return activityRepository.save(activity);
  }
}
