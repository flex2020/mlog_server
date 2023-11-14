package com.web.mlog_server.mvc.admin;

import com.web.mlog_server.mvc.post.PostDto;
import com.web.mlog_server.security.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Slf4j
@Secured("ADMIN")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    /**
     * 로그인 요청
     * */
    @PostMapping("/login")
    public TokenInfo login(@RequestBody AdminDto.LoginDto dto) {
        return adminService.login(dto.getId(), dto.getPassword());
    }
    /**
     * 권한이 있는 사용자인지 확인하기
     * */
    @PostMapping("/auth")
    public boolean isAuth() {
        return true;
    }
}
