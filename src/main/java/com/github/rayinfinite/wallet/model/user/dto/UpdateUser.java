package com.github.rayinfinite.wallet.model.user.dto;

import org.hibernate.validator.constraints.Length;

public class UpdateUser {
    @Length(max = 16, message = "昵称长度小于16位")
    private String nickName;

    private String avatar;

    @Length(max = 16, message = "手机号长度小于11位")
    private String telephone;

    @Length(max = 64, message = "邮箱长度小于64位")
    private String email;
}
