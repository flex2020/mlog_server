package com.web.mlog_server.mvc.project.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(
        name =  "PROJECT_SEQ_GEN", // 시퀀스 제너레이터 이름
        sequenceName = "PROJECT_SEQ", // 시퀀스 이름
        initialValue = 1,
        allocationSize = 1
)
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

    @OneToMany(mappedBy = "project")
    private List<ProjectFile> fileList = new ArrayList<>();

    @Column(nullable = false)
    private Boolean visible;
}
