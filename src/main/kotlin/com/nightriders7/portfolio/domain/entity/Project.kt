package com.nightriders7.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class Project : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    var id: Long? = null


}