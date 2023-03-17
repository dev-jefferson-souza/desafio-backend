package com.neki.apiteste.domain.service;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neki.apiteste.domain.exception.ResourceNotFoundException;
import com.neki.apiteste.domain.model.UserSkill;
import com.neki.apiteste.domain.repository.UserSkillRepository;

@Service
public class UserSkillService {

  @Autowired
  private UserSkillRepository repository;

  public Optional<UserSkill> findById(Long id) {
    Optional<UserSkill> optUserSkill = repository.findById(id);
    if(optUserSkill.isEmpty()){
      throw new ResourceNotFoundException("Não foi possível encontrar a userSkill com id: " + id);
    }
    return optUserSkill;
  }

  public List<UserSkill> getAll() {
    return repository.findAllByOrderByCreatedAtAsc();
  }

  @Transactional
  public UserSkill register(UserSkill userSkill) {
    userSkill.setKnowledgeLevel(0);
    return repository.save(userSkill);
  }

  public void delete(Long id) {
    findById(id);
    repository.deleteById(id);
  }


}