package com.nightriders7.portfolio.domain.repository

import com.nightriders7.portfolio.domain.entity.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Long>