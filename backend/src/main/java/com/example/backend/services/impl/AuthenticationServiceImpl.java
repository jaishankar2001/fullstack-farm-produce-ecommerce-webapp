package com.example.backend.services.impl;

import com.example.backend.dto.request.RefreshTokenRequest;
import com.example.backend.dto.request.SignInRequest;
import com.example.backend.dto.request.SignUpRequest;
import com.example.backend.dto.response.JwtAuthenticationResponse;
import com.example.backend.entities.User;
import com.example.backend.entities.UserMeta;
import com.example.backend.exception.ApiRequestException;
import com.example.backend.repository.UserMetaRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.AuthenticationService;
import com.example.backend.services.JWTService;
import com.example.backend.services.VerificationService;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
    private final VerificationService verificationService;

    @Override
    public User signUp(SignUpRequest signUpRequest) {

        User tempUser = userRepository.findByEmail(signUpRequest.getEmail());
        if (tempUser != null) {
            throw new ApiRequestException("User already registered");
        }
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstname(signUpRequest.getFirstName());
        user.setLastname(signUpRequest.getLastName());
        user.setRole(signUpRequest.getRole());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);

        UserMeta userMeta = new UserMeta();
        userMeta.setUser(user);
        userMetaRepository.save(userMeta);

        verificationService.sendVerificationEmail(user);

        return user;

    }

    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {

        UserMeta userMeta;
        // User tempUser =
        // userRepository.findByEmail(signInRequest.getEmail()).orElseThrow();
        User tempUser = userRepository.findByEmail(signInRequest.getEmail());

        if (tempUser != null) {
            Boolean password = BCrypt.checkpw(signInRequest.getPassword(), tempUser.getPassword());

            if (password) {
                userMeta = userMetaRepository.findByUser(tempUser);

                if (userMeta.isVerified() == true) {
                    var jwt = jwtService.generateToken(tempUser);
                    var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), tempUser);

                    JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
                    jwtAuthenticationResponse.setToken(jwt);
                    jwtAuthenticationResponse.setRefreshToken(refreshToken);
                    return jwtAuthenticationResponse;
                } else {
                    throw new ApiRequestException("please varify your email");
                }
            } else {
                throw new ApiRequestException("Wrong username or password");
            }
        } else {
            throw new ApiRequestException("Wrong username or password");
        }
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());

        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), userDetails)) {
            var jwt = jwtService.generateToken(userDetails);

            System.out.println("TOKEN JWTT : " + jwt);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
