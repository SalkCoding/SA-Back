package com.aiiagcu.air.service;

import com.aiiagcu.air.entity.ApplicationForm;
import com.aiiagcu.air.repository.ApplicationFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationFormService {
    private final ApplicationFormRepository applicationFormRepository;

    public List<ApplicationForm> findAll(){
        return applicationFormRepository.findAll();
    }

}
