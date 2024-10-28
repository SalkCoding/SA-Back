package com.aiiagcu.air.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class EmailInput {
    @Email(regexp = "\\w+@gachon.ac.kr", message = "가천대학교 gmail을 사용해야 합니다")
    private String gachonEmail;
}
