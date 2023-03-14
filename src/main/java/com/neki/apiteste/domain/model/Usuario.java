package com.neki.apiteste.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(
  name = "generator_user",
  sequenceName = "user_seq",
  initialValue = 1000,
  allocationSize = 1,
  schema = "teste_residencia"
)
@Entity
@Table(name = "user", schema = "teste_residencia")
public class Usuario implements UserDetails{

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "generator_user"
  )
  private Long id;

  @Column(nullable = false, length = 12)
  private String login;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Column(nullable = false, length = 100)
  private String password;

  @UpdateTimestamp
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Column(name = "last_login_date")
  private Date lastLoginDate;

  @ManyToMany
  @JoinTable(
    name = "userSkill",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "skill_id")
  )
  private List<Skill> skill = new ArrayList<Skill>();

  //Implementação do UserDetails
  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  @JsonIgnore
  public String getUsername() {
    return login;
  }

  @Override
  @JsonIgnore
  public String getPassword() {
    return password;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isEnabled() {
    return true;
  }
}