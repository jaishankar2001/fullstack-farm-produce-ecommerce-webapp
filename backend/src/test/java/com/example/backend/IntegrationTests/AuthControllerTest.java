
package com.example.backend.IntegrationTests;

import com.example.backend.controller.AuthController;
import com.example.backend.dto.request.ResetPasswordRequest;
import com.example.backend.dto.request.SignInRequest;
import com.example.backend.entities.*;
import com.example.backend.repository.UserMetaRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.VerificationCodeRepository;
import com.example.backend.services.AuthenticationService;
import com.example.backend.services.VerificationService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.backend.dto.request.SignUpRequest;
import com.example.backend.dto.response.LoginResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthController authController;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserMetaRepository userMetaRepository;

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private UserRepository userRepository;

    private String regisetrUserEmail = "IntegrateTest@gmail.com";

    private String loggedInUserEmail = "ecopickasdc@gmail.com";
    private static User loggedInUser;
    private static String token;

    @BeforeEach
    public void setUp() {
        createLoginUser();
        removeUser(regisetrUserEmail);
    }

    @AfterEach()
    public void clear() {
        removeUser(regisetrUserEmail);
    }

    public void removeUser(String email) {
        User IntegrateUser = userRepository.findByEmail(email);
        if (IntegrateUser != null) {
            UserMeta userMeta = IntegrateUser.getUserMeta();
            userMetaRepository.deleteById(userMeta.getId());
            userRepository.deleteById(IntegrateUser.getId());
        }
    }

    public void createLoginUser() {
        User isUserExists = userRepository.findByEmail(loggedInUserEmail);
        String password = "Test@123";
        if(isUserExists == null){
            SignUpRequest request = new SignUpRequest();
            request.setFirstName("Test");
            request.setLastName("User");
            request.setPassword(password);
            request.setEmail(loggedInUserEmail);
            request.setRole(Role.FARMER);

            authService.signUp(request);
            VerificationCode verificationCode = verificationCodeRepository.findByEmail(loggedInUserEmail);
            verificationService.verify(verificationCode.getCode(), loggedInUserEmail);
        }
        SignInRequest loginReq = new SignInRequest();
        loginReq.setEmail(loggedInUserEmail);
        loginReq.setPassword(password);
        LoginResponse res = authService.signIn(loginReq);
        token = res.getToken();
        loggedInUser = userRepository.findByEmail(loggedInUserEmail);
    }

    @Test
    public void signupTest() throws Exception {
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Test");
        request.setLastName("User");
        request.setPassword("Test@123");
        request.setEmail(regisetrUserEmail);
        request.setRole(Role.FARMER);

        String requestBody = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void signInTest() throws Exception {
        SignInRequest request = new SignInRequest();
        request.setPassword("Test@123");
        request.setEmail(loggedInUserEmail);

        String requestBody = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void testForgotpasswordReq() throws Exception {
        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setEmail(loggedInUser.getEmail());

        String requestBody = objectMapper.writeValueAsString(request);
        MvcResult result = mockMvc.perform(post("/api/auth/ResetPasswordReq")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();

        String responseString = result.getResponse().getContentAsString();

        assertEquals("Please check email for password reset link", responseString);
    }
}