package com.web.mlog_server.domain.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@Slf4j
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    /**
     * 페이지를 body 로부터 받아 목록을 보여줌
     *
     * */
    @GetMapping("")
    public List<PostDto.ListDto> getPostList(@RequestBody int page, Pageable pageable) {
        return postService.getPostList(pageable);
    }

    @PostMapping("")
    public boolean postAdd(@RequestBody PostDto.AddDto dto) {
        return postService.addPost(dto);
    }
}
