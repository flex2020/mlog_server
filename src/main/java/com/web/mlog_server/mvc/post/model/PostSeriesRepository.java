package com.web.mlog_server.mvc.post.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostSeriesRepository extends JpaRepository<PostSeries, Integer> {
    boolean existsBySeries(String series);
    Optional<PostSeries> findBySeries(String series);
}
