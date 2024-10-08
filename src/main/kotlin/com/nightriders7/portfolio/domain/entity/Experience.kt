package com.nightriders7.portfolio.domain.entity

import jakarta.persistence.*

@Entity //@Entity 애노테이션을 통해 이 클래스는 JPA 엔티티임을 나타내며, 데이터베이스의 테이블과 매핑됩니다.
class Experience( //생성자 파라미터로 경력의 제목, 설명, 시작 연도 및 월, 종료 연도 및 월, 활성 상태 등의 정보를 받습니다.
        title: String,
        description: String,
        startYear: Int,
        startMonth: Int,
        endYear: Int?,
        endMonth: Int?,
        isActive: Boolean
) : BaseEntity() {

    @Id // @Id로 기본 키로 설정되었고, @GeneratedValue를 통해 데이터베이스에서 자동으로 값을 생성하는 자동 증가 키입니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_id") // @Column(name = "experience_id")를 통해 데이터베이스 컬럼명을 지정합니다.
    var id: Long? = null

    var title: String = title
    var description: String = description
    var startYear: Int = startYear
    var startMonth: Int = startMonth
// title, description, startYear, startMonth, endYear, endMonth, isActive 필드들은
// 각각 생성자에서 받아온 값을 초기화하며, 경력에 대한 정보를 저장합니다.

    var endYear: Int? = endYear
    var endtMonth: Int? = endMonth
//endYear, endMonth는 nullable(Int?) 타입으로, 프로젝트가 아직 끝나지 않은 경우 null 값을 가질 수 있습니다.

    var isActive:Boolean = isActive
// isActive는 현재 프로젝트가 진행 중인지를 나타내는 활성 상태를 저장합니다.


    //@OneToMany: Experience는 여러 개의 ExperienseDetail(경력 세부 정보)와 1:N 관계를 가집니다.
    @OneToMany(targetEntity = ExperienseDetail::class,
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY)
        //cascade = [CascadeType.ALL]: Experience가 저장, 삭제될 때 연관된 세부 정보도 함께 저장, 삭제되도록 합니다.
        //fetch = FetchType.LAZY: 연관된 데이터는 필요할 때만 가져오도록 설정해 메모리와 성능을 관리합니다.

    //@JoinColumn(name = "experience_id"): 외래 키로 사용될 컬럼을 지정하며, ExperienseDetail 테이블의 experience_id와 매핑합니다.
    @JoinColumn(name = "experience_id")
    var details: MutableList<ExperienseDetail> = mutableListOf()
    // detail 필드: MutableList<ExperienseDetail> 타입으로, 경력에 대한 여러 세부 정보들을 포함합니다.

    //getEndYearMonth() 메서드는 종료 연도와 월을 반환합니다.
    fun getEndYearMonth(): String {

        if(endYear == null || endtMonth == null) {
            return "Present" // 프로젝트가 아직 진행 중이라면
        }

        return "${endYear}.${endtMonth}" // 프로젝트가 끝난 시간이 명확하면 2024.10
    }

    // update() 메서드는 경력 정보를 업데이트하기 위해 사용됩니다.
    fun update(  title: String, description: String,
                 startYear: Int, startMonth: Int,
                 endYear: Int?, endMonth: Int?,
                 isActive: Boolean) {
        this.title = title
        this.description = description
        this.startYear = startYear
        this.startMonth = startMonth
        this.endYear = endYear
        this.endtMonth = endMonth
        this.isActive = isActive
    }

    //addDetails() 메서드는 ExperienseDetail을 추가하는 메서드입니다.
    //인자로 받은 세부 정보 리스트가 null이 아닌 경우, 기존의 detail 리스트에 모두 추가합니다.
    fun addDetails(details: MutableList<ExperienseDetail>?){
        if(details != null) {
            this.details.addAll(details)
        }
    }

}