package com.github.rayinfinite.wallet.user.factory;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.rayinfinite.wallet.model.user.User;
import com.github.rayinfinite.wallet.model.user.dto.Login;
import com.github.rayinfinite.wallet.user.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UsernamePasswordLoginService.class})
@ExtendWith(SpringExtension.class)
class UsernamePasswordLoginServiceDiffblueTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UsernamePasswordLoginService usernamePasswordLoginService;

    /**
     * Method under test: {@link UsernamePasswordLoginService#login(Login)}
     */
    @Test
    void testLogin() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findOneByUsername(Mockito.<String>any())).thenReturn(ofResult);

        Login login = new Login();
        login.setLoginType(1);
        login.setPassword("iloveyou");
        login.setUsername("janedoe");
        User actualLoginResult = usernamePasswordLoginService.login(login);
        verify(userRepository).findOneByUsername(Mockito.<String>any());
        assertSame(user, actualLoginResult);
    }
}
