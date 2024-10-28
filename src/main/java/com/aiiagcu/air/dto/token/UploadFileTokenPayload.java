package com.aiiagcu.air.dto.token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadFileTokenPayload extends TokenPayload {
    private Long userId;

    public UploadFileTokenPayload(Long userId) {
        super(Permission.AIIAFS_UPLOAD_FILE);
        this.userId = userId;
    }
}

