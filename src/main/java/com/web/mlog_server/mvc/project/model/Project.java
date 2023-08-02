package com.web.mlog_server.mvc.project.model;

import com.web.mlog_server.mvc.project.ProjectDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(
        name =  "PROJECT_SEQ_GEN", // 시퀀스 제너레이터 이름
        sequenceName = "PROJECT_SEQ", // 시퀀스 이름
        initialValue = 1,
        allocationSize = 1
)
@NoArgsConstructor
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJECT_SEQ_GEN")
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

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectFile> fileList = new ArrayList<>();

    @Column(nullable = false)
    private Boolean visible;

    @Builder
    public Project(Integer id, String title, String content, String thumbnail, String summary, String duration, String skills, String learning, List<ProjectFile> fileList, Boolean visible) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.summary = summary;
        this.duration = duration;
        this.skills = skills;
        this.learning = learning;
        this.fileList = fileList;
        this.visible = visible;
    }

    public ProjectDto.ListDto toListDto() {
        return ProjectDto.ListDto.builder()
                .id(id)
                .title(title)
                .skills(skills)
                .summary(summary)
                .thumbnail(thumbnail)
                .build();
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
