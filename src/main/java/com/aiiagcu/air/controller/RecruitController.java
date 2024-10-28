package com.aiiagcu.air.controller;

import com.aiiagcu.air.dto.RecruitDTO;
import com.aiiagcu.air.service.RecruitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecruitController {
    private final RecruitService recruitService;

    @PatchMapping ("/recruit")
    public ResponseEntity<RecruitDTO> createRecruit(@RequestBody RecruitDTO recruitDTO) {
        RecruitDTO save = recruitService.save(recruitDTO);

        return save.getId() == null ?
                ResponseEntity.internalServerError().build():
                ResponseEntity.ok(save);
    }

    @PostMapping("/recruit/{id}")
    public ResponseEntity<RecruitDTO> updateRecruit(@RequestBody RecruitDTO recruitDTO, @PathVariable Long id) {
        recruitDTO.setId(id);
        RecruitDTO save = recruitService.save(recruitDTO);

        return save.getId() == null ?
                ResponseEntity.internalServerError().build():
                ResponseEntity.ok(save);
    }

    @GetMapping("/recruit")
    public ResponseEntity<List<RecruitDTO>> findOpenRecruit() {
        return ResponseEntity.ok(recruitService.findOpenRecruits());
    }

}
