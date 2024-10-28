package com.aiiagcu.air.service;

import com.aiiagcu.air.dto.ProjectDTO;
import com.aiiagcu.air.entity.Project;
import com.aiiagcu.air.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    // DTO를 Entity로 변환하여 레퍼지토리에 저장.
    public ProjectDTO save(ProjectDTO projectDTO){
        Project projectEntity = Project.toSaveEntity(projectDTO);

        projectRepository.save(projectEntity);

        return ProjectDTO.toSaveDTO(projectEntity);
    }

    // 레퍼지토리에 있는 모든 프로젝트를 DTO형태로 가져온다.
    public List<ProjectDTO> findAll() {
        List<Project> postEntityList = projectRepository.findAll();
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        for(Project projectEntity: postEntityList) {
            projectDTOList.add(ProjectDTO.toSaveDTO(projectEntity));
        }
        return projectDTOList;
    }

    // id를 통하여 해당 id를 가진 프로젝트 DTO를 불러온다.
    public ProjectDTO findById(Long id){
        Project findProject = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + id));

        return ProjectDTO.toSaveDTO(findProject);
    }

    // 기존에 있는 프로젝트를 레퍼지토리에서 검색하여 바뀐 값으로 업데이트하여 레퍼지토리에 저장한다.
    public ProjectDTO update(ProjectDTO projectDTO){
        // 프로젝트 ID를 기반으로 업데이트할 프로젝트를 찾아옵니다.
        Project updateProject = Project.toUpdateEntity(projectDTO);

        // 업데이트된 프로젝트를 저장합니다.
        projectRepository.save(updateProject);

        return ProjectDTO.toSaveDTO(updateProject);

    }

    public void delete(ProjectDTO projectDTO){
        Project deleteProject = projectRepository.findById(projectDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + projectDTO.getId()));

        projectRepository.delete(deleteProject);
    }

}
