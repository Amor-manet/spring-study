package com.nightriders7.portfolio.domain.repository

import com.nightriders7.portfolio.domain.entity.Introduction
import org.springframework.data.jpa.repository.JpaRepository

interface IntroductionRepository : JpaRepository<Introduction, Long>