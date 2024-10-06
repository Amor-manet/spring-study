package com.nightriders7.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class ExperienseDetail : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_detail_id")
    var id: Long? = null


}