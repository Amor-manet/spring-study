package com.nightriders7.portfolio.domain.repository

import com.nightriders7.portfolio.domain.entity.Experience
import org.springframework.data.jpa.repository.JpaRepository

interface ExperienceRepository : JpaRepository<Experience, Long>