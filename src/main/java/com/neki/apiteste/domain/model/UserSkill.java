package com.neki.apiteste.domain.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Table(name = "user_skill", schema = "teste_residencia")
@SequenceGenerator(
name = "generator_user_skill",
sequenceName = "user_skill_seq",
initialValue = 1000,
allocationSize = 1,
schema = "teste_residencia"
)
@Entity(name = "UserSkill")
public class UserSkill {
  
  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "generator_user_skill"
  )
  private Long id;


  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private Usuario user;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "skill_id")
  private Skill skill;

  @Column(nullable = false, name = "knowledge_level")
  private Integer knowledgeLevel;

  @CreationTimestamp
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Column(nullable = false, name = "created_at")
  private Date createdAt;
  
  @UpdateTimestamp
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Column(nullable = true, name = "updated_at")
  private Date updatedAt;

}
