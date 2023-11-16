package com.web.mlog_server.mvc.post.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findTop3ByVisibleIsTrueOrderByIdDesc();
    @Query("select p from Post p left join fetch p.postSeries s where p.visible = true order by p.id desc")
    List<Post> findPostByFetchJoin();
    Optional<Post> findByIdAndVisibleIsTrue(Integer id);
}
