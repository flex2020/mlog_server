package com.web.mlog_server.domain.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
@Slf4j
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
}
