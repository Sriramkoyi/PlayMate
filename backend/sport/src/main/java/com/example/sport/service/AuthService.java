package com.example.sport.service;

import com.example.sport.dto.request.LoginRequest;
import com.example.sport.dto.request.RegisterRequest;
import com.example.sport.dto.response.AuthResponse;

public interface AuthService {
	AuthResponse register(RegisterRequest request);
	AuthResponse login(LoginRequest request);
}
