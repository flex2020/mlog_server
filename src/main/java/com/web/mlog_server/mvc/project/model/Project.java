package com.web.mlog_server.mvc.project.model;

import com.web.mlog_server.mvc.admin.AdminDto;
import com.web.mlog_server.mvc.project.ProjectDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;
    private String thumbnail;

    @Column(length = 500, nullable = false)
    private String summary;

    @Column(length = 100, nullable = false)
    private String duration;

    @Column(length = 500, nullable = false)
    private String skills;

    @Column(length = 500, nullable = false)
    private String learning;

    @Column(nullable = false)
    private LocalDateTime writingTime;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectFile> fileList = new ArrayList<>();

    @Column(nullable = false)
    private Boolean visible;

    @Builder
    public Project(Integer id, String title, String content, String thumbnail, String summary, String duration, String skills, String learning, LocalDateTime writingTime, List<ProjectFile> fileList, Boolean visible) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.summary = summary;
        this.duration = duration;
        this.skills = skills;
        this.learning = learning;
        this.writingTime = writingTime;
        this.fileList = fileList;
        this.visible = visible;
    }


    public ProjectDto.ListDto toListDto() {
        return ProjectDto.ListDto.builder()
                .id(id)
                .title(title)
                .duration(duration)
                .skills(skills)
                .summary(summary)
                .thumbnail(thumbnail)
                .learning(learning)
                .writingTime(writingTime)
                .build();
    }
    public ProjectDto.DetailDto toDetailDto() {
        return ProjectDto.DetailDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .summary(summary)
                .duration(duration)
                .skills(skills)
                .learning(learning)
                .build();
    }

    public ProjectDto.AllDto toAllDto() {
        return ProjectDto.AllDto.builder()
                .id(id)
                .title(title)
                .writingTime(writingTime)
                .visible(visible)
                .build();
    }

    public void modifyProject(ProjectDto.ModifyDto dto) {
        title = dto.getTitle();
        content = dto.getContent();
        thumbnail = dto.getThumbnail();
        summary = dto.getSummary();
        duration = dto.getDuration();
        skills = dto.getSkills();
        learning = dto.getLearning();
        visible = dto.getVisible();
    }
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
