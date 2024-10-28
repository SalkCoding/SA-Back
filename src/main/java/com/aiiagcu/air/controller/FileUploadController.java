package com.aiiagcu.air.controller;

import com.aiiagcu.air.dto.token.*;
import com.aiiagcu.air.entity.User;
import com.aiiagcu.air.service.FileServerTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/file")
public class FileUploadController {
    private final FileServerTokenService fileServerTokenService;

    @GetMapping("/token/upload")
    List<String> getAllForm(@SessionAttribute(name = "loginUser", required = false) User user) {
        IssueTokenRequest req = new IssueTokenRequest(
                Issuer.AIIA_AIR,
                Permission.AIIAFS_UPLOAD_FILE,
                Map.of(
                        "userId", user.getId()
                )
        );

        return fileServerTokenService.issueToken(req);
    }
}
