package com.nightriders7.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class Link : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    var id: Long? = null


}