package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.model.Idea;
import pl.kosiorski.model.Rating;
import pl.kosiorski.model.User;
import pl.kosiorski.repository.IdeaRepository;
import pl.kosiorski.repository.RatingRepository;
import pl.kosiorski.repository.UserRepository;
import pl.kosiorski.service.RatingService;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
  private final UserRepository userRepository;
  private final IdeaRepository ideaRepository;
  private final RatingRepository ratingRepository;

  @Autowired
  public RatingServiceImpl(
      UserRepository userRepository,
      IdeaRepository ideaRepository,
      RatingRepository ratingRepository) {
    this.userRepository = userRepository;
    this.ideaRepository = ideaRepository;
    this.ratingRepository = ratingRepository;
  }

  @Override
  public double countRating(Long ideaId) {

    List<Rating> ratings = ratingRepository.findAllByIdeaId(ideaId);

    long sum = 0L;
    double result;

    for (Rating rating : ratings) {
      sum += rating.getValue();
    }

    result = (double) sum / ratings.size();
    return result;
  }

  @Override
  public void save(Rating rating) {
    ratingRepository.save(rating);
  }
}
