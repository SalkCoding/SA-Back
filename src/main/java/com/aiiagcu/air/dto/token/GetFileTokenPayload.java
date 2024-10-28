package com.aiiagcu.air.dto.token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetFileTokenPayload extends TokenPayload {
    private String fileId;

    public GetFileTokenPayload(String fileId) {
        super(Permission.AIIAFS_GET_FILE);
        this.fileId = fileId;
    }
}