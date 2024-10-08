package com.nightriders7.portfolio.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity{

    @CreatedDate // 처음에 언제 만들어졌는지
    @Column(nullable = false, updatable = false) // 컬럼이름은 null일 수 없다, 컬럼은 업데이트 될 수 없다
    var createdDateTime: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate // 데이터가 마지막으로 수정된 시간이 언제인지
    @Column(nullable = false) // updatable 디폴트 값이 true임
    var updateDateTime: LocalDateTime = LocalDateTime.now()

}