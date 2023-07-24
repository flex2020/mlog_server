package com.web.mlog_server.mvc.post.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
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

    @Builder
    public PostFile(String uuid, String mimeType, String originalFileName, String fileName, Long fileSize, Post post) {
        this.uuid = uuid;
        this.mimeType = mimeType;
        this.originalFileName = originalFileName;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.post = post;
    }
}
