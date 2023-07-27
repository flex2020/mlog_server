package com.web.mlog_server.mvc.project.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("select p " +
            "from Project  p " +
            "limit 3 ")
    List<Project> getPreviewProject();
}
