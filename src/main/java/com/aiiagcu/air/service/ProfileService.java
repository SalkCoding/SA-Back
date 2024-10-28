package com.aiiagcu.air.service;

import com.aiiagcu.air.dto.ProfileDTO;
import com.aiiagcu.air.entity.Profile;
import com.aiiagcu.air.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileDTO save(ProfileDTO profileDTO) {
        // startTime <= endTime인지 검증
        if(!profileDTO.getStartAt().isBefore(profileDTO.getEndAt())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "endAt cannot be earlier than startAt");
        }

        // 업데이트인 경우 ID가 존재하는지 검증
        if(profileDTO.getId() != null) {
            Optional<Profile> targetEntity = profileRepository.findById(profileDTO.getId());
            if (targetEntity.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Not found");
            }
        }

        Profile profileEntity = Profile.toSaveEntity(profileDTO);
        profileRepository.save(profileEntity);

        return ProfileDTO.toSaveDTO(profileEntity);
    }

    public List<ProfileDTO> findAll() {
        // TODO: 접근권한 검증 구현: 관리자만 조회 가능하도록
        final List<Profile> entitiyList = profileRepository.findAll();
        List<ProfileDTO> dtoList = new ArrayList<>();
        for(Profile e: entitiyList) {
            dtoList.add(ProfileDTO.toSaveDTO(e));
        }
        return dtoList;
    }

    public List<ProfileDTO> findOpenProfiles() {
        final List<Profile> entitiyList = profileRepository.findOpenProfiles(LocalDateTime.now());
        List<ProfileDTO> dtoList = new ArrayList<>();
        for(Profile e: entitiyList) {
            dtoList.add(ProfileDTO.toSaveDTO(e));
        }
        return dtoList;
    }

}
