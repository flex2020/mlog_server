package com.web.mlog_server.mvc.post;

import com.web.mlog_server.mvc.post.model.Post;
import com.web.mlog_server.mvc.post.model.PostFile;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {
    @Data
    @NoArgsConstructor
    public static class PreviewDto {
        private Integer id;
        private String title;
        private String thumbnail;

        @Builder
        public PreviewDto(Integer id, String title, String thumbnail) {
            this.id = id;
            this.title = title;
            this.thumbnail = thumbnail;
        }
    }
    @Data
    @NoArgsConstructor
    public static class ListDto {
        private Integer id;
        private String series;
        private String title;
        private String previewContent;
        private String thumbnail;
        private LocalDateTime writingTime;

        @Builder
        public ListDto(Integer id, String series, String title, String previewContent, String thumbnail, LocalDateTime writingTime) {
            this.id = id;
            this.series = series;
            this.title = title;
            this.previewContent = previewContent;
            this.thumbnail = thumbnail;
            this.writingTime = writingTime;
        }
    }
    @Data
    public static class DetailDto {
        private Integer id;
        private String series;
        private String title;
        private String content;
        private LocalDateTime writingTime;

        @Builder
        public DetailDto(Integer id, String series, String title, String content, LocalDateTime writingTime) {
            this.id = id;
            this.series = series;
            this.title = title;
            this.content = content;
            this.writingTime = writingTime;
        }
    }
    @Data
    @NoArgsConstructor
    public static class AddDto {
        private String title;
        private String content;
        private String series;
        private String previewContent;
        private String thumbnail;
        private List<String> fileList;
        private Boolean visible;

        public Post toEntity(List<PostFile> fileList) {
            return Post.builder()
                    .title(title)
                    .content(content)
                    .previewContent(previewContent)
                    .writingTime(LocalDateTime.now())
                    .thumbnail(thumbnail)
                    .fileList(fileList)
                    .visible(visible)
                    .build();
        }
        @Builder
        public AddDto(String title, String content, String series, String previewContent, String thumbnail, List<String> fileList, Boolean visible) {
            this.title = title;
            this.content = content;
            this.series = series;
            this.previewContent = previewContent;
            this.thumbnail = thumbnail;
            this.fileList = fileList;
            this.visible = visible;
        }
    }
    @Data
    @NoArgsConstructor
    public static class DeleteDto {
        private Integer id;

        public DeleteDto(Integer id) {
            this.id = id;
        }
    }
    @Data
    @NoArgsConstructor
    public static class ModifyDto {
        private Integer id;
        private String title;
        private String series;
        private String content;
        private String previewContent;
        private String thumbnail;
        private List<String> fileList;
        private Boolean visible;

        @Builder
        public ModifyDto(Integer id, String title, String series, String content, String previewContent, String thumbnail, List<String> fileList, Boolean visible) {
            this.id = id;
            this.title = title;
            this.series = series;
            this.content = content;
            this.previewContent = previewContent;
            this.thumbnail = thumbnail;
            this.fileList = fileList;
            this.visible = visible;
        }
    }

    @Data
    @NoArgsConstructor
    public static class AllDto {
        private Integer id;
        private String title;
        private LocalDateTime writingTime;
        private Boolean visible;

        @Builder
        public AllDto(Integer id, String title, LocalDateTime writingTime, Boolean visible) {
            this.id = id;
            this.title = title;
            this.writingTime = writingTime;
            this.visible = visible;
        }
    }
}
