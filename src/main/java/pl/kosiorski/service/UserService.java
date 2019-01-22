package pl.kosiorski.service;

import pl.kosiorski.model.User;

public interface UserService {

  User findUserByLogin(String login);

  void saveUser(User user);

  User findCurrentLoggedUser();
}
