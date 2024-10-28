package com.aiiagcu.air.dto.token;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class IssueTokenRequest {
    private Issuer issuer;
    private Permission permission;
    private Map<String, Object> payload;

    public IssueTokenRequest(Issuer issuer, Permission permission, Map<String, Object> payload) {
        this.issuer = issuer;
        this.permission = permission;
        this.payload = payload;
    }
}
