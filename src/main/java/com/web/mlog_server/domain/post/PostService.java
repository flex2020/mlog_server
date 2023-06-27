package com.web.mlog_server.domain.post;

import com.web.mlog_server.domain.post.model.PostFileRepository;
import com.web.mlog_server.domain.post.model.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostFileRepository postFileRepository;
}
