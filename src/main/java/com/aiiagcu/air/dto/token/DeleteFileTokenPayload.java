package com.aiiagcu.air.dto.token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteFileTokenPayload extends TokenPayload {
    private String fileId;
    private Long userId;

    public DeleteFileTokenPayload(String fileId, Long userId) {
        super(Permission.AIIAFS_DELETE_FILE);
        this.fileId = fileId;
        this.userId = userId;
    }
}