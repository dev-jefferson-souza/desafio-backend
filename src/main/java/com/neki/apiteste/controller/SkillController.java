package com.neki.apiteste.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neki.apiteste.domain.exception.BadRequestException;
import com.neki.apiteste.domain.model.Skill;
import com.neki.apiteste.domain.service.SkillService;

@CrossOrigin(origins = "**", allowedHeaders = "**", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/skill")
public class SkillController {
  
  @Autowired
  private SkillService service;

  @GetMapping
  public ResponseEntity<List<Skill>> getAll(){
    List<Skill> list = service.getAll();
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Skill> getById(@PathVariable Long id){
    Optional<Skill> optSkill = service.findById(id);
    return ResponseEntity.ok(optSkill.get());
  }

  @PostMapping
  public ResponseEntity<Skill> register(@Valid @RequestBody Skill skill){
    try{
      skill = service.register(skill);
    }catch(Exception e){
      throw new BadRequestException("O objeto está incompleto, por favor verifique o objeto enviado.");
    }
    return new ResponseEntity<>(skill, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Skill> update(@PathVariable Long id, @RequestBody Skill skill){
    try{
      return ResponseEntity.ok(service.update(id, skill));
    }catch(Exception e){
      throw new BadRequestException("O objeto está incompleto, por favor verifique o objeto enviado.");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id){
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

 

}
