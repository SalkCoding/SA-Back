package com.aiiagcu.air.entity;

import com.aiiagcu.air.dto.ProjectDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @Column
    private String description;

    @Column
    private String githubLink;

    @Column
    private String start;


    public static Project toSaveEntity(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setId(projectDTO.getId());
        project.setName(projectDTO.getName());
        project.setOriginalFileName(projectDTO.getOriginalFileName());
        project.setStoredFileName(projectDTO.getStoredFileName());
        project.setDescription(projectDTO.getDescription());
        project.setGithubLink(projectDTO.getGithubLink());
        project.setStart(projectDTO.getStart());

        return project;
    }

    public static Project toUpdateEntity(ProjectDTO projectDTO){
        Project project = new Project();
        project.setName(project.getName());
        project.setOriginalFileName(projectDTO.getOriginalFileName());
        project.setStoredFileName(projectDTO.getStoredFileName());
        project.setDescription(projectDTO.getDescription());
        project.setGithubLink(projectDTO.getGithubLink());
        project.setStart(projectDTO.getStart());

        return project;
    }

}
