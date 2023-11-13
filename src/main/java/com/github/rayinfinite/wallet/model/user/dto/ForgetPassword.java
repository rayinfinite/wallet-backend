package com.github.rayinfinite.wallet.model.user.dto;

import com.github.rayinfinite.wallet.model.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ForgetPassword {
    @NotBlank(message = "请输入密码")
    @Length(min = 6, message = "密码长度大于6位")
    private String newPassword;

    @NotBlank(message = "请输入用户名")
    private String username;

    private String nickName;
    private String phone;
    private String email;

    public boolean validate(User user) {
        return user.getNickName().equals(nickName) &&
                user.getPhone().equals(phone) &&
                user.getEmail().equals(email);
    }
}
