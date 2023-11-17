package com.web.mlog_server.mvc.post;

import com.web.mlog_server.common.FileUtil;
import com.web.mlog_server.mvc.post.model.*;
import com.web.mlog_server.mvc.series.model.PostSeries;
import com.web.mlog_server.mvc.series.model.PostSeriesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    /**
     * 포스트 목록
     * */
    @Transactional(readOnly = true)
    public List<PostDto.ListDto> getPostList() {
        return postRepository.findPostByFetchJoin()
                .stream().map(Post::toListDto)
                .toList();
    }
    /**
     * 포스트 미리보기
     * */
    @Transactional(readOnly = true)
    public List<PostDto.PreviewDto> getPreviewPost() {
        return postRepository.findTop3ByVisibleIsTrueOrderByIdDesc()
                .stream()
                .map(Post::toPreviewDto)
                .toList();
    }
    /**
     * 포스트 상세보기
     * */
    @Transactional(readOnly = true)
    public PostDto.DetailDto getPostDetail(int id) {
        return postRepository.findDetailByFetchJoin(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 포스트입니다."))
                .toDetailDto();
    }
    /**
     * 포스트 추가
     * */
    @Transactional
    public boolean addPost(PostDto.AddDto dto) {
        try {
            List<PostFile> fileList = postFileRepository.findAllById(dto.getFileList());
            Post post = dto.toEntity(fileList);
            if (dto.getSeries() != null && !dto.getSeries().equals("")) {
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
    /**
     * 포스트 삭제
     * */
    public boolean deletePost(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 포스트입니다."));
        try {
            postRepository.delete(post);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "포스트 삭제에 실패하였습니다.");
        }
        return true;
    }
    /**
     * 포스트 공개여부 변경
     * */
    @Transactional
    public boolean changeVisibility(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 포스트입니다."));
        try {
            post.setVisible(!post.getVisible());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "포스트 공개여부 변경에 실패했습니다.");
        }
        return true;
    }
    /**
     * 포스트 수정
     * */
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
    /**
     * 포스트 업로드 파일
     * */
    public String uploadFile(MultipartFile file) {
        PostFile postFile = fileUtil.getPostFile(file);
        postFileRepository.save(postFile);
        fileUtil.uploadFile(file, postFile.getFileName());

        return postFile.getFileName();
    }
    /**
     * 포스트 전체 목록
     * */
    public List<PostDto.AllDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(Post::toAllDto)
                .toList();
    }
}
