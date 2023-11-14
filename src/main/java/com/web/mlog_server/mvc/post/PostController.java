package com.web.mlog_server.mvc.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Slf4j
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    /**
     * (사용자) 공개된 포스트 목록
     * */
    @GetMapping("")
    public List<PostDto.ListDto> getPostList() {
        return postService.getPostList();
    }
    /**
     * (사용자) 포스트 미리보기
     * */
    @GetMapping("/preview")
    public List<PostDto.ListDto> getPreviewPost() {
        return postService.getPreviewPost();
    }
    /**
     * (사용자) 포스트 상세보기
     * */
    @GetMapping("/{id}")
    public PostDto.DetailDto getPostDetail(@PathVariable("id") int id) {
        return postService.getPostDetail(id);
    }
    /**
     * (관리자) 포스트 추가
     * */
    @PostMapping("")
    public boolean postAdd(@RequestBody PostDto.AddDto dto) {
        return postService.addPost(dto);
    }
    /**
     * (관리자) 포스트 삭제
     * */
    @DeleteMapping("/{id}")
    public boolean postDelete(@PathVariable("id") Integer id) {
        return postService.deletePost(id);
    }
    /**
     * (관리자) 포스트 공개여부 변경
     * */
    @PostMapping("/{id}/visibility")
    public boolean changeVisibility(@PathVariable("id") Integer id) {
        return postService.changeVisibility(id);
    }
    /**
     * (관리자) 포스트 수정
     * */
    @PutMapping("")
    public boolean postModify(@RequestBody PostDto.ModifyDto dto) {
        return postService.modifyPost(dto);
    }
    /**
     * (관리자) 포스트 파일 업로드
     * */
    @PostMapping("/file")
    public String fileUpload(MultipartFile file) {
        return postService.uploadFile(file);
    }
    /**
     * (관리자) 공개여부 관계 없이 모든 포스트 목록 불러오기
     * */
    @GetMapping("/all")
    public List<PostDto.AllDto> getAllPosts() {
        return postService.getAllPosts();
    }

}
