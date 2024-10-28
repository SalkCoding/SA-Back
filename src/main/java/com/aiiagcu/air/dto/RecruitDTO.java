package com.aiiagcu.air.dto;

import com.aiiagcu.air.entity.Recruit;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.aiiagcu.air.entity.Recruit}
 */
@Data
public class RecruitDTO implements Serializable {

    private Long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endAt;

    public static RecruitDTO toSaveDTO(Recruit e){
        RecruitDTO recruitDTO = new RecruitDTO();
        recruitDTO.setId(e.getId());
        recruitDTO.setName(e.getName());
        recruitDTO.setStartAt(e.getStartAt());
        recruitDTO.setEndAt(e.getEndAt());

        return recruitDTO;
    }

}
