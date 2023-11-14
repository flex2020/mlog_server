package com.web.mlog_server.mvc.post.model;

import com.web.mlog_server.mvc.post.PostDto;
import com.web.mlog_server.mvc.series.model.PostSeries;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PostFile> fileList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_series")
    private PostSeries postSeries;

    @Column(nullable = false)
    private Boolean visible;

    @Builder
    public Post(Integer id, String title, String content, String previewContent, String thumbnail, LocalDateTime writingTime, List<PostFile> fileList, PostSeries postSeries, Boolean visible) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.previewContent = previewContent;
        this.thumbnail = thumbnail;
        this.writingTime = writingTime;
        this.fileList = fileList;
        this.postSeries = postSeries;
        this.visible = visible;
    }



    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public PostDto.PreviewDto toPreviewDto() {
        return PostDto.PreviewDto.builder()
                .id(id)
                .thumbnail(thumbnail)
                .title(title)
                .build();
    }

    public PostDto.ListDto toListDto() {
        return PostDto.ListDto.builder()
                .id(id)
                .thumbnail(thumbnail)
                .series(postSeries != null ? postSeries.getSeries() : null)
                .title(title)
                .previewContent(previewContent)
                .writingTime(writingTime)
                .build();
    }

    public PostDto.DetailDto toDetailDto() {
        return PostDto.DetailDto.builder()
                .id(id)
                .title(title)
                .series(postSeries != null ? postSeries.getSeries() : null)
                .content(content)
                .writingTime(writingTime)
                .build();
    }

    public PostDto.AllDto toAllDto() {
        return PostDto.AllDto.builder()
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

    public void updatePostSeries(PostSeries newSeries) {
        this.postSeries = newSeries;
    }
}
