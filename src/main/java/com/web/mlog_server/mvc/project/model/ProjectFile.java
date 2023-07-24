package com.web.mlog_server.mvc.project.model;

import jakarta.persistence.*;

@Entity
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

}
