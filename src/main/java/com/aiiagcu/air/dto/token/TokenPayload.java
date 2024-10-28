package com.aiiagcu.air.dto.token;

import lombok.Data;

@Data
public class TokenPayload {
    private Permission permission;

    public TokenPayload(Permission permission) {
        this.permission = permission;
    }
}