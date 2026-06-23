package com.example.sport.controller;

import java.nio.file.attribute.UserDefinedFileAttributeView;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sport.dto.request.LoginRequest;
import com.example.sport.dto.request.RegisterRequest;
import com.example.sport.dto.response.AuthResponse;
import com.example.sport.entity.User;
import com.example.sport.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	private final AuthService authService;
	public AuthController(AuthService authService) {
		this.authService=authService;
	}
	@PostMapping("/register")
	public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
		return authService.register(request);
	}
	@PostMapping("/login")
	public AuthResponse login(@Valid @RequestBody LoginRequest request) {
		return authService.login(request);
	}
}