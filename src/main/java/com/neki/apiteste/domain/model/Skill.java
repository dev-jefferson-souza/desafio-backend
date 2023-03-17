package com.neki.apiteste.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Table(name = "skill", schema = "teste_residencia")
@SequenceGenerator(
name = "generator_skill",
sequenceName = "skill_seq",
initialValue = 1000,
allocationSize = 1,
schema = "teste_residencia"
)
@Entity(name = "Skill")
public class Skill {
  
  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "generator_skill"
  )  private Long id;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = true, length = 10)
  private String version;

  @Column(nullable = false, length = 255)
  private String description;

  @Column(nullable = true, length = 255)
  private String image_url;

  @ManyToMany(mappedBy = "skill")
  @JsonBackReference
  private List<Usuario> skill = new ArrayList<Usuario>();
  
}
