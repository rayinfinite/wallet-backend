package com.github.rayinfinite.wallet.model.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AddUser {
    @NotBlank(message = "请输入名称")
    @Length(min = 3, max = 16, message = "用户名长度为3-16位")
    private String username;

    @NotBlank(message = "请输入密码")
    @Length(min = 6, message = "密码长度大于6位")
    private String password;

    @Length(max = 16, message = "昵称长度小于16位")
    private String nickName;

    @Length(max = 16, message = "手机号长度小于11位")
    private String phone;

    @Length(max = 64, message = "邮箱长度小于64位")
    private String email;
}
