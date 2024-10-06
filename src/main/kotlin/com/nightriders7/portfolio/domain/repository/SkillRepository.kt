package com.nightriders7.portfolio.domain.repository

import com.nightriders7.portfolio.domain.entity.Skill
import org.springframework.data.jpa.repository.JpaRepository

interface SkillRepository : JpaRepository<Skill, Long>