package com.web.mlog_server.mvc.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        return postService.getPostList(page, pageable);
    }
    @GetMapping("/{id}")
    public PostDto.DetailDto getPostDetail(@PathVariable("id") int id) {
        return postService.getPostDetail(id);
    }

    @PostMapping("")
    public boolean postAdd(@RequestBody PostDto.AddDto dto) {
        return postService.addPost(dto);
    }

    @DeleteMapping("")
    public boolean postDelete(@RequestBody PostDto.DeleteDto dto) {
        return postService.deletePost(dto.getId());
    }

    @PutMapping("")
    public boolean postModify(@RequestBody PostDto.ModifyDto dto) {
        return postService.modifyPost(dto);
    }

    @PostMapping("/file")
    public String fileUpload(MultipartFile file) {
        return postService.uploadFile(file);
    }
}
