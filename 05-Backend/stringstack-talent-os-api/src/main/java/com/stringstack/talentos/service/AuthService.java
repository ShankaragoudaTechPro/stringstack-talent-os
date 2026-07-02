package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.auth.LoginRequest;
import com.stringstack.talentos.dto.auth.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}