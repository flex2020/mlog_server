package com.web.mlog_server.mvc.post.model;

import com.web.mlog_server.mvc.post.PostDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    private String series;

    @OneToMany(mappedBy = "postSeries")
    private List<Post> postList = new ArrayList<>();

    @Builder
    public PostSeries(String series, List<Post> postList) {
        this.series = series;
        this.postList = postList;
    }

    public PostDto.SeriesCommonDto toCommonDto() {
        return PostDto.SeriesCommonDto.builder()
                .series(this.series)
                .build();
    }

    public void setSeries(String newSeries) {
        this.series = newSeries;
        // 연결된 모든 Post의 PostSeries 업데이트
        for (Post post : postList) {
            post.updatePostSeries(this);
        }
    }
}
