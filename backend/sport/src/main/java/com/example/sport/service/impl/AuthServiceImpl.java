package com.example.sport.service.impl;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sport.dto.request.LoginRequest;
import com.example.sport.dto.request.RegisterRequest;
import com.example.sport.dto.response.AuthResponse;
import com.example.sport.entity.User;
import com.example.sport.repository.UserRepository;
import com.example.sport.security.JwtService;
import com.example.sport.service.AuthService;

import tools.jackson.databind.ser.std.ToEmptyObjectSerializer;

@Service
public class AuthServiceImpl implements AuthService{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
	@Override
	public AuthResponse register(RegisterRequest request) {
		if(userRepository.existsByEmail(request.getEmail())){
			throw new RuntimeException("Email already exists");
		}
		User user=new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPhone(request.getPhone());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		userRepository.save(user);
		String token=jwtService.generateToken(user.getEmail());
		return new AuthResponse(token);
		
	}
	@Override 
	public AuthResponse login(LoginRequest request) {
		User user=userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
		boolean matches=passwordEncoder.matches(request.getPassword(), user.getPassword());
		if(!matches) {
			throw new RuntimeException("Invalid credentials");
		}
		String token=jwtService.generateToken(request.getEmail());
		return new AuthResponse(token);
	}
}
