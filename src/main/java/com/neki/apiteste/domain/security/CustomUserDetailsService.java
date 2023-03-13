package com.neki.apiteste.domain.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.neki.apiteste.domain.service.UsuarioService;

@Service
public class CustomUserDetailsService implements UserDetailsService{

  @Autowired
  private UsuarioService service;

  @Override
  public UserDetails loadUserByUsername(String login){
    return service.getByLogin(login).get();
  }

  public UserDetails getUserById(Long id){
    return service.getById(id).get();
  }
  
}
