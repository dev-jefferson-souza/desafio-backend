package com.neki.apiteste.domain.shared;

import java.sql.Date;

public interface UserSkillInfo {
  Long getSkill_id();
  String getName();
  String getVersion();
  String getDescription();
  String getImage_url();
  Integer getKnowledge_level();
  Long getId();
  Date getUpdated_at();
}