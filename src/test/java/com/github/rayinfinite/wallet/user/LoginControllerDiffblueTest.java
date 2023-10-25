package com.github.rayinfinite.wallet.user;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rayinfinite.wallet.model.user.User;
import com.github.rayinfinite.wallet.model.user.dto.AddUser;
import com.github.rayinfinite.wallet.model.user.dto.ForgetPassword;
import com.github.rayinfinite.wallet.model.user.dto.Login;
import com.github.rayinfinite.wallet.security.JwtConfig;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {LoginController.class})
@ExtendWith(SpringExtension.class)
class LoginControllerDiffblueTest {
    @MockBean
    private HttpServletRequest httpServletRequest;

    @MockBean
    private JwtConfig jwtConfig;

    @Autowired
    private LoginController loginController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link LoginController#login(Login)}
     */
    @Test
    void testLogin() throws Exception {
        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        when(userService.login(Mockito.any())).thenReturn(user);
        when(jwtConfig.createToken(Mockito.<Long>any())).thenReturn("ABC123");

        Login login = new Login();
        login.setPassword("iloveyou");
        login.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(login);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":\"ABC123\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link LoginController#register(AddUser)}
     */
    @Test
    void testRegister() throws Exception {
        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        when(userService.register(Mockito.any())).thenReturn(user);
        when(jwtConfig.createToken(Mockito.<Long>any())).thenReturn("ABC123");

        AddUser addUser = new AddUser();
        addUser.setEmail("jane.doe@example.org");
        addUser.setNickName("Nick Name");
        addUser.setPassword("iloveyou");
        addUser.setTelephone("6625550144");
        addUser.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(addUser);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":\"ABC123\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link LoginController#forgetPassword(ForgetPassword)}
     */
    @Test
    void testForgetPassword() throws Exception {
        doNothing().when(userService).forgetPassword(Mockito.any());

        ForgetPassword forgetPassword = new ForgetPassword();
        forgetPassword.setEmail("jane.doe@example.org");
        forgetPassword.setNewPassword("iloveyou");
        forgetPassword.setNickName("Nick Name");
        forgetPassword.setTelephone("6625550144");
        forgetPassword.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(forgetPassword);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login/forget")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Reset password successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link LoginController#CurrentUser()}
     */
    @Test
    void testCurrentUser() throws Exception {
        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        when(userService.CurrentUser()).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login/current");
        MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":{\"id\":1,\"username\":\"janedoe\",\"password\":\"iloveyou\",\"nickName\":\"Nick Name\",\"avatar\":"
                                        + "\"Avatar\",\"telephone\":\"6625550144\",\"email\":\"jane.doe@example.org\",\"registerTime\":[1970,1,1,0,0],"
                                        + "\"defaultBook\":1},\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link LoginController#logout()}
     */
    @Test
    void testLogout() throws Exception {
        when(httpServletRequest.getSession()).thenReturn(new MockHttpSession());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/logout");
        MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Logout successfully\",\"message\":\"ok\"}"));
    }
}

