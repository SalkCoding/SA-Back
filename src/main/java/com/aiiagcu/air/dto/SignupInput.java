package com.aiiagcu.air.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupInput {
    @NotBlank(message = "이름을 입력해 주세요.")
    private String name; //이름
    @NotBlank(message = "학번을 입력해 주세요.")
    private String studentNum;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;
//    @Email(regexp = "\\w+@gachon.ac.kr", message = "가천대학교 gmail을 사용해야 합니다")
//    private String gachonEmail;
}
