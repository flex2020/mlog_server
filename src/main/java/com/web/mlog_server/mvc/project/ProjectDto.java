package com.web.mlog_server.mvc.project;

import lombok.Builder;
import lombok.Data;

public class ProjectDto {
    @Data
    public static class ListDto {
        private Integer id;
        private String title;
        private String thumbnail;
        private String summary;
        private String skills;
        @Builder
        public ListDto(Integer id, String title, String thumbnail, String summary, String skills) {
            this.id = id;
            this.title = title;
            this.thumbnail = thumbnail;
            this.summary = summary;
            this.skills = skills;
        }
    }
    @Data
    public static class DetailDto {
        private Integer id;
        private String title;
        private String content;
        private String summary;
        private String duration;
        private String skills;
        private String learning;
    }
    @Data
    public static class AddDto {
        private String title;
        private String content;
        private String thumbnail;
        private String duration;
        private String skills;
        private String learning;
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
        private String thumbnail;
        private String duration;
        private String skills;
        private String learning;
        private Boolean visible;
    }



}
