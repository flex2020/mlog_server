# 나만의 블로그: Mlog

2023년 8월 26일 도메인 발급 완료

블로그 주소 - https://flex2020.com/

## 😎 프로젝트 진행인원

해당 프로젝트는 개인 프로젝트로 진행하였습니다.

## 📌 프로젝트 소개
이 프로젝트는 저의 학습 기록과 정리를 위해 velog를 관리하던 중 **나만의 블로그를 만들면 좋지 않을까?** 라는 생각에 시작하게 되었습니다.

나만의 블로그를 만들게 된다면 블로그를 만들면서 배우게 되는 새로운 기술도 있고 기존에 알고 있던 기술들을 갈고 닦을 기회가 생긴다고 생각했습니다.

또한, 사이트를 직접 운영하게 된다면 **경험해보지 못한 새로운 오류**를 마주하고 그 오류를 **해결하는 능력**을 키울 수 있을 것이라고 생각하여 해당 프로젝트를 진행하게 되었습니다.


## 📌 프로젝트 목표
- 스프링 부트와 JPA를 학습하고 실제로 사용함으로써, 기술의 숙련도를 향상시키기 위함.
- 프로젝트를 진행하며 새롭게 배운 점 혹은 다시 한번 알게 된 점을 정리하기 위함.
- AWS를 통해 실제 서비스를 배포하고 운영하여 실제 운영단계에서 일어날 수 있는 일들을 경험하기 위함.

## 📌 시스템 동작 설계
![다이어그램](https://github.com/flex2020/mlog_server/assets/61104736/dd669b51-ac4a-4f1b-9a52-30aa8cc52265)

모든 방문자가 사용가능한 서비스는 포스트 열람과 프로젝트 열람입니다.

포스트와 프로젝트 모두 작성은 관리자 로그인을 통해 권한을 얻고 이용이 가능합니다.

## 📌 데이터베이스 설계
![image](https://github.com/flex2020/mlog_server/assets/61104736/8b99da6c-84cb-4a43-9c20-d546c8ff2221)

2023-09-12 기준 데이터베이스 구조입니다.

데이터베이스 구조는 개발 중 컬럼이 추가되거나 변경될 가능성이 있습니다.

## 📃 API 명세

API 명세는 미완성 입니다.

[API 명세보기](https://github.com/flex2020/mlog_server/blob/master/document/api.md)

## 👉 시작 가이드
### 요구 사항
시스템을 동작시키기 위해 아래 요소들을 설치해주세요.
- [IntelliJ](https://www.jetbrains.com/ko-kr/idea/download/?section=windows)
- [Java SE Development Kit 18.0.2.1](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)
- [MariaDB 10.9](https://mariadb.org/download/?t=mariadb&p=mariadb&r=10.9.7&os=windows&cpu=x86_64&pkg=msi&m=blendbyte)

### 설치 및 실행

1. `git bash`를 이용해 아래 명령어를 입력하거나, `GitHub Desktop`을 이용해 레포지토리를 `clone`해주세요.
```git
$ git clone http://github.com/flex2020/mlog_server.git
```

2. `IntelliJ`를 실행시킨 후 프로젝트 폴더를 Open 해주세요.
3. IntelliJ에서 `src/main/resources`폴더에 `application.yml`파일을 생성하고 아래의 내용을 입력해주세요.
4. DB에서 `mlog` 데이터베이스를 생성해주세요. (문자 인코딩은 utf8-unicode-ci로 선택해주세요.)

```yaml
server:
  port: 8080
  error:
    include-message: always

spring:
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    url: jdbc:mariadb://localhost:3306/mlog
    username: 사용자명
    password: 비밀번호

  jpa:
    open-in-view: false
    generate-ddl: true
    show-sql: true
    hibernate:
      ddl-auto: none
    properties:
      hibernate:
        show_sql: true
        format_sql: true
        default_batch_fetch_size: 15
    database-platform: org.hibernate.dialect.MariaDB106Dialect
  servlet:
    multipart:
      max-file-size: 50MB
      max-request-size: 50MB

logging:
  level:
    org.hibernate.type.descriptor.sql: trace

jwt:
  secret: jwt 생성에 사용되는 비밀키

path:
  original: /home/ubuntu/mlog/resource/original/
  thumbnail: /home/ubuntu/mlog/resource/thumbnail/
  connect: /api/files/**
  window: file:///C:/home/ubuntu/mlog/resource/
  ubuntu: file:///home/ubuntu/mlog/resource/
```

4. IntelliJ에서 `Shift + F10` 단축키를 이용해 어플리케이션을 실행해주세요.

## 🛠 기술 스택

### IDE 및 Git
IntelliJ, Git, GitHub
### 개발
Java, Spring Boot, Spring Security, JPA, MariaDB

## ⭐ 주요 기능
- ### 개발 포스트 및 프로젝트 목록 API(모든 사용자)
  개발 포스트 및 프로젝트 목록 데이터를 클라이언트에게 전송합니다.
- ### 개발 포스트 및 프로젝트 상세보기 API(모든 사용자)
  개발 포스트 및 프로젝트 상세보기 데이터를 클라이언트에게 전송합니다.
- ### 개발 포스트 및 프로젝트 작성(관리자 전용)
  개발 포스트 및 프로젝트를 작성하여 데이터베이스에 저장합니다.
- ### 개발 포스트 및 프로젝트 삭제(관리자 전용)
  개발 포스트 및 프로젝트를 삭제합니다.
- ### 개발 포스트 및 프로젝트 수정(관리자 전용)
  개발 포스트 및 프로젝트의 내용을 수정합니다.
