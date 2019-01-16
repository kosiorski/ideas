package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.model.Idea;
import pl.kosiorski.model.Rating;
import pl.kosiorski.repository.RatingRepository;
import pl.kosiorski.service.RatingService;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
  private final RatingRepository ratingRepository;

  @Autowired
  public RatingServiceImpl(RatingRepository ratingRepository) {
    this.ratingRepository = ratingRepository;
  }

  @Override
  public double countRating(Idea idea) {

    Long ideaId = idea.getId();
    List<Rating> ratings = ratingRepository.findAllByIdeaId(ideaId);

    float sum = 0;

    for (Rating rating : ratings) {
      sum += rating.getValue();
    }

    return sum / ratings.size();
  }

  @Override
  public void save(Rating rating) {
    ratingRepository.save(rating);
  }
}
