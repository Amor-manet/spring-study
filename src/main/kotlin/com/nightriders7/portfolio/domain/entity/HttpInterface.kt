package com.nightriders7.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class HttpInterface : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "http_interface_id")
    var id: Long? = null


}