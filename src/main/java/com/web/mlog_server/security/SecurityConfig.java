package com.web.mlog_server.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtProvider jwtProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(httpSecurityHttpBasicConfigurer -> {
                    httpSecurityHttpBasicConfigurer.disable();
                })
                .csrf(httpSecurityCsrfConfigurer -> {
                    httpSecurityCsrfConfigurer.disable();
                })
                .sessionManagement(httpSecuritySessionManagementConfigurer -> {
                    httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .logout(httpSecurityLogoutConfigurer -> {
                    httpSecurityLogoutConfigurer
                            .logoutUrl("/admin/logout")
                            .logoutSuccessHandler(((request, response, authentication) -> {
                                 response.setStatus(HttpStatus.OK.value());
                            }))
                            .deleteCookies("jwt")
                            .permitAll();
                })
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                            authorizationManagerRequestMatcherRegistry
                                    // 모든 사용자 가능
                                    .requestMatchers(HttpMethod.GET, "/post", "/post/**", "/project", "/project/**").permitAll()
                                    .requestMatchers(HttpMethod.POST, "/admin/login").permitAll()
                                    // 관리자 로그인한 사용자만 가능
                                    .requestMatchers(HttpMethod.GET).hasAnyRole("ADMIN")
                                    .requestMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN")
                                    .requestMatchers(HttpMethod.POST).hasAnyRole("ADMIN")
                                    .requestMatchers(HttpMethod.PUT).hasAnyRole("ADMIN")
                                    .anyRequest().authenticated();
                })
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    /**
     * 비밀번호 암호화 알고리즘 설정
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}