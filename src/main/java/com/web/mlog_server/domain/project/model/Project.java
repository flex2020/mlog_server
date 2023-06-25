package com.web.mlog_server.domain.project.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@SequenceGenerator(
        name =  "PROJECT_SEQ_GEN", // 시퀀스 제너레이터 이름
        sequenceName = "PROJECT_SEQ", // 시퀀스 이름
        initialValue = 1,
        allocationSize = 1
)
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJECT_SEQ_GEN")
    private int id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "LONG TEXT", nullable = false)
    private String content;

    @Column(length = 500, nullable = false)
    private String summary;

    @Column(length = 100, nullable = false)
    private String duration;

    @Column(length = 500, nullable = false)
    private String skills;

    @Column(length = 500, nullable = false)
    private String learning;

}
