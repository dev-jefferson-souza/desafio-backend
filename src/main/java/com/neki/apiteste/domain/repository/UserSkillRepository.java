package com.neki.apiteste.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neki.apiteste.domain.model.UserSkill;

public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {
  List<UserSkill> findAllByOrderByCreatedAtAsc();
}