package com.aiiagcu.air.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginInput {
    @NotBlank(message = "학번을 입력해 주세요.")
    private String studentNum;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;
}
