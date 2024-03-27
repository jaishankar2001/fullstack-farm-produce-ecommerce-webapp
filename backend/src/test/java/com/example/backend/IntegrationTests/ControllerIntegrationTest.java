package com.example.backend.IntegrationTests;

import com.example.backend.dto.request.ResetPasswordRequest;
import com.example.backend.dto.request.SignInRequest;
import com.example.backend.dto.response.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerIntegrationTest {
    private static String token;
    private static final String ADMIN_EMAIL = "admin123@gmail.com";
    static final double LONGITUDE = 789.012;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // @Autowired
    // private UserRepository userRepository;

    // @Autowired
    // private AuthenticationService authService;

    // @Autowired
    // private VerificationCodeRepository verificationCodeRepository;

    // @Autowired
    // private VerificationService verificationService;

    @BeforeEach
    public void setUp() throws Exception {
        token = getToken();
        // createLoginUser();
    }

    public String getToken() throws Exception {
        SignInRequest request = new SignInRequest();
        request.setEmail(ADMIN_EMAIL);
        request.setPassword("Test@123");

        String credentialsDtoJson = objectMapper.writeValueAsString(request);

        MvcResult result = mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(credentialsDtoJson))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        LoginResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), LoginResponse.class);
        System.out.println("RESSSS???" + response.getToken());
        return response.getToken();

    }

    // public void createLoginUser() {
    // String adminUser = "admin123@gmail.com";
    // User isUserExists = userRepository.findByEmail(adminUser);
    // String password = "Test@123";
    // if (isUserExists == null) {
    // System.out.println("SHOULD NOT GO HEREEEEEE!");
    // SignUpRequest request = new SignUpRequest();
    // request.setFirstName("Test");
    // request.setLastName("User");
    // request.setPassword(password);
    // request.setEmail(adminUser);
    // request.setRole(Role.ADMIN);

    // authService.signUp(request);

    // }
    // VerificationCode verificationCode =
    // verificationCodeRepository.findByEmail(adminUser);
    // if (verificationCode != null)
    // verificationService.verify(verificationCode.getCode(), adminUser);

    // SignInRequest loginReq = new SignInRequest();
    // loginReq.setEmail(adminUser);
    // loginReq.setPassword(password);
    // LoginResponse res = authService.signIn(loginReq);
    // System.out.println("TOKENNNN?" + res.getToken());
    // token = res.getToken();
    // }

    @Test
    public void testForgotpasswordReq() throws Exception {
        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setEmail(ADMIN_EMAIL);

        String requestBody = objectMapper.writeValueAsString(request);
        MvcResult result = mockMvc.perform(post("/api/auth/ResetPasswordReq")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();

        String responseString = result.getResponse().getContentAsString();

        assertEquals("Please check email for password reset link", responseString);
    }

    @Test
    public void testGetStatisticsAdmin() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/Admin/info-page")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        assertNotNull(responseBody);
    }

    @Test
    public void testGetCategory() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/category/list")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        assertNotNull(responseBody);
    }

    @Test
    public void testCustomerListFarm() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/customer/listfarms")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .param("farmName", ""))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        assertNotNull(responseBody);
    }

    @Test
    public void testHomeMeta() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/home/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        assertNotNull(responseBody);
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

    @Test
    public void testGetOwnSubscription() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/subscribe/my-subscription")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        assertNotNull(responseBody);
    }

    @Test
    public void getMySubscribedProducts() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/subscribe/my-subscribed-products")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        assertNotNull(responseBody);
    }

    @Test
    public void getOwnFarms() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/farmer/own-farms")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        assertNotNull(responseBody);
    }

    @Test
    public void testGetFarm() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/farmer/getFarm/{farmId}", 6)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        System.out.println("responseBody" + responseBody);
        assertNotNull(responseBody);
    }

}
