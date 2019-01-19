package pl.kosiorski.service;

import pl.kosiorski.model.Idea;
import pl.kosiorski.model.Rating;

public interface RatingService {

  double countRating(Idea idea);

  void save(Rating rating);
}
