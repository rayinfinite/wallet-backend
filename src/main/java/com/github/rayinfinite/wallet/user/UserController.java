package com.github.rayinfinite.wallet.user;

import com.github.rayinfinite.wallet.model.BaseResponse;
import com.github.rayinfinite.wallet.model.user.dto.ChangePassword;
import com.github.rayinfinite.wallet.model.user.dto.UpdateUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Tag(name = "user")
public class UserController {
    private final UserService userService;

    @Operation(description = "Change Password")
    @PostMapping("/change")
    public BaseResponse<String> changePassword(ChangePassword changePassword) {
        userService.changePassword(changePassword);
        return BaseResponse.success("Change password successfully");
    }

    @Operation(description = "Update User")
    @PostMapping("/update")
    public BaseResponse<String> updateUser(UpdateUser updateUser) {
        userService.update(updateUser);
        return BaseResponse.success("Update user successfully");
    }

    @Operation(description = "Set default book")
    @GetMapping("/setDefaultBook/{id}")
    public BaseResponse<String> setDefaultBook(@PathVariable("id") long id) {
        userService.setDefaultBook(id);
        return BaseResponse.success("Set default book successfully");
    }

    @Operation(description = "Set book")
    @GetMapping("/setBook/{id}")
    public BaseResponse<String> setBook(@PathVariable("id") long id) {
        userService.setBook(id);
        return BaseResponse.success("Set default book successfully");
    }
}
