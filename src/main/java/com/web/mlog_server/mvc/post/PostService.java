package com.web.mlog_server.mvc.post;

import com.web.mlog_server.common.FileUtil;
import com.web.mlog_server.mvc.post.model.Post;
import com.web.mlog_server.mvc.post.model.PostFile;
import com.web.mlog_server.mvc.post.model.PostFileRepository;
import com.web.mlog_server.mvc.post.model.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostFileRepository postFileRepository;
    private final FileUtil fileUtil;

    public List<PostDto.ListDto> getPreviewPost() {
        return postRepository.findTop3ByVisibleIsTrueOrderByIdDesc()
                .stream()
                .map(Post::toListDto)
                .toList();
    }

    public List<PostDto.ListDto> getPostList() {
        return postRepository.findAllByVisibleIsTrueOrderByIdDesc()
                .stream().map(Post::toListDto)
                .toList();
    }
    public PostDto.DetailDto getPostDetail(int id) {
        return postRepository.findByIdAndVisibleIsTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 포스트입니다."))
                .toDetailDto();
    }
    public boolean addPost(PostDto.AddDto dto) {
        try {
            postRepository.save(dto.toEntity());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "포스트 업로드에 실패하였습니다.");
        }
        return true;
    }
    @Transactional
    public boolean deletePost(int id) {
        Post post = postRepository.findByIdAndVisibleIsTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 포스트입니다."));
        try {
            post.setVisible(!post.getVisible());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "포스트 수정에 실패했습니다.");
        }
        return true;
    }

    @Transactional
    public boolean modifyPost(PostDto.ModifyDto dto) {
        Post post = postRepository.findByIdAndVisibleIsTrue(dto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 포스트입니다."));
        try {
            post.modifyPost(dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "포스트 수정에 실패했습니다.");
        }
        return true;
    }

    public String uploadFile(MultipartFile file) {
        PostFile postFile = fileUtil.getPostFile(file);
        postFileRepository.save(postFile);
        fileUtil.uploadFile(file, postFile.getFileName());

        return postFile.getFileName();
    }
}
