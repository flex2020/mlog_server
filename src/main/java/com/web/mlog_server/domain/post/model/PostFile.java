package com.web.mlog_server.domain.post.model;

import jakarta.persistence.*;

@Entity
public class PostFile {
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
    @JoinColumn(name = "post_id")
    private Post post;

}
