package com.neki.apiteste.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neki.apiteste.domain.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long>{
  
}
