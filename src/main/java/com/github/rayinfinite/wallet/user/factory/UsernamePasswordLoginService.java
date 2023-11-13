package com.github.rayinfinite.wallet.user.factory;

import com.github.rayinfinite.wallet.model.user.User;
import com.github.rayinfinite.wallet.model.user.dto.Login;
import com.github.rayinfinite.wallet.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsernamePasswordLoginService implements LoginService {
    private final UserRepository userRepository;

    @Override
    public User login(Login login) {
        return userRepository.findOneByUsername(login.getUsername()).orElse(null);
    }
}
