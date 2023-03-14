package com.neki.apiteste.domain.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.neki.apiteste.domain.exception.DataIntegrityViolationException;
import com.neki.apiteste.domain.exception.ResourceNotFoundException;
import com.neki.apiteste.domain.model.Usuario;
import com.neki.apiteste.domain.model.Login.LoginResponse;
import com.neki.apiteste.domain.repository.UsuarioRepository;
import com.neki.apiteste.domain.security.JWTService;

@Service
public class UsuarioService {

  private static final String headerPrefix = "Bearer ";

  @Autowired
  private UsuarioRepository repository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JWTService jwtService;

  @Autowired
  private AuthenticationManager authenticationManager;

  public List<Usuario> getAll() {
    return repository.findAll();
  }

  public Optional<Usuario> getById(Long id) {
    Optional<Usuario> optUsuario = repository.findById(id);
    if (optUsuario.isEmpty()) {
      throw new ResourceNotFoundException("Não foi possível encontrar o usuário com id: " + id);
    }
    return optUsuario;
  }

  public Optional<Usuario> getByLogin(String login) {
    return repository.findByLogin(login);
  }

  public Usuario register(Usuario usuario) {
    usuario.setId(null);

    if (getByLogin(usuario.getLogin()).isPresent()) {

      throw new DataIntegrityViolationException("Já existe um usuário cadastrado com o login: " + usuario.getLogin());
    }

    String password = passwordEncoder.encode(usuario.getPassword());
    usuario.setPassword(password);

    return repository.save(usuario);
  }

  public Usuario update(Long id, Usuario usuario) {
    repository.findById(id);
    usuario.setId(id);

    return repository.save(usuario);
  }

  public void delete(Long id) {
    Optional<Usuario> optUsuario = repository.findById(id);
    if (optUsuario.isEmpty()) {
      throw new ResourceNotFoundException("Não foi possível encontrar o usuário com id: " + id);
    }

    repository.deleteById(id);
  }

  public LoginResponse login(String login, String password) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(login, password, Collections.emptyList()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = headerPrefix + jwtService.generateToken(authentication);

    Usuario usuario = repository.findByLogin(login).get();

    usuario.setLastLoginDate(new Date());
    repository.save(usuario);

    
    return new LoginResponse(token, usuario);
  }
}
