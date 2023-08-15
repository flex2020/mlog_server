package com.web.mlog_server.mvc.admin;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

public class AdminDto {
    @Data
    public static class LoginDto {
        private String id;
        private String password;
    }

    @Data
    public static class TableDto {
        private Integer id;
        private String title;
        private LocalDateTime writingTime;
        private Boolean visible;

        @Builder
        public TableDto(Integer id, String title, LocalDateTime writingTime, Boolean visible) {
            this.id = id;
            this.title = title;
            this.writingTime = writingTime;
            this.visible = visible;
        }
    }
}
