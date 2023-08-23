package com.web.mlog_server.mvc.post;

import com.web.mlog_server.mvc.post.model.Post;
import com.web.mlog_server.mvc.post.model.PostFile;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {
    @Data
    public static class ListDto {
        private Integer id;
        private String title;
        private String previewContent;
        private String thumbnail;
        private LocalDateTime writingTime;

        @Builder
        public ListDto(Integer id, String title, String previewContent, String thumbnail, LocalDateTime writingTime) {
            this.id = id;
            this.title = title;
            this.previewContent = previewContent;
            this.thumbnail = thumbnail;
            this.writingTime = writingTime;
        }
    }
    @Data
    public static class DetailDto {
        private Integer id;
        private String title;
        private String content;
        private LocalDateTime writingTime;

        @Builder
        public DetailDto(Integer id, String title, String content, LocalDateTime writingTime) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.writingTime = writingTime;
        }
    }
    @Data
    public static class AddDto {
        private String title;
        private String content;
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
    }
    @Data
    public static class DeleteDto {
        private Integer id;
    }
    @Data
    public static class ModifyDto {
        private Integer id;
        private String title;
        private String content;
        private String previewContent;
        private String thumbnail;
        private List<String> fileList;
        private Boolean visible;
    }
}
