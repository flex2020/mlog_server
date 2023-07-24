package com.web.mlog_server.mvc.post;

import com.web.mlog_server.mvc.post.model.Post;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

public class PostDto {
    @Data
    public static class ListDto {
        private Integer id;
        private String title;
        private String thumbnail;
        private LocalDateTime writingTime;

        @Builder
        public ListDto(Integer id, String title, String thumbnail, LocalDateTime writingTime) {
            this.id = id;
            this.title = title;
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
        private String thumbnail;
        private Boolean visible;

        public Post toEntity() {
            return Post.builder()
                    .title(title)
                    .content(content)
                    .writingTime(LocalDateTime.now())
                    .thumbnail(thumbnail)
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
        private Boolean visible;
    }
}
