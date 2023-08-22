package com.web.mlog_server.mvc.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@Slf4j
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("")
    public List<PostDto.ListDto> getPostList() {
        return postService.getPostList();
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
    public boolean changeVisibility(@RequestBody PostDto.DeleteDto dto) {
        return postService.deletePost(dto.getId());
    }

    @PutMapping("")
    public boolean postModify(@RequestBody PostDto.ModifyDto dto) {
        return postService.modifyPost(dto);
    }

    @GetMapping("/preview")
    public List<PostDto.ListDto> getPreviewPost() {
        return postService.getPreviewPost();
    }

    @PostMapping("/file")
    public String fileUpload(MultipartFile file) {
        return postService.uploadFile(file);
    }
}
