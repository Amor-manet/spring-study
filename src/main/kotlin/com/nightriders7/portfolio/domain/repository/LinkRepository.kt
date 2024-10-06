package com.nightriders7.portfolio.domain.repository

import com.nightriders7.portfolio.domain.entity.Link
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository : JpaRepository<Link, Long>