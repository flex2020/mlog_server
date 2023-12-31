package com.web.mlog_server.mvc.project;

import com.web.mlog_server.common.FileUtil;
import com.web.mlog_server.mvc.project.model.Project;
import com.web.mlog_server.mvc.project.model.ProjectFile;
import com.web.mlog_server.mvc.project.model.ProjectFileRepository;
import com.web.mlog_server.mvc.project.model.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectFileRepository projectFileRepository;
    private final FileUtil fileUtil;

    /**
     * 프로젝트 목록
     * */
    public List<ProjectDto.ListDto> getProjectList() {
        return projectRepository.findAllByVisibleTrue()
                .stream()
                .map(Project::toListDto)
                .toList();
    }
    /**
     * 프로젝트 미리보기
     * */
    public List<ProjectDto.ListDto> getPreviewPost() {
        return projectRepository.findTop3ByVisibleIsTrueOrderByIdDesc()
                .stream()
                .map(Project::toListDto)
                .toList();
    }
    /**
     * 프로젝트 상세보기
     * */
    public ProjectDto.DetailDto getProjectDetail(Integer id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 프로젝트입니다."))
                .toDetailDto();
    }
    /**
     * 프로젝트 추가
     * */
    @Transactional
    public boolean addProject(ProjectDto.AddDto dto) {
        try {
            List<ProjectFile> fileList = projectFileRepository.findAllById(dto.getFileList());
            Project project = projectRepository.save(dto.toEntity());
            for (ProjectFile projectFile: fileList) {
                projectFile.setProject(project);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "프로젝트 업로드에 실패했습니다.");
        }
        return true;
    }
    public boolean deleteProject(Integer id) {
        try {
            Project project = projectRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "프로젝트가 존재하지 않습니다."));
            projectRepository.delete(project);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "프로젝트 삭제에 실패하였습니다.");
        }
        return true;
    }
    /**
     * 프로젝트 공개여부 변경
     * */
    @Transactional
    public boolean changeVisibility(Integer id) {
        try {
            Project project = projectRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 프로젝트입니다."));
            project.setVisible(!project.getVisible());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "프로젝트 삭제에 실패했습니다.");
        }
        return true;
    }
    /**
     * 프로젝트 수정
     * */
    @Transactional
    public boolean modifyProject(ProjectDto.ModifyDto dto) {
        try {
            Project project = projectRepository.findById(dto.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 프로젝트입니다."));
            project.modifyProject(dto);
            List<ProjectFile> fileList = projectFileRepository.findAllById(dto.getFileList());
            for (ProjectFile projectFile : fileList) {
                projectFile.setProject(project);
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "프로젝트 수정에 실패했습니다.");
        }
        return true;
    }
    /**
     * 프로젝트 파일 업로드
     * */
    public String uploadFile(MultipartFile file) {
        ProjectFile projectFile = fileUtil.getProjectFile(file);
        projectFileRepository.save(projectFile);
        fileUtil.uploadFile(file, projectFile.getFileName());

        return projectFile.getFileName();
    }
    /**
     * 공개여부 관계없이 모든 프로젝트 목록 가져오기
     * */
    public List<ProjectDto.AllDto> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(Project::toAllDto)
                .toList();
    }
}
