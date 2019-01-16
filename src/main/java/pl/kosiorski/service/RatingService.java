package pl.kosiorski.service;

import pl.kosiorski.model.Idea;
import pl.kosiorski.model.Rating;
import pl.kosiorski.model.User;

public interface RatingService {

  double countRating(Long ideaId);

  void save(Rating rating);
}
