package com.web.mlog_server.mvc.post;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@AutoConfigureMockMvc(addFilters = false)
class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    /**
     * 컨트롤러가 의존하는 서비스를 Mock 으로 구현
     */
    @MockBean
    PostService postService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("포스트 전체 목록 불러오기")
    void 포스트목록() throws Exception {
        List<PostDto.ListDto> list = new ArrayList<>();
        PostDto.ListDto dto = PostDto.ListDto.builder()
                .id(1)
                .title("포스트 목록 제목 1")
                .previewContent("포스트 목록 미리보기 1")
                .thumbnail("썸네일 경로 1")
                .writingTime(LocalDateTime.now())
                .build();
        list.add(dto);

        given(postService.getPostList()).willReturn(list);

        mockMvc.perform(get("/api/post")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].title").exists())
                .andExpect(jsonPath("$[0].previewContent").exists())
                .andExpect(jsonPath("$[0].thumbnail").exists())
                .andExpect(jsonPath("$[0].writingTime").exists());
    }
}