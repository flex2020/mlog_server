package com.web.mlog_server.mvc.post.model;

import com.web.mlog_server.mvc.admin.AdminDto;
import com.web.mlog_server.mvc.post.PostDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
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
    @Column(nullable = false)
    private String previewContent;
    @Column
    private String thumbnail;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime writingTime;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostFile> fileList = new ArrayList<>();

    @Column(nullable = false)
    private Boolean visible;

    @Builder
    public Post(Integer id, String title, String content, String previewContent, String thumbnail, LocalDateTime writingTime, List<PostFile> fileList, Boolean visible) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.previewContent = previewContent;
        this.thumbnail = thumbnail;
        this.writingTime = writingTime;
        this.fileList = fileList;
        this.visible = visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public PostDto.ListDto toListDto() {
        return PostDto.ListDto.builder()
                .id(id)
                .thumbnail(thumbnail)
                .title(title)
                .previewContent(previewContent)
                .writingTime(writingTime)
                .build();
    }

    public PostDto.DetailDto toDetailDto() {
        return PostDto.DetailDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .writingTime(writingTime)
                .build();
    }

    public AdminDto.TableDto toTableDto() {
        return AdminDto.TableDto.builder()
                .id(id)
                .title(title)
                .writingTime(writingTime)
                .visible(visible)
                .build();
    }

    public void modifyPost(PostDto.ModifyDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.previewContent = dto.getPreviewContent();
        this.visible = dto.getVisible();
    }
}
