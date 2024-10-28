package com.aiiagcu.air.dto;

import com.aiiagcu.air.entity.Project;
import lombok.*;

//Data 어노테이션 -> toString(), getter,setter 메소드 자동으로 생성해줌
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private Long id;

    private String name;

    private String originalFileName;

    private String storedFileName;

    private String description;

    private String githubLink;

    private String start;

    public static ProjectDTO toSaveDTO(Project projectEntity){
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName(projectEntity.getName());
        projectDTO.setId(projectEntity.getId());
        projectDTO.setOriginalFileName(projectEntity.getOriginalFileName());
        projectDTO.setStoredFileName(projectEntity.getStoredFileName());
        projectDTO.setDescription(projectEntity.getDescription());
        projectDTO.setGithubLink(projectEntity.getGithubLink());
        projectDTO.setStart(projectEntity.getStart());

        return projectDTO;
    }

}
