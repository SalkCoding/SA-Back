package com.aiiagcu.air.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FreshmanSignupInput {
    @NotBlank(message = "이름을 입력해 주세요.")
    private String name; //이름
    @NotBlank(message = "학번을 입력해 주세요.")
    private String studentNum;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;
}
