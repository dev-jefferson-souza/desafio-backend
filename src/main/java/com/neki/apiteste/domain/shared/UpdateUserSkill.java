package com.neki.apiteste.domain.shared;

import com.neki.apiteste.domain.model.UserSkill;

import lombok.Data;

@Data
public class UpdateUserSkill {
  private Integer knowledgeLevel;
  private Long id;
  
  public UpdateUserSkill(){
  
  }

  public UpdateUserSkill(Integer knowledgeLevel, Long id){
    this.knowledgeLevel = knowledgeLevel;
    this.id = id;
  }

  public UpdateUserSkill(UserSkill userSkill){
    knowledgeLevel = userSkill.getKnowledgeLevel();
  }
}
