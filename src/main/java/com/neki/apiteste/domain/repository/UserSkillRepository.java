package com.neki.apiteste.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neki.apiteste.domain.model.UserSkill;
import com.neki.apiteste.domain.shared.UserSkillInfo;

public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {

  @Query(value = "SELECT s.id AS skill_id, s.name AS name, s.version, s.description, s.image_url, us.knowledge_level, us.id, us.updated_at "
+ "FROM teste_residencia.user_skill us "
+ "INNER JOIN teste_residencia.skill s ON s.id = us.skill_id "
+ "WHERE us.user_id = :userId", nativeQuery = true)
List<UserSkillInfo> findSkillByUserId(@Param("userId") Long userId);


}
