package com.github.rayinfinite.wallet.model.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChangePassword {
    @NotBlank(message = "请输入新密码")
    @Length(min = 6, message = "密码长度大于6位")
    private String newPassword;

    @NotBlank(message = "请输入现密码")
    private String oldPassword;
}
