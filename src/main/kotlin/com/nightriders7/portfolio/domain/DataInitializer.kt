package com.nightriders7.portfolio.domain

import com.nightriders7.portfolio.domain.constant.SkillType
import com.nightriders7.portfolio.domain.entity.*
import com.nightriders7.portfolio.domain.repository.*
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDate
import javax.swing.Spring


@Component
@Profile(value = ["default"]) // 접속 프로필 설정 도커나 디폴트
class DataInitializer(private val achievementRepository: AchievementRepository,
                      private val introductionRepository: IntroductionRepository,
                      private val linkRepository: LinkRepository,
                      private val skillRepository: SkillRepository,
                      private val projectRepository: ProjectRepository,
                      private val experienceRepository: ExperienceRepository,) {


    @PostConstruct
    fun initializeData(){

        println("스프링이 실행되었습니다 테스트 데이터를 초기화합니다.")

        val achievement = mutableListOf<Achievement>(
                Achievement(
                    title = "2020 판다 대회 최우수상",
                    description = "죽순 빨리 먹기 신기록",
                    host = "용인시",
                    achievedDate = LocalDate.of(2022, 10, 2),
                    isActive = true

                ),
                Achievement(
                    title = "정보처리기사",
                    description = "자료구조 알고리즘 개발론 소프트웨어공학 등등",
                    host = "한국산업인력공단",
                    achievedDate = LocalDate.of(2021, 5, 8),
                    isActive = true

                ),
        )
        achievementRepository.saveAll(achievement)

        val introductions = mutableListOf<Introduction>(
            Introduction(content = "1. 주도적으로 문제점을 찾고 해결하고 동료들과 지식을 공유할 수 있는 판다입니다.", isActive = true),
            Introduction(content = "2. 나 한명을 위한 기술이 아닌 모두를 위한 기술을 추구합니다.", isActive = true,),
            Introduction(content = "3. 예외상황을 미리 예측하고 최선을 다해 처리하려고 노력합니다.", isActive = true)
        )
        introductionRepository.saveAll(introductions)

        val links = mutableListOf<Link>(
            Link(name = "에버랜드" , content = "https://www.everland.com/gateway", isActive = true),
            Link(name = "나무위키" , content = "https://namu.wiki/w/%ED%91%B8%EB%B0%94%EC%98%A4", isActive = true)
        )
        linkRepository.saveAll(links)

        var experience1 = Experience(
            title = "에버대학교",
            description =  "컴퓨터공학과 전공",
            startYear = 2020,
            startMonth = 7,
            endYear = 2024,
            endMonth = 4,
            isActive = true

        )
        experience1.addDetails(
            mutableListOf(
                    ExperienseDetail(content = "학점: 3.4/4.5", isActive = true ),
                    ExperienseDetail(content = "자료구조 스터디 운영: 2년", isActive = true )
            )

        )

        val experience2 = Experience(
            title = "에버랜드",
            description = "죽순 처리법 개발자",
            startYear = 2021,
            startMonth = 5,
            endYear = null,
            endMonth = null,
            isActive = true
        )
        experience2.addDetails(
            mutableListOf(
                ExperienseDetail(content = "죽순 서리 최고속 판다", isActive = true ),
                ExperienseDetail(content = "이달의 판다요원 최우수상 수상", isActive = true )
            )

        )
        experienceRepository.saveAll(mutableListOf(experience1,experience2))

        val java = Skill(name = "Java", type = SkillType.LANGUAGE.name, isActive = true)
        val kotlin = Skill(name = "Kotlin", type = SkillType.LANGUAGE.name, isActive = true)
        val Python = Skill(name = "python", type = SkillType.LANGUAGE.name, isActive = true)
        val Spring = Skill(name = "spring", type = SkillType.LANGUAGE.name, isActive = true)
        val django = Skill(name = "Django", type = SkillType.LANGUAGE.name, isActive = true)
        val MySQL = Skill(name = "Mysql", type = SkillType.LANGUAGE.name, isActive = true)
        val kafka = Skill(name = "Kafka", type = SkillType.LANGUAGE.name, isActive = true)
        skillRepository.saveAll(mutableListOf(java,kotlin,Python,Spring,django,MySQL,kafka))


        val project1 = Project(
            name = "자동 죽순 서리 시스템 개발",
            description =  "사육사가 없을 때 자동으로 알림을 전송하여 죽순 서리타이밍을 정확히 계산해주는 서비스, 싱싱한 죽순 섭취 개선",
            startYear = 2022,
            startMonth = 3,
            endYear = 2023,
            endMonth = 1,
            isActive = true

        )
        project1.addDetails(
                    mutableListOf(
                        ProjectDetail(content = "자동죽순서리기 개발", url = null, isActive = true),
                        ProjectDetail(content = "돌맹이 시선 처리 개선, 구르기 속도 상향", url = null, isActive = true)
                    )
        )
        project1.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project1, skill = java),
                ProjectSkill(project = project1, skill = Spring),
                ProjectSkill(project = project1, skill = MySQL),
                ProjectSkill(project = project1, skill = kafka),
            )

        )

        val project2 = Project(
            name = "유명인사 랭킹 시스템 개발 및 운영 중",
            description =  "유명인사의 업적 관리 및 랭킹 운영으로 경쟁심 고취 팬들의 자긍심 함양",
            startYear = 2024,
            startMonth = 10,
            endYear = 2024,
            endMonth = 11,
            isActive = true

        )
        project2.addDetails(
            mutableListOf(
                ProjectDetail(content = "테이블 구성 고도화", url = null, isActive = true),
                ProjectDetail(content = "알고리즘 개발로 랭킹 시스템 최적화", url = null, isActive = true),
                ProjectDetail(content = "마케팅을 통한 비지니스 개선", url = null, isActive = true)
            )
        )
        project2.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project2, skill = java),
                ProjectSkill(project = project2, skill = Spring),
                ProjectSkill(project = project2, skill = MySQL),
                ProjectSkill(project = project2, skill = Python),
            )

        )
        projectRepository.saveAll(mutableListOf(project1,project2))






    }


}