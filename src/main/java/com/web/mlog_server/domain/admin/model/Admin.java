package com.web.mlog_server.domain.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Admin {
    @Id @Column(length = 100)
    private String id;

    @Column(length = 200, nullable = false)
    private String password;
}
