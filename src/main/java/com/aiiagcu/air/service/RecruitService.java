package com.aiiagcu.air.service;

import com.aiiagcu.air.dto.RecruitDTO;
import com.aiiagcu.air.entity.Recruit;
import com.aiiagcu.air.repository.RecruitRepository;
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
public class RecruitService {
    private final RecruitRepository recruitRepository;

    public RecruitDTO save(RecruitDTO recruitDTO) {
        // startTime <= endTime인지 검증
        if(!recruitDTO.getStartAt().isBefore(recruitDTO.getEndAt())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "endAt cannot be earlier than startAt");
        }

        // 업데이트인 경우 ID가 존재하는지 검증
        if(recruitDTO.getId() != null) {
            Optional<Recruit> targetEntity = recruitRepository.findById(recruitDTO.getId());
            if (targetEntity.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Not found");
            }
        }

        Recruit recruitEntity = Recruit.toSaveEntity(recruitDTO);
        recruitRepository.save(recruitEntity);

        return RecruitDTO.toSaveDTO(recruitEntity);
    }

    public List<RecruitDTO> findAll() {
        // TODO: 접근권한 검증 구현: 관리자만 조회 가능하도록
        final List<Recruit> entitiyList = recruitRepository.findAll();
        List<RecruitDTO> dtoList = new ArrayList<>();
        for(Recruit e: entitiyList) {
            dtoList.add(RecruitDTO.toSaveDTO(e));
        }
        return dtoList;
    }

    public List<RecruitDTO> findOpenRecruits() {
        final List<Recruit> entitiyList = recruitRepository.findOpenRecruits(LocalDateTime.now());
        List<RecruitDTO> dtoList = new ArrayList<>();
        for(Recruit e: entitiyList) {
            dtoList.add(RecruitDTO.toSaveDTO(e));
        }
        return dtoList;
    }

}
