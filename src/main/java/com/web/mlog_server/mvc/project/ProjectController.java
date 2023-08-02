package com.web.mlog_server.mvc.project;

import com.web.mlog_server.mvc.project.model.Project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@Slf4j
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/preview")
    public List<ProjectDto.ListDto> getPreviewProject() {
        return projectService.getPreviewPost();
    }
    @GetMapping("")
    public List<ProjectDto.ListDto> projectList() {
        return projectService.getProjectList();
    }
    @PostMapping("")
    public boolean projectAdd(@RequestBody ProjectDto.AddDto dto) {
        return projectService.addProject(dto);
    }
}
