package com.github.rayinfinite.wallet.model.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class Login implements Serializable {
    @NotBlank(message = "请输入名称")
    private String username;
    @NotBlank(message = "请输入密码")
    private String password;
    private Boolean autoLogin;
    private int loginType=0;
}
