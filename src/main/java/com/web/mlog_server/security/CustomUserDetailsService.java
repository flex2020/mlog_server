package com.web.mlog_server.security;

import com.web.mlog_server.mvc.admin.model.Admin;
import com.web.mlog_server.mvc.admin.model.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepository.findById(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"입력된 정보와 일치하는 사용자가 없습니다."));
    }

    private UserDetails createUserDetails(Admin admin) {
        return User.builder()
                .username(admin.getUsername())
                .password(passwordEncoder.encode(admin.getPassword()))
                .roles(admin.getRoles().toArray(new String[0]))
                .build();
    }
}