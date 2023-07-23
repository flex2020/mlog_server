package com.web.mlog_server.domain.post;

import com.web.mlog_server.domain.post.model.Post;
import com.web.mlog_server.domain.post.model.PostFileRepository;
import com.web.mlog_server.domain.post.model.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostFileRepository postFileRepository;

    public List<PostDto.ListDto> getPostList(Pageable pageable) {
        return postRepository.findAll(pageable)
                .getContent()
                .stream().map(Post::toListDto)
                .toList();
    }
    public PostDto.DetailDto getPostDetail(int id) {
        return postRepository.findById(id)
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
        try {
            postRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 포스트입니다."))
                    .setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "포스트 삭제에 실패하였습니다.");
        }
        return true;
    }

    @Transactional
    public boolean modifyPost(PostDto.ModifyDto dto) {
        try {
            Post post = postRepository.findById(dto.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 포스트입니다."));
            post.modifyPost(dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "포스트 수정에 실패하였습니다.");
        }
        return true;
    }
}
