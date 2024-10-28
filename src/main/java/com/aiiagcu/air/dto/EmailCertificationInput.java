package com.aiiagcu.air.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailCertificationInput {
    @Email(regexp = "\\w+@gachon.ac.kr", message = "가천대학교 gmail을 사용해야 합니다")
    private String gachonEmail;
    @NotBlank(message = "인증번호를 입력해주세요")
    private String certificationNumber;
}
