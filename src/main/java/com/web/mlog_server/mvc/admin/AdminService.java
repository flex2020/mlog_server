package com.web.mlog_server.mvc.admin;

import com.web.mlog_server.mvc.admin.model.Admin;
import com.web.mlog_server.mvc.admin.model.AdminRepository;
import com.web.mlog_server.mvc.post.PostDto;
import com.web.mlog_server.mvc.post.model.Post;
import com.web.mlog_server.mvc.post.model.PostRepository;
import com.web.mlog_server.mvc.project.model.Project;
import com.web.mlog_server.mvc.project.model.ProjectRepository;
import com.web.mlog_server.security.JwtProvider;
import com.web.mlog_server.security.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PostRepository postRepository;
    private final ProjectRepository projectRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;

    @Transactional
    public TokenInfo login(String id, String password) {
        //--- 1. ID, 암호화된 PW를 기반으로 Authentication 객체 생성
        // 이 때 authentication 은 인증 여부를 확인하는 authenticated 값이 false 로 설정되어있음.
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(id, password);
        //--- 2. 실제 검증 과정 (사용자 비밀번호 확인)
        // authenticate 함수가 실행되면, CustomUserDetailsService 에서 구현한 loadUserByUsername 함수가 자동으로 실행 됨.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "입력된 정보와 일치하는 사용자가 없습니다."));
        //--- 3. 인증 정보를 기반으로 JWT 생성
        TokenInfo tokenInfo = jwtProvider.generateToken(authentication);
        return tokenInfo;
    }

    public List<AdminDto.TableDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(Post::toTableDto)
                .toList();
    }
    public List<AdminDto.TableDto> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(Project::toTableDto)
                .toList();
    }
    public PostDto.DetailDto getPostDetail(Integer id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 포스트입니다."))
                .toDetailDto();
    }
}
