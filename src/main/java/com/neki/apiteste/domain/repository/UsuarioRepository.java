package com.neki.apiteste.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neki.apiteste.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
