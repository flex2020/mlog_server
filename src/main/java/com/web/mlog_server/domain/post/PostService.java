package com.web.mlog_server.domain.post;

import com.web.mlog_server.domain.post.model.Post;
import com.web.mlog_server.domain.post.model.PostFileRepository;
import com.web.mlog_server.domain.post.model.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
