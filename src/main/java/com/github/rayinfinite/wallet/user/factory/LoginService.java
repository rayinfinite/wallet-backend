package com.github.rayinfinite.wallet.user.factory;

import com.github.rayinfinite.wallet.model.user.User;
import com.github.rayinfinite.wallet.model.user.dto.Login;

public interface LoginService {
    User login(Login login);
}
