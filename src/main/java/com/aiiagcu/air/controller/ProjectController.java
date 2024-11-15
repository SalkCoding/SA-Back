package com.aiiagcu.air.controller;

import com.aiiagcu.air.dto.ProjectDTO;
import com.aiiagcu.air.service.ProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class ProjectController {

    private final ProjectService projectService;

    // 프로젝트 추가
    @PostMapping("/project/create")
    public ResponseEntity<ProjectDTO> postProject(@RequestBody ProjectDTO projectDTO){
        System.out.println("projectDTO = " + projectDTO);
        ProjectDTO save = projectService.save(projectDTO);

        System.out.println(save.getId());
        return save.getId() == null ?
                ResponseEntity.internalServerError().build():
                ResponseEntity.ok(save);
    }

    // 전체 프로젝트를 가져오는 컨트롤러
    @GetMapping("/project/all")
    public ResponseEntity<List<ProjectDTO>> projectList(){
        List<ProjectDTO> projects = projectService.findAll();
        System.out.println("projects = " + projects);

        return ResponseEntity.ok(projects);
    }

    // 프로젝트를 수정하는 컨트롤러 <- api 명세서에서는 patch 로 되어있음 확인 필요
    @PatchMapping("/project/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO projectDTO, @PathVariable Long id){
        projectDTO.setId(id);
        ProjectDTO update = projectService.update(projectDTO);

        System.out.println(update.toString());

        return update.getId() == null?
                ResponseEntity.internalServerError().build():
                ResponseEntity.ok(update);
    }

    // 프로젝트를 삭제하는 컨트롤러
    @DeleteMapping("/project/{id}")
    public ResponseEntity<ProjectDTO> deleteProject(@PathVariable Long id){
        ProjectDTO deleteDTO = projectService.findById(id);

        projectService.delete(deleteDTO);

        return deleteDTO.getId() == null ?
                ResponseEntity.badRequest().build():
                ResponseEntity.ok(deleteDTO);
    }

}

