package com.github.rayinfinite.wallet.user;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.github.rayinfinite.wallet.model.user.User;
import com.github.rayinfinite.wallet.model.user.dto.ChangePassword;
import com.github.rayinfinite.wallet.model.user.dto.UpdateUser;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerDiffblueTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#changePassword(ChangePassword)}
     */
    @Test
    void testChangePassword() throws Exception {
        doNothing().when(userService).changePassword(Mockito.any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/change");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Change password successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link UserController#changePassword(ChangePassword)}
     */
    @Test
    void testChangePassword2() throws Exception {
        doNothing().when(userService).changePassword(Mockito.any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/change");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Change password successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link UserController#changePassword(ChangePassword)}
     */
    @Test
    void testChangePassword3() throws Exception {
        doNothing().when(userService).changePassword(Mockito.<ChangePassword>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/change");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Change password successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link UserController#changePassword(ChangePassword)}
     */
    @Test
    void testChangePassword4() throws Exception {
        doNothing().when(userService).changePassword(Mockito.<ChangePassword>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/change");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Change password successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link UserController#setDefaultBook(long)}
     */
    @Test
    void testSetDefaultBook() throws Exception {
        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUsername("janedoe");
        when(userService.setDefaultBook(anyLong())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/setDefaultBook/{id}", 1L);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Set default book successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link UserController#setBook(long)}
     */
    @Test
    void testSetBook() throws Exception {
        doNothing().when(userService).setBook(anyLong());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/setBook/{id}", 1L);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Set default book successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link UserController#setBook(long)}
     */
    @Test
    void testSetBook2() throws Exception {
        doNothing().when(userService).setBook(anyLong());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/setBook/{id}", 1L);
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Set default book successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link UserController#updateUser(UpdateUser)}
     */
    @Test
    void testUpdateUser() throws Exception {
        doNothing().when(userService).update(Mockito.any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/update");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Update user successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link UserController#updateUser(UpdateUser)}
     */
    @Test
    void testUpdateUser2() throws Exception {
        doNothing().when(userService).update(Mockito.any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/update");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Update user successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link UserController#updateUser(UpdateUser)}
     */
    @Test
    void testUpdateUser3() throws Exception {
        doNothing().when(userService).update(Mockito.<UpdateUser>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/update");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Update user successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link UserController#updateUser(UpdateUser)}
     */
    @Test
    void testUpdateUser4() throws Exception {
        doNothing().when(userService).update(Mockito.<UpdateUser>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/update");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Update user successfully\",\"message\":\"ok\"}"));
    }
}

