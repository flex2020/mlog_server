package com.web.mlog_server.mvc.project;

import com.web.mlog_server.mvc.project.model.Project;
import com.web.mlog_server.mvc.project.model.ProjectFileRepository;
import com.web.mlog_server.mvc.project.model.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectFileRepository projectFileRepository;

    public List<ProjectDto.ListDto> getPreviewPost() {
        return projectRepository.getPreviewProject()
                .stream()
                .map(Project::toListDto)
                .toList();
    }

}
