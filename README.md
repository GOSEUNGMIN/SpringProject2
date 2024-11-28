# SpringProject2
Java Spring Boot와 MySQL을 이용하여 음식점 예약 및 관리 사이트를 구현하였습니다.
## 🎑 프로젝트 소개
해당 프로젝트는 **이용자가 예약을 하고 싶은 가게를 음식점 예약을 실제로 진행** 하거나 **음식점의 점주가 해당 사이트에 가게를 등록하여 예약을 받을 수 있는 시스템**이 구현된 프로젝트 입니다.

## 🎆 개발 상세 내용
- **개발 상세 기간** : 2024-11-20(수) ~ 2024-11-26(화)
- 로그인 및 회원가입 기능
- admin, shop, user 3개의 권한으로 구분하여 기능제어
- admin은 모든 기능을 사용 할 수 있으며, 가게 및 예약정보를 삭제 가능.
- shop은 가게를 가게목록에 등록 시킬 수 있으며, **KakaoMap으로 주소를 등록하여 KakaoMap**으로 해당 위치 파악 가능
- user는 등록된 가게의 목록을 보고 **예약하고 싶은 시간대로 예약 가능**.
- user가 예약을 취소하거나, shop이 해당 예약을 취소시 정보가 사라지지 않고 **"예약취소"** 상태로 변경
- Spring Batch를 이용하여 로그인 시 메일을 전송하고, **1분 간격으로 총 3번 전송** 하는 테스트 배치 구현
- REST API를 이용하여 **GET, POST, PUT, DELETE로 회원관리를 JSON방식**으로 받아 테스트 구현 

## 📻 개발 환경
- **Version** : java 18
- **IDE** : IntelliJ
- **DataBase** : XAMPP-MySQL
- **ORM** : JPA
- **API** : Postman
