package com.aiiagcu.air.dto;

import com.aiiagcu.air.entity.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Profile}
 */
@Data
public class ProfileDTO implements Serializable {

    private Long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endAt;

    public static ProfileDTO toSaveDTO(Profile e){
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(e.getId());
        profileDTO.setName(e.getName());
        profileDTO.setStartAt(e.getStartAt());
        profileDTO.setEndAt(e.getEndAt());

        return profileDTO;
    }

}
