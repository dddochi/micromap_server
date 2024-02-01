# Micro Map

## Objective
마이크로 맵은 주거지와 가장 가까운 환경을 활용하는 것을 돕는 지도 기반 어플입니다.

## Idea
집 앞 신호등을 건널 때 간발의 차이로 빨간불이 되어서 못 건너는 경우가 있습니다.
미리 신호등 시간을 알고 최적의 효율을 가지고 집 근처 환경을 활용하면 좋겠다는 아이디어로 앱을 만들기 시작했습니다.
이 아이디어를 확장시켜 집 근처 포장마차, 식당에 미리 주문을 해두어 지나가는 길에 바로 픽업하는 기능을 추가했습니다.
동네 친구들과의 약속을 맵에 저장해둬서 편리하게 약속 관리를 하는 기능도 추가했습니다.

## Functions

다음 기능들을 통해 신속하게 주변 환경을 이용할 수 있습니다.
- 신호등 시간 알려주기
- 집 근처 맛집 저장하기
- 약속 잡기
- 집 근처 포장마차, 식당 예약 주문

## Technology
벡엔드: Java, Spring boot를 사용해서 개발
<br>
서버: localhost
<br>
데이터 베이스: h2 database
<br>
프론트 : 추후에 Flutter를 통해 만들 예정 

<img src="https://img.shields.io/badge/JAVA-007396?style=flat-square&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=flat-square&logo=springboot&logoColor=black">

## Progress
완성된 기능
- 로그인, 유저, 식당, 주문, 약속 : Database & API 완성

미완성 기능

- 신호등 기능 : 실제 데이터가 없어서 가상 데이터 사용할 예정
## API TEST USING SWAGGER
Swagger를 활용해서 API Test 진행 

![swagger1](https://github.com/dddochi/micromap_server/blob/main/swagger1.png)
![swagger2](https://github.com/dddochi/micromap_server/blob/main/swagger2.png)
![swagger3](https://github.com/dddochi/micromap_server/blob/main/swagger3.png)
![swagger4](https://github.com/dddochi/micromap_server/blob/main/swagger4.png)


