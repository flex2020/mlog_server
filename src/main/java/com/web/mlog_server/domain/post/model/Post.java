package com.web.mlog_server.domain.post.model;

import com.web.mlog_server.domain.post.PostDto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(
        name =  "POST_SEQ_GEN", // 시퀀스 제너레이터 이름
        sequenceName = "POST_SEQ", // 시퀀스 이름
        initialValue = 1,
        allocationSize = 1
)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQ_GEN")
    private Integer id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @Column
    private String thumbnail;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime writingTime;

    @OneToMany(mappedBy = "post")
    private List<PostFile> fileList = new ArrayList<>();

    @Column(nullable = false)
    private Boolean visible;

    public PostDto.ListDto toListDto() {
        return PostDto.ListDto.builder()
                .id(id)
                .thumbnail(thumbnail)
                .title(title)
                .writingTime(writingTime)
                .build();
    }
}
