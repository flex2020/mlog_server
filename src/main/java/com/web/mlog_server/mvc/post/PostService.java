package com.web.mlog_server.mvc.post;

import com.web.mlog_server.common.FileUtil;
import com.web.mlog_server.mvc.post.model.*;
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
    private final PostSeriesRepository postSeriesRepository;
    private final FileUtil fileUtil;

    @Transactional(readOnly = true)
    public List<PostDto.ListDto> getPreviewPost() {
        return postRepository.findTop3ByVisibleIsTrueOrderByIdDesc()
                .stream()
                .map(Post::toListDto)
                .toList();
    }
    @Transactional(readOnly = true)
    public List<PostDto.ListDto> getPostList() {
        return postRepository.findAllByVisibleIsTrueOrderByIdDesc()
                .stream().map(Post::toListDto)
                .toList();
    }
    @Transactional(readOnly = true)
    public PostDto.DetailDto getPostDetail(int id) {
        return postRepository.findByIdAndVisibleIsTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 포스트입니다."))
                .toDetailDto();
    }
    @Transactional
    public boolean addPost(PostDto.AddDto dto) {
        try {
            List<PostFile> fileList = postFileRepository.findAllById(dto.getFileList());
            Post post = dto.toEntity(fileList);
            if (dto.getSeries() != null) {
                PostSeries postSeries = postSeriesRepository.findBySeries(dto.getSeries())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                post.updatePostSeries(postSeries);
            }
            Post save = postRepository.save(post);
            for (PostFile postFile : fileList) {
                postFile.setPost(save);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "포스트 업로드에 실패하였습니다.");
        }
        return true;
    }
    @Transactional
    public boolean changeVisibility(int id) {
        Post post = postRepository.findById(id)
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
        Post post = postRepository.findById(dto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 포스트입니다."));
        try {
            post.modifyPost(dto);
            // 시리즈가 있는 기존 포스트의 시리즈를 없애는 경우
            if ((dto.getSeries() == null || dto.getSeries().equals("")) && post.getPostSeries() != null) {
                post.updatePostSeries(null);
            }
            // 시리즈가 없는 기존 포스트에 시리즈를 추가하는 경우
            else if (dto.getSeries() != null && !dto.getSeries().equals("") && post.getPostSeries() == null) {
                PostSeries postSeries = postSeriesRepository.findBySeries(dto.getSeries())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 시리즈입니다."));
                post.updatePostSeries(postSeries);
            }
            // 시리즈가 있는 기존 포스트의 시리즈를 다른 시리즈로 변경하는 경우
            else if (dto.getSeries() != null && !dto.getSeries().equals("") && post.getPostSeries() != null && !post.getPostSeries().getSeries().equals(dto.getSeries())) {
                PostSeries postSeries = postSeriesRepository.findBySeries(dto.getSeries())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 시리즈입니다."));
                post.updatePostSeries(postSeries);
            }
            List<PostFile> fileList = postFileRepository.findAllById(dto.getFileList());
            for (PostFile postFile : fileList) {
                postFile.setPost(post);
            }
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

    public List<PostDto.SeriesCommonDto> getSeriesList() {
        return postSeriesRepository.findAll()
                .stream()
                .map(PostSeries::toCommonDto)
                .toList();
    }
}
