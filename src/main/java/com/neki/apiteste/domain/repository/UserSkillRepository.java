package com.neki.apiteste.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neki.apiteste.domain.model.UserSkill;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {
  List<UserSkill> findAllByOrderByCreatedAtAsc();
}