package com.web.mlog_server.mvc.admin;

import lombok.Data;

public class AdminDto {
    @Data
    public static class LoginDto {
        private String id;
        private String password;
    }
}
