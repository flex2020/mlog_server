package com.web.mlog_server.mvc.project;

import com.web.mlog_server.mvc.project.model.Project;
import com.web.mlog_server.mvc.project.model.ProjectFileRepository;
import com.web.mlog_server.mvc.project.model.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectFileRepository projectFileRepository;

    public List<ProjectDto.ListDto> getPreviewPost() {
        return projectRepository.findTop3ByOrderByIdDesc()
                .stream()
                .map(Project::toListDto)
                .toList();
    }
    public List<ProjectDto.ListDto> getProjectList() {
        return projectRepository.findAllByVisibleTrue()
                .stream()
                .map(Project::toListDto)
                .toList();
    }
    public ProjectDto.DetailDto getProjectDetail(Integer id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 프로젝트입니다."))
                .toDetailDto();
    }
    public boolean addProject(ProjectDto.AddDto dto) {
        try {
            projectRepository.save(dto.toEntity());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "프로젝트 업로드에 실패했습니다.");
        }
        return true;
    }
    @Transactional
    public boolean deleteProject(ProjectDto.DeleteDto dto) {
        try {
            Project project = projectRepository.findById(dto.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 프로젝트입니다."));
            project.setVisible(false);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "프로젝트 삭제에 실패했습니다.");
        }
        return true;
    }
    @Transactional
    public boolean modifyProject(ProjectDto.ModifyDto dto) {
        try {
            Project project = projectRepository.findById(dto.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 프로젝트입니다."));
            project.modifyProject(dto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "프로젝트 수정에 실패했습니다.");
        }
        return true;
    }
}
