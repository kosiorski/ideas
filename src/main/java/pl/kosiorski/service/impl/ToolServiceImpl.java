package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.model.Tool;
import pl.kosiorski.repository.ToolRepsitory;
import pl.kosiorski.service.ToolService;

import java.util.List;

@Service
public class ToolServiceImpl implements ToolService {
  private final ToolRepsitory toolRepsitory;

  @Autowired
  public ToolServiceImpl(ToolRepsitory toolRepsitory) {
    this.toolRepsitory = toolRepsitory;
  }

  @Override
  public List<Tool> findAll() {
    return toolRepsitory.findAll();
  }
}
