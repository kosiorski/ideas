package pl.kosiorski.service;

public interface RatingService {

    Double countRating(Long userId, int userRate, Long ideaId);
}
