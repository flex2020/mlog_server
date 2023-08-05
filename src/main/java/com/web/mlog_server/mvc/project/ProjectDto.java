package com.web.mlog_server.mvc.project;

import com.web.mlog_server.mvc.project.model.Project;
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
        private String learning;
        private String duration;
        @Builder
        public ListDto(Integer id, String title, String thumbnail, String summary, String skills, String learning, String duration) {
            this.id = id;
            this.title = title;
            this.thumbnail = thumbnail;
            this.summary = summary;
            this.skills = skills;
            this.learning = learning;
            this.duration = duration;
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

        @Builder
        public DetailDto(Integer id, String title, String content, String summary, String duration, String skills, String learning) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.summary = summary;
            this.duration = duration;
            this.skills = skills;
            this.learning = learning;
        }
    }
    @Data
    public static class AddDto {
        private String title;
        private String content;
        private String thumbnail;
        private String duration;
        private String skills;
        private String learning;
        private String summary;
        private Boolean visible;

        public Project toEntity() {
            return Project.builder()
                    .title(title)
                    .content(content)
                    .thumbnail(thumbnail)
                    .summary(summary)
                    .duration(duration)
                    .skills(skills)
                    .learning(learning)
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
        private String thumbnail;
        private String duration;
        private String skills;
        private String learning;
        private Boolean visible;
    }



}
