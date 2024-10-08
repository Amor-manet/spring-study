package com.nightriders7.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class ExperienseDetail(content: String, isActive:Boolean) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_detail_id")
    var id: Long? = null

    var content: String = content
    var isActive: Boolean = isActive

    fun update(content: String, isActive:Boolean){

        var content: String = content
        var isActive: Boolean = isActive
    }

}