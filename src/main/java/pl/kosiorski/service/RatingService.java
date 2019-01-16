package pl.kosiorski.service;

import pl.kosiorski.model.Idea;
import pl.kosiorski.model.Rating;
import pl.kosiorski.model.User;

public interface RatingService {

  double countRating(Idea idea);

  void save(Rating rating);
}
