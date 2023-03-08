package com.neki.apiteste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neki.apiteste.domain.model.Usuario;
import com.neki.apiteste.domain.service.UsuarioService;

@RestController
@RequestMapping("/user")
public class UsuarioController {
  
  @Autowired
  UsuarioService service;

  @GetMapping
  public ResponseEntity<List<Usuario>> getAll(){
    List<Usuario> list = service.getAll();
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Usuario> getById(@PathVariable Long id){
    Optional<Usuario> optUsuario = service.getById(id);
    return ResponseEntity.ok(optUsuario.get());
  }

  @PostMapping
  public ResponseEntity<Usuario> register(@Validated @RequestBody Usuario usuario){
    usuario = service.register(usuario);

    return new ResponseEntity<>(usuario, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario){
    return ResponseEntity.ok(service.update(id, usuario));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id){
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}

