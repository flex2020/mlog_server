package com.web.mlog_server.mvc.post.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostSeries {
    @Id
    @Column(length = 100)
    private String seriesName;

    @OneToMany(mappedBy = "post")
    private List<Post> postList;
}
