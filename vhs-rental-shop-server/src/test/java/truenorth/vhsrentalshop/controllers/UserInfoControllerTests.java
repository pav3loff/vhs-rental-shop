package truenorth.vhsrentalshop.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserInfoController.class)
public class UserInfoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(value = "test")
    public void getUserInfoSuccessTest() throws Exception {
        mockMvc.perform(get("/user-info").principal(() -> "test"))
                .andExpect(status().isOk());
    }

    @Test
    public void getUnauthorizedUserInfoTest() throws Exception {
        mockMvc.perform(get("/user-info").principal(() -> "test"))
                .andExpect(status().isUnauthorized());
    }

}
