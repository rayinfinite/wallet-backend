package com.github.rayinfinite.wallet.user.factory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoginServiceFactory {
    private final UsernamePasswordLoginService usernamePasswordLoginService;
    private final PhonePasswordLoginService phonePasswordLoginService;
    private final EmailPasswordLoginService emailPasswordLoginService;

    public LoginService getLoginService(int type) {
        return switch (type) {
            case 0 -> usernamePasswordLoginService;
            case 1 -> phonePasswordLoginService;
            case 2 -> emailPasswordLoginService;
            default -> throw new IllegalArgumentException("Unsupported login type");
        };
    }
}
