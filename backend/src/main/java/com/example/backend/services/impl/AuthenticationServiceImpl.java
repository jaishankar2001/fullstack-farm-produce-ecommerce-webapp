package com.example.backend.services.impl;

<<<<<<< HEAD
import com.example.backend.dto.request.RefreshTokenRequest;
import com.example.backend.dto.request.SignInRequest;
import com.example.backend.dto.request.SignUpRequest;
import com.example.backend.dto.response.JwtAuthenticationResponse;
import com.example.backend.entities.User;
import com.example.backend.entities.UserMeta;
import com.example.backend.repository.UserMetaRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.AuthenticationService;
import com.example.backend.services.JWTService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
// @NoArgsConstructor
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserMetaRepository userMetaRepository;

    @Override
    public User signUp(SignUpRequest signUpRequest){
        
=======
import com.example.backend.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.backend.services.AuthenticationService;
import com.example.backend.entities.User;
import com.example.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User signUp(SignUpRequest signUpRequest) {
>>>>>>> 54a6bb6c909c10a24cc66aa4cc064847a90b8442
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstname(signUpRequest.getFirstName());
        user.setLastname(signUpRequest.getLastName());
        user.setRole(signUpRequest.getRole());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);

<<<<<<< HEAD
        UserMeta userMeta = new UserMeta();
        userMeta.setUser(user);
        userMetaRepository.save(userMeta);

        return user;

    }

    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        System.out.println(signInRequest.getEmail() + signInRequest.getPassword());
        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow();
        System.out.println(user);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        // User tempUser = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow();


        System.out.println("Hereeee?");
        
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;   
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());


        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), userDetails)){
            var jwt = jwtService.generateToken(userDetails);

            System.out.println("TOKEN JWTT : " + jwt);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse; 
        }
        return null;
    }
=======
        return user;
    }

>>>>>>> 54a6bb6c909c10a24cc66aa4cc064847a90b8442
}

