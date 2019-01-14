package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.model.Idea;
import pl.kosiorski.model.Rating;
import pl.kosiorski.model.User;
import pl.kosiorski.repository.IdeaRepository;
import pl.kosiorski.repository.RatingRepository;
import pl.kosiorski.repository.UserRepository;
import pl.kosiorski.service.IdeaService;
import pl.kosiorski.service.RatingService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
  public Double countRating(Long userId, int userRate, Long ideaId) {

    Rating rating = new Rating();

    LinkedList<Rating> ratings = new LinkedList<>();

    try{
        ratings = ratingRepository.findAll();
    }
    catch (NullPointerException e){
      System.out.println(e.getMessage());
    }


    rating.setValue(userRate);
    rating.setUser(userRepository.findById(userId).get());
    rating.setIdea(ideaRepository.findById(ideaId).get());
    ratings.add(rating);
    ratingRepository.save(rating);

    LinkedList<Rating> ratingsToCount = ratingRepository.findAll();

    long sum = 0L;
    double result = 0;

    for (int i = 0; i < ratingsToCount.size(); i++) {
      sum += ratings.get(i).getValue();
    }

    result = (double) sum / ratingsToCount.size();

    return result;
  }
}
