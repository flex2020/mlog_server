package com.web.mlog_server.domain.project;

import com.web.mlog_server.domain.project.model.ProjectFileRepository;
import com.web.mlog_server.domain.project.model.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectFileRepository projectFileRepository;


}
