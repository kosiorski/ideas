package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kosiorski.model.Role;
import pl.kosiorski.model.User;
import pl.kosiorski.repository.RoleRepository;
import pl.kosiorski.repository.UserRepository;
import pl.kosiorski.service.UserService;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public UserServiceImpl(
      UserRepository userRepository,
      RoleRepository roleRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public User findUserByLogin(String login) {
    return userRepository.findByLogin(login);
  }

  @Override
  public void saveUser(User user) {

    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setActive(1);
    Role userRole = roleRepository.findByRole("USER");
    user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
    userRepository.save(user);
  }
}
