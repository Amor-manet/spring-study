package com.nightriders7.portfolio.domain.entity

import com.nightriders7.portfolio.domain.constant.SkillType
import jakarta.persistence.*

@Entity
class Skill(
    name: String,
    type: String,
    isActive:Boolean

) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    var id: Long? = null

    var name: String = name

    @Column(name= "skill_type")// DBMS에 따라 type이라는 용어를 예약어로 사용할 수 있으므로, 이부분을 주의해서 따로 컬럼명 지정
    @Enumerated(value = EnumType.STRING)
    var type: SkillType = SkillType.valueOf(type) // constant SkillType 파일에서 일치하는 스킬타입을 가져와서 넣어준다

    var isActive: Boolean = isActive

}