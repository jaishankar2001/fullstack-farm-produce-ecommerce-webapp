package com.example.backend.IntegrationTests;

import com.example.backend.dto.request.OrderRequest;
import com.example.backend.dto.request.SignInRequest;
import com.example.backend.dto.request.SignUpRequest;
import com.example.backend.dto.response.LoginResponse;
import com.example.backend.entities.Role;
import com.example.backend.entities.User;
import com.example.backend.entities.VerificationCode;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.VerificationCodeRepository;
import com.example.backend.services.AuthenticationService;
import com.example.backend.services.VerificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    private String loggedInUserEmail = "ecopickasdc@gmail.com";
    private static User loggedInUser;
    private static String token;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    private VerificationService verificationService;

    @BeforeEach
    public void setUp() {
        createLoginUser();
    }

    public void createLoginUser() {
        User isUserExists = userRepository.findByEmail(loggedInUserEmail);
        String password = "Test@123";
        if(isUserExists == null){
            System.out.println("SHOULD NOT GO HEREEEEEE!");
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
    public void testOrderHistory() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/order/orderHistory")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        assertNotNull(responseBody);
    }

}
