package com.web.mlog_server.mvc.project.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectFile {
    @Id @Column(length = 50)
    private String uuid;

    @Column(length = 100, nullable = false)
    private String mimeType;

    @Column(length = 500, nullable = false)
    private String originalFileName;

    @Column(length = 100, nullable = false)
    private String fileName;

    @Column
    private Long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
    @Builder
    public ProjectFile(String uuid, String mimeType, String originalFileName, String fileName, Long fileSize, Project project) {
        this.uuid = uuid;
        this.mimeType = mimeType;
        this.originalFileName = originalFileName;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.project = project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
