package com.github.rayinfinite.wallet.user;

import com.github.rayinfinite.wallet.model.BaseResponse;
import com.github.rayinfinite.wallet.model.user.User;
import com.github.rayinfinite.wallet.model.user.dto.ForgetPassword;
import com.github.rayinfinite.wallet.model.user.dto.Login;
import com.github.rayinfinite.wallet.model.user.dto.AddUser;
import com.github.rayinfinite.wallet.security.JwtConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("")
@Tag(name = "login")
public class LoginController {
    private final UserService userService;
    private final JwtConfig jwtConfig;
    private final HttpServletRequest request;

    @Operation(description = "Login")
    @PostMapping("/login")
    public BaseResponse<String> login(@Validated @RequestBody Login login) {
        User user = userService.login(login);
        String token = jwtConfig.createToken(user.getId());
        return BaseResponse.success(token);
    }

    @Operation(description = "Logout")
    @PostMapping("/logout")
    public BaseResponse<String> logout() {
        request.getSession().removeAttribute("accessToken");
        return BaseResponse.success("Logout successfully");
    }

    @Operation(description = "Register")
    @PostMapping("/login/register")
    public BaseResponse<String> register(@Validated @RequestBody AddUser addUser) {
        User user = userService.register(addUser);
        if (user == null) {
            return BaseResponse.error("User already exists");
        }
        String token = jwtConfig.createToken(user.getId());
        return BaseResponse.success(token);
    }

    @Operation(description = "Forget Password")
    @PostMapping("/login/forget")
    public BaseResponse<String> forgetPassword(@Validated @RequestBody ForgetPassword forgetPassword) {
        userService.forgetPassword(forgetPassword);
        return BaseResponse.success("Reset password successfully");
    }

    @Operation(description = "Current User")
    @PostMapping("/login/current")
    public BaseResponse<User> CurrentUser() {
        User user = userService.CurrentUser();
        return BaseResponse.success(user);
    }
}
