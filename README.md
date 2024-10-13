![ERD 다이어그램](./images/Celebraiting.webp)
첫번째 개인 웹 프로젝트
# Celebraiting 🔥본격🔥  최애 배틀 ⚔️

---

제가 만든 서비스는 유명인의 업적을 수치화 시켜 랭킹으로 보여주는 서비스입니다.
당신의 그 잘난 유명인의 업적을 세상에 알리고 포인트를 쌓아 순위권 안에 유지시켜주세요.
지금도 경쟁자가 쫓아오고 있습니다~~ 😰

Service I made is a service that quantify the achievements of celebrities and shows them as a ranking.
Let the world know the achievements of your great celebrity, build points, and keep them in the ranking.


<br>


# 📚 목차

---
1. [소개](#소개)
2. [주요기능](#주요기능)
3. [테이블 설명](#테이블-설명)
   - [FamousPerson](#famousperson)
   - [TalentArea](#TalentArea)
   - [FamousPerson_TalentArea](#FamousPerson_TalentArea)
   - [Achievement](#achievement)
   - [FamousPerson_Achievement](#famousperson_achievement)
   - [PointsAwardCriteria](#pointsawardcriteria)
   - [PointsLog](#Pointslog)


<br>

## 🖼️ 소개

---
이 mini  프로젝트는 유명인의 다양한 업적을 체계적으로 관리하고, 업적에 따라 포인트를 부여하며, 이를 통해 유명인의 랭킹을 산출하는 것을 목표로 합니다. 스키마(?) 통해 유명인과 그들의 업적, 분야, 포인트 부여 내역 등을 효율적으로 저장하고 관리할 수 있으며, 그들의 팬들에게 자긍심을 줍니다.


<br>


## 💡 주요기능

---
### - 유명인의 개인 정보와 그들이 속한 분야 관리

### -  업적 및 업적에 대한 상세 정보 저장

### -  포인트 수여 기준 정의 및 포인트 부여 내역 기록

### -  포인트 부여 시 총 포인트 자동 계산 및 업데이트

### -  유명인의 총 포인트를 기반으로 랭킹 산출


<br>



## 👨‍🏫 테이블 설명

---
![ERD 다이어그램](./images/erd.png)
### 1. FamousPerson
유명인의 기본 정보와 total point를 저장합니다.

```sql
CREATE TABLE FamousPerson (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 고유 ID
   name VARCHAR(100),                     -- 이름
   age INT,                               -- 나이
   gender BOOLEAN,                        -- 성별 (0: 여자, 1: 남자)
   occupation VARCHAR(100),               -- 직업 또는 주요 활동
   description TEXT,                      -- 간략한 설명
   total_points INT DEFAULT 0             -- 총 포인트 (초기값 0)
);
```

### 2. TalentArea
유명인이 활동하는 분야를 저장합니다.
```sql
CREATE TABLE TalentArea (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 고유 ID
   talent_area_name VARCHAR(100)          -- 분야 이름
);
```

### 3. FamousPerson_TalentArea
유명인과 분야 간의 n:m 관계를 나타냅니다.
```sql
CREATE TABLE FamousPerson_TalentArea (
   famous_person_id BIGINT,               -- 유명인사 ID
   talent_area_id BIGINT,                 -- 분야 ID
   PRIMARY KEY (famous_person_id, talent_area_id),
   FOREIGN KEY (famous_person_id) REFERENCES FamousPerson(id),
   FOREIGN KEY (talent_area_id) REFERENCES TalentArea(id)
);
```

### 4. Achievement
유명인의 업적 정보를 저장합니다.
```sql
CREATE TABLE Achievement (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 고유 ID
   title VARCHAR(255),                    -- 업적 제목
   description TEXT,                      -- 업적 설명
   date DATE,                             -- 업적 달성 날짜
   tier VARCHAR(20)                       -- 업적 티어 (예: 도시지배급, 국가권력급)
);
```
### 5. FamousPerson_Achievement
유명인과 업적 간의 n:m 관계를 나타냅니다.
```sql
CREATE TABLE FamousPerson_Achievement (
   famous_person_id BIGINT,               -- 유명인사 ID
   achievement_id BIGINT,                 -- 업적 ID
   PRIMARY KEY (famous_person_id, achievement_id),
   FOREIGN KEY (famous_person_id) REFERENCES FamousPerson(id),
   FOREIGN KEY (achievement_id) REFERENCES Achievement(id)
);
```

### 6. PointsAwardCriteria
포인트를 부여하는 기준과 해당 포인트 값을 정의합니다.
```sql
CREATE TABLE PointsAwardCriteria (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 고유 ID
   criteria_name VARCHAR(255),            -- 기준 이름
   points INT,                            -- 부여될 포인트
   description TEXT                       -- 기준 설명
);
```

### 7. PointsLog
포인트를 부여하는 기준과 해당 포인트 값을 정의합니다.
```sql
CREATE TABLE PointsLog (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 고유 ID
   famous_person_id BIGINT,               -- 유명인사 ID
   achievement_id BIGINT,                 -- 업적 ID
   points_award_criteria_id BIGINT,       -- 포인트 수여 기준 ID
   points INT,                            -- 부여된 포인트
   date_awarded DATE,                     -- 포인트 부여 날짜
   FOREIGN KEY (famous_person_id) REFERENCES FamousPerson(id),
   FOREIGN KEY (achievement_id) REFERENCES Achievement(id),
   FOREIGN KEY (points_award_criteria_id) REFERENCES PointsAwardCriteria(id),
   UNIQUE KEY unique_points_award (famous_person_id, achievement_id, points_award_criteria_id)
);
```

<br>

## REST API 설계

---
### 1. FamousPerson API

| **Method** | **Endpoint**              | **Request Body**                                                                                   | **Response Body**                                                                                       | **Description**                          |
|------------|---------------------------|----------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------|------------------------------------------|
| POST       | /famous-people             | `{ "name": "홍길동", "age": 35, "gender": true, "description": "한국의 전설적인 인물" }`              | `{ "id": 1, "name": "홍길동", "age": 35, "gender": true, "description": "한국의 전설적인 인물" }`         | 새로운 유명인을 등록합니다.              |
| GET        | /famous-people             | 없음                                                                                               | `[ { "id": 1, "name": "홍길동", "age": 35, "gender": true, "description": "한국의 전설적인 인물" } ]`    | 모든 유명인의 목록을 조회합니다.         |
| GET        | /famous-people/{id}        | 없음                                                                                               | `{ "id": 1, "name": "홍길동", "age": 35, "gender": true, "description": "한국의 전설적인 인물" }`         | 특정 유명인의 정보를 조회합니다.         |
| PUT        | /famous-people/{id}        | `{ "name": "홍길동", "age": 36, "description": "사실은 그냥 좀도둑" }`                               | `{ "id": 1, "name": "홍길동", "age": 36, "gender": true, "description": "사실은 그냥 좀도둑" }`            | 특정 유명인의 정보를 수정합니다.         |
| DELETE     | /famous-people/{id}        | 없음                                                                                               | `{ "message": "Famous person deleted successfully" }`                                                    | 특정 유명인을 삭제합니다.                |
### 2. TalentArea API

| **Method** | **Endpoint**              | **Request Body**                                                | **Response Body**                                | **Description**                          |
|------------|---------------------------|-----------------------------------------------------------------|-------------------------------------------------|------------------------------------------|
| POST       | /talent-areas             | `{ "talentArea_name": "연기" }`                                  | `{ "id": 1, "talentArea_name": "연기" }`         | 새로운 활동 분야를 등록합니다.           |
| GET        | /talent-areas             | 없음                                                            | `[ { "id": 1, "talentArea_name": "연기" } ]`     | 모든 활동 분야를 조회합니다.             |
| GET        | /talent-areas/{id}        | 없음                                                            | `{ "id": 1, "talentArea_name": "연기" }`         | 특정 활동 분야를 조회합니다.             |
| PUT        | /talent-areas/{id}        | `{ "talentArea_name": "수정된 분야 이름" }`                      | `{ "id": 1, "talentArea_name": "수정된 분야 이름" }`| 특정 활동 분야의 이름을 수정합니다.      |
| DELETE     | /talent-areas/{id}        | 없음                                                            | `{ "message": "Talent area deleted successfully" }` | 특정 활동 분야를 삭제합니다.             |

### 3. FamousPerson_TalentArea API

| **Method** | **Endpoint**                                           | **Request Body** | **Response Body**                              | **Description**                                    |
|------------|--------------------------------------------------------|------------------|------------------------------------------------|----------------------------------------------------|
| POST       | /famous-people/{famousPersonId}/talent-areas/{talentAreaId} | 없음             | `{ "message": "Talent area assigned to famous person" }` | 특정 유명인에게 특정 활동 분야를 연결합니다.         |
| DELETE     | /famous-people/{famousPersonId}/talent-areas/{talentAreaId} | 없음             | `{ "message": "Talent area removed from famous person" }` | 특정 유명인과 활동 분야 간의 연결을 해제합니다.      |

### 4. Achievement API

| **Method** | **Endpoint**              | **Request Body**                                                                                    | **Response Body**                                                                                       | **Description**                          |
|------------|---------------------------|-----------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------|------------------------------------------|
| POST       | /achievements             | `{ "title": "대상 수상", "description": "대한민국 최고 연기자상 수상", "date": "2024-01-01", "tier": "Gold" }` | `{ "id": 1, "title": "대상 수상", "description": "대한민국 최고 연기자상 수상", "date": "2024-01-01", "tier": "Gold" }` | 새로운 업적을 등록합니다.                |
| GET        | /achievements             | 없음                                                                                                | `[ { "id": 1, "title": "대상 수상", "description": "대한민국 최고 연기자상 수상", "date": "2024-01-01", "tier": "Gold" } ]` | 모든 업적을 조회합니다.                  |
| GET        | /achievements/{id}        | 없음                                                                                                | `{ "id": 1, "title": "대상 수상", "description": "대한민국 최고 연기자상 수상", "date": "2024-01-01", "tier": "Gold" }` | 특정 업적을 조회합니다.                  |
| PUT        | /achievements/{id}        | `{ "title": "수정된 업적 제목", "description": "수정된 설명" }`                                       | `{ "id": 1, "title": "수정된 업적 제목", "description": "수정된 설명", "date": "2024-01-01", "tier": "Gold" }` | 특정 업적의 정보를 수정합니다.           |
| DELETE     | /achievements/{id}        | 없음                                                                                                | `{ "message": "Achievement deleted successfully" }`                                                      | 특정 업적을 삭제합니다.                  |

### 5. FamousPerson_Achievement API

| **Method** | **Endpoint**                                             | **Request Body** | **Response Body**                              | **Description**                                    |
|------------|----------------------------------------------------------|------------------|------------------------------------------------|----------------------------------------------------|
| POST       | /famous-people/{famousPersonId}/achievements/{achievementId} | 없음             | `{ "message": "Achievement assigned to famous person" }` | 특정 유명인에게 특정 업적을 연결합니다.              |
| DELETE     | /famous-people/{famousPersonId}/achievements/{achievementId} | 없음             | `{ "message": "Achievement removed from famous person" }` | 특정 유명인과 업적 간의 연결을 해제합니다.           |

### 6. PointsAwardCriteria API

| **Method** | **Endpoint**              | **Request Body**                                                                         | **Response Body**                                                                         | **Description**                          |
|------------|---------------------------|------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|------------------------------------------|
| POST       | /points-award-criteria     | `{ "criteria_name": "기술 혁신 기여", "points": 50, "description": "대한민국 기술 혁신에 기여한 경우" }` | `{ "id": 1, "criteria_name": "기술 혁신 기여", "points": 50, "description": "대한민국 기술 혁신에 기여한 경우" }` | 새로운 포인트 부여 기준을 등록합니다.     |
| GET        | /points-award-criteria     | 없음                                                                                     | `[ { "id": 1, "criteria_name": "기술 혁신 기여", "points": 50, "description": "기술 혁신" } ]` | 모든 포인트 부여 기준을 조회합니다.       |
| GET        | /points-award-criteria/{id}| 없음                                                                                     | `{ "id": 1, "criteria_name": "기술 혁신 기여", "points": 50, "description": "기술 혁신" }`  | 특정 포인트 부여 기준을 조회합니다.       |
| PUT        | /points-award-criteria/{id}| `{ "criteria_name": "수정된 기준 이름", "points": 30 }`                                   | `{ "id": 1, "criteria_name": "수정된 기준 이름", "points": 30 }`                            | 특정 포인트 부여 기준의 정보를 수정합니다.|
| DELETE     | /points-award-criteria/{id}| 없음                                                                                     | `{ "message": "Points award criteria deleted successfully" }`                              | 특정 포인트 부여 기준을 삭제합니다.       |

### 7. PointsLog API

| **Method** | **Endpoint**              | **Request Body**                                                                                               | **Response Body**                                                                                                       | **Description**                          |
|------------|---------------------------|----------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|------------------------------------------|
| POST       | /points-logs              | `{ "famous_person_id": 1, "achievement_id": 2, "points_award_criteria_id": 3, "points": 50, "date_awarded": "2024-10-08" }` | `{ "id": 1, "famous_person_id": 1, "achievement_id": 2, "points_award_criteria_id": 3, "points": 50, "date_awarded": "2024-10-08" }` | 새로운 포인트 로그를 생성합니다.         |
| GET        | /points-logs              | 없음                                                                                                           | `[ { "id": 1, "famous_person_id": 1, "achievement_id": 2, "points_award_criteria_id": 3, "points": 50, "date_awarded": "2024-10-08" } ]` | 모든 포인트 로그를 조회합니다.           |
| GET        | /points-logs/{id}         | 없음                                                                                                           | `{ "id": 1, "famous_person_id": 1, "achievement_id": 2, "points_award_criteria_id": 3, "points": 50, "date_awarded": "2024-10-08" }`  | 특정 포인트 로그를 조회합니다.           |
| GET        | /famous-people/{famousPersonId}/points | 없음                                                                                                           | `{ "famous_person_id": 1, "total_points": 150, "points_logs": [ { "achievement_id": 2, "points": 50, "date_awarded": "2024-10-08" }, { "achievement_id": 3, "points": 100, "date_awarded": "2024-10-05" } ] }` | 특정 유명인의 업적 포인트를 조회합니다.   |
| GET        | /famous-people/{famousPersonId}/points | 없음                                                                                                           | `{ "famous_person_id": 1, "total_points": 150, "points_logs": [ { "achievement_id": 2, "points": 50, "date_awarded": "2024-10-08" }, { "achievement_id": 3, "points": 100, "date_awarded": "2024-10-05" } ] }` | 특정 유명인의 업적 포인트 및 포인트 로그를 조회합니다. |



## 💭 만들면서 고려했던 부분

---
1. 포인트 로그 테이블에서 동일인물 동일업적 동일조건에 조합으로 **중복 수혜를 막고자 유니크 키를 사용하였음**
2. PointsLog 테이블이 포인트를 직접 저장하지 않고, PointsAwardCriteria의 points를 참조하면서 미래에 포인트 값이 변경된다면 과거에 부여된 포인트에도 영향을 주게 됨
   **로그 테이블에서 포인트 부여시점의 점수를 직접 저장하게 포인트 칼럼을 추가함**
3. 랭킹을 계산할 때마다 PointsLog 테이블에서 실시간으로 총점을 합산하면
   추후에 데이터가 많아질경우  성능 저하가 발생할 수 있는데..
   이건 트리거를 사용해서 **total_points를 즉시 업데이트하면 될거 같다**
4. 유명인테이블에서 단일 문자열이던 분야를 삭제하고 TalentArea를 추가했습니다 이제부터 유명인이 활동하는 다양한 분야를 테이블로 표현할 수 있습니다.
5. REST api를 설계해서 추가하였습니다.

<br>


## 💰진짜 웹서비스로 만들어보고 싶습니다

---
우선은 스프링 공부를 하면서 어떻게 구현하면 좋을지 계속 구상해보겠습니다.

![머메이드 다이어그램](./images/mermaid.png)
