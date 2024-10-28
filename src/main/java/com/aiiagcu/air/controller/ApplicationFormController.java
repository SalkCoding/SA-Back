package com.aiiagcu.air.controller;

import com.aiiagcu.air.entity.ApplicationForm;
import com.aiiagcu.air.service.ApplicationFormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApplicationFormController {
    private final ApplicationFormService applicationFormService;

    @GetMapping("/getForm")
    List<ApplicationForm> getAllForm() {
        return applicationFormService.findAll();
    }
}
