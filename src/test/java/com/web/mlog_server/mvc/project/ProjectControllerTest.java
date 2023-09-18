package com.web.mlog_server.mvc.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.mlog_server.mvc.post.PostController;
import com.web.mlog_server.mvc.post.PostDto;
import com.web.mlog_server.mvc.post.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProjectControllerTest {
    @Autowired
    MockMvc mockMvc;

    /**
     * 컨트롤러가 의존하는 서비스를 Mock 으로 구현
     */
    @MockBean
    ProjectService projectService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("프로젝트 전체 목록 불러오기")
    void 프로젝트_목록() throws Exception {
        List<ProjectDto.ListDto> list = new ArrayList<>();
        ProjectDto.ListDto dto = ProjectDto.ListDto.builder()
                .id(1)
                .title("프로젝트 제목")
                .thumbnail("썸네일 경로")
                .summary("프로젝트 요약")
                .learning("프로젝트에서 배운 점")
                .skills("프로젝트에서 사용한 기술들")
                .duration("프로젝트 진행기간")
                .writingTime(LocalDateTime.now())
                .build();
        list.add(dto);

        given(projectService.getProjectList()).willReturn(list);

        mockMvc.perform(get("/api/project")
        )
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("프로젝트 상세보기")
    void 프로젝트_상세보기() throws Exception {
        ProjectDto.DetailDto dto = ProjectDto.DetailDto.builder()
                .id(1)
                .title("프로젝트 제목")
                .content("프로젝트 본문 내용")
                .summary("프로젝트 요약")
                .learning("프로젝트에서 배운 점")
                .skills("프로젝트에서 사용한 기술들")
                .duration("프로젝트 진행기간")
                .build();

        given(projectService.getProjectDetail(1)).willReturn(dto);

        mockMvc.perform(get("/api/project/1")
                )
                .andExpect(status().isOk());
    }

}