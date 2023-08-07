package com.web.mlog_server.mvc.project.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findTop3ByVisibleIsTrueOrderByIdDesc();
    List<Project> findAllByVisibleTrue();
}
