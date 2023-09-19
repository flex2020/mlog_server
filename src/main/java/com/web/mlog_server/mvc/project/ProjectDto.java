package com.web.mlog_server.mvc.project;

import com.web.mlog_server.mvc.project.model.Project;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
        private LocalDateTime writingTime;
        @Builder
        public ListDto(Integer id, String title, String thumbnail, String summary, String skills, String learning, String duration, LocalDateTime writingTime) {
            this.id = id;
            this.title = title;
            this.thumbnail = thumbnail;
            this.summary = summary;
            this.skills = skills;
            this.learning = learning;
            this.duration = duration;
            this.writingTime = writingTime;
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
    @NoArgsConstructor
    public static class AddDto {
        private String title;
        private String content;
        private String thumbnail;
        private String duration;
        private String skills;
        private String learning;
        private String summary;
        private Boolean visible;

        @Builder
        public AddDto(String title, String content, String thumbnail, String duration, String skills, String learning, String summary, Boolean visible) {
            this.title = title;
            this.content = content;
            this.thumbnail = thumbnail;
            this.duration = duration;
            this.skills = skills;
            this.learning = learning;
            this.summary = summary;
            this.visible = visible;
        }

        public Project toEntity() {
            return Project.builder()
                    .title(title)
                    .content(content)
                    .thumbnail(thumbnail)
                    .summary(summary)
                    .duration(duration)
                    .skills(skills)
                    .learning(learning)
                    .writingTime(LocalDateTime.now())
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
        private String summary;
        private String thumbnail;
        private String duration;
        private String skills;
        private String learning;
        private Boolean visible;
    }



}
