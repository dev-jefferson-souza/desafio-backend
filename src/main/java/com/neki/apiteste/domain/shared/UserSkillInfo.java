package com.neki.apiteste.domain.shared;

import java.sql.Date;

public interface UserSkillInfo {
  Long getSkillId();
  String getName();
  String getVersion();
  String getDescription();
  String getImageUrl();
  Integer getKnowledgeLevel();
  Long getId();
  Date getUpdatedAt();
}