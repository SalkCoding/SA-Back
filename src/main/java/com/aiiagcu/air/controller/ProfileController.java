package com.aiiagcu.air.controller;

import com.aiiagcu.air.dto.ProfileDTO;
import com.aiiagcu.air.service.ProfileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class ProfileController {
    private final ProfileService profileService;

    @PatchMapping ("/profile")
    public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO save = profileService.save(profileDTO);

        return save.getId() == null ?
                ResponseEntity.internalServerError().build():
                ResponseEntity.ok(save);
    }

    @PostMapping("/profile/{id}")
    public ResponseEntity<ProfileDTO> updateProfile(@RequestBody ProfileDTO profileDTO, @PathVariable Long id) {
        profileDTO.setId(id);
        ProfileDTO save = profileService.save(profileDTO);

        return save.getId() == null ?
                ResponseEntity.internalServerError().build():
                ResponseEntity.ok(save);
    }

    @GetMapping("/profile")
    public ResponseEntity<List<ProfileDTO>> findOpenProfile() {
        return ResponseEntity.ok(profileService.findOpenProfiles());
    }

}
