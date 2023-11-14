package com.web.mlog_server.mvc.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@Slf4j
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    /**
     * (사용자) 공개된 프로젝트 목록
     * */
    @GetMapping("")
    public List<ProjectDto.ListDto> projectList() {
        return projectService.getProjectList();
    }
    /**
     * (사용자) 프로젝트 미리보기
     * */
    @GetMapping("/preview")
    public List<ProjectDto.ListDto> getPreviewProject() {
        return projectService.getPreviewPost();
    }
    /**
     * (사용자) 프로젝트 자세히 보기
     * */
    @GetMapping("/{id}")
    public ProjectDto.DetailDto projectDetail(@PathVariable("id") Integer id) {
        return projectService.getProjectDetail(id);
    }
    /**
     * (관리자) 프로젝트 추가
     * */
    @PostMapping("")
    public boolean projectAdd(@RequestBody ProjectDto.AddDto dto) {
        return projectService.addProject(dto);
    }
    @DeleteMapping("/{id}")
    public boolean projectDelete(@PathVariable("id") Integer id) {
        return projectService.deleteProject(id);
    }
    /**
     * (관리자) 프로젝트 공개여부 변경
     * */
    @PostMapping("/{id}/visibility")
    public boolean changeVisibility(@PathVariable("id") Integer id) {
        return projectService.changeVisibility(id);
    }
    /**
     * (관리자) 프로젝트 수정
     * */
    @PutMapping("")
    public boolean projectModify(@RequestBody ProjectDto.ModifyDto dto) {
        return projectService.modifyProject(dto);
    }
    /**
     * (관리자) 프로젝트 작성 시 파일 업로드
     * */
    @PostMapping("/file")
    public String fileUpload(MultipartFile file) {
        return projectService.uploadFile(file);
    }
    /**
     * (관리자) 공개여부 관계 없이 모든 프로젝트 목록 불러오기
     * */
    @GetMapping("/all")
    public List<ProjectDto.AllDto> getAllProjects() {
        return projectService.getAllProjects();
    }
}
