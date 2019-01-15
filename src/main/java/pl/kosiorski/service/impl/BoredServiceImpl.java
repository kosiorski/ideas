package pl.kosiorski.service.impl;

import org.springframework.stereotype.Service;
import pl.kosiorski.service.BoredService;

@Service
public class BoredServiceImpl implements BoredService {

  @Override
  public String get() {

    return "yo";
  }
}
