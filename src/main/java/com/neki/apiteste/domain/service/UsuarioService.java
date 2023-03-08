package com.neki.apiteste.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neki.apiteste.domain.exception.ResourceNotFoundException;
import com.neki.apiteste.domain.model.Usuario;
import com.neki.apiteste.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
  
  @Autowired
  private UsuarioRepository repository;

  public List<Usuario> getAll(){
    return repository.findAll();
  }

  public Optional<Usuario> getById(Long id){
    Optional<Usuario> optUsuario = repository.findById(id);
    if(optUsuario.isEmpty()){
      throw new ResourceNotFoundException("Não foi possível encontrar o usuário com id " + id);
    }
    return optUsuario;
  }

  public Usuario register (Usuario usuario){
    return repository.save(usuario);
  }

  public Usuario update (Long id, Usuario usuario){
    repository.findById(id);
    usuario.setId(id);

    return repository.save(usuario);
  }

  public void delete(Long id){
    Optional<Usuario> optUsuario = repository.findById(id);
    if(optUsuario.isEmpty()){
      throw new ResourceNotFoundException("Não foi possível encontrar o usuário com id " + id);
    }
    repository.deleteById(id);
  }
}
