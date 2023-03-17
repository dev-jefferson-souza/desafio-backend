package com.neki.apiteste.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neki.apiteste.domain.exception.ResourceNotFoundException;
import com.neki.apiteste.domain.model.Skill;
import com.neki.apiteste.domain.repository.SkillRepository;

@Service
public class SkillService {

  @Autowired
  private SkillRepository repository;

  public List<Skill> getAll() {
    return repository.findAll();
  }

  public Optional<Skill> findById(Long id) {
    Optional<Skill> optSkill = repository.findById(id);
    if (optSkill.isEmpty()) {
      throw new ResourceNotFoundException("Não foi possível encontrar a skill com id: " + id);
    }
    return optSkill;
  }

  @Transactional
  public Skill register(Skill skill) {
    // skill.setId(null);
    return repository.save(skill);
  }

  public Skill update(Long id, Skill skill) {
    repository.findById(id);
    skill.setId(id);

    return repository.save(skill);
  }

  public void delete(Long id) {
    Optional<Skill> optSkill = repository.findById(id);
    if (optSkill.isEmpty()) {
      throw new ResourceNotFoundException("Não foi possível encontrar uma skill com id: " + id);
    }

    repository.deleteById(id);
  }
}
