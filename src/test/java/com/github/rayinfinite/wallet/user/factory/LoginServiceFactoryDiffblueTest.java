package com.github.rayinfinite.wallet.user.factory;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LoginServiceFactory.class})
@ExtendWith(SpringExtension.class)
class LoginServiceFactoryDiffblueTest {
    @MockBean
    private EmailPasswordLoginService emailPasswordLoginService;

    @Autowired
    private LoginServiceFactory loginServiceFactory;

    @MockBean
    private PhonePasswordLoginService phonePasswordLoginService;

    @MockBean
    private UsernamePasswordLoginService usernamePasswordLoginService;

    /**
     * Method under test: {@link LoginServiceFactory#getLoginService(int)}
     */
    @Test
    void testGetLoginService() {
        assertThrows(IllegalArgumentException.class, () -> loginServiceFactory.getLoginService(-1));
    }
}
