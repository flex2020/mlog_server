package com.web.mlog_server.mvc.project;

import com.web.mlog_server.mvc.project.model.Project;
import com.web.mlog_server.mvc.project.model.ProjectFileRepository;
import com.web.mlog_server.mvc.project.model.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
    public boolean addProject(ProjectDto.AddDto dto) {
        try {
            projectRepository.save(dto.toEntity());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "프로젝트 업로드에 실패했습니다.");
        }
        return true;
    }
}
