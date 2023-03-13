package com.neki.apiteste.domain.model.Login;

import lombok.Data;

@Data
public class LoginRequest {
  private String login;
  private String password;
}
