package com.aiiagcu.air.dto.token;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Token {
    private LocalDateTime nbf;
    private LocalDateTime iat;
    private LocalDateTime exp;
    private Issuer iss;
    private TokenPayload payload;

    public Token(LocalDateTime nbf, LocalDateTime iat, LocalDateTime exp, Issuer iss, TokenPayload payload) {
        this.nbf = nbf;
        this.iat = iat;
        this.exp = exp;
        this.iss = iss;
        this.payload = payload;
    }
}
