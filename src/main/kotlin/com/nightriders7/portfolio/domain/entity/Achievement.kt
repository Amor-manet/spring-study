package com.nightriders7.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class Achievement : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    var id: Long? = null


}