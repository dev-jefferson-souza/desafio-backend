package com.neki.apiteste.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neki.apiteste.domain.exception.BadRequestException;
import com.neki.apiteste.domain.exception.ResourceNotFoundException;
import com.neki.apiteste.domain.model.UserSkill;
import com.neki.apiteste.domain.repository.UserSkillRepository;
import com.neki.apiteste.domain.service.UserSkillService;
import com.neki.apiteste.domain.shared.UpdateUserSkill;

@CrossOrigin(origins = "**", allowedHeaders = "**", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/userSkill")
public class UserSkillController {
  
  @Autowired
  private UserSkillService service;

  @Autowired
  private UserSkillRepository repository;

  @GetMapping
  public ResponseEntity<List<UserSkill>> getAll(){
    List<UserSkill> list = service.getAll();
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserSkill> getById(@PathVariable Long id){
    Optional<UserSkill> optSkill = service.findById(id);
    return ResponseEntity.ok(optSkill.get());
  }


  @PostMapping
  public ResponseEntity<UserSkill> register(@Validated @RequestBody UserSkill userskill){
    try{
      userskill = service.register(userskill);
    }catch(Exception e){
      throw new BadRequestException("O objeto está incompleto, por favor verifique o objeto enviado." + e);
    }
    return new ResponseEntity<>(userskill, HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<UpdateUserSkill> update(@PathVariable Long id, @RequestBody Map<String, Object> partialUpdate) {
      UserSkill userSkillToUpdate = repository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("UserSkill não encontrado com id: " + id));
      
      partialUpdate.forEach((key, value) -> {
          switch (key) {
              case "knowledgeLevel":
              userSkillToUpdate.setId(id);
                  userSkillToUpdate.setKnowledgeLevel((Integer) value);
                  break;
              default:
                  throw new BadRequestException("Invalid field: " + key);
          }
      });
      
      UserSkill updatedUserSkill = repository.save(userSkillToUpdate);
      UpdateUserSkill response = new UpdateUserSkill(updatedUserSkill.getKnowledgeLevel(), updatedUserSkill.getId());
      return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id){
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

 

}
