package com.web.mlog_server.domain.post;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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
        private List<String> fileList;
    }
    @Data
    public static class AddDto {
        private String title;
        private String content;
        private String thumbnail;
        private Boolean visible;
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
