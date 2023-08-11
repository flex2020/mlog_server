package com.web.mlog_server.mvc.admin;

import com.web.mlog_server.mvc.admin.model.AdminDto;
import com.web.mlog_server.security.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Slf4j
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody AdminDto.LoginDto dto) {
        return adminService.login(dto.getId(), dto.getPassword());
    }

    @PostMapping("/auth")
    public boolean isAuth() {
        return true;
    }
}
