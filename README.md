# kafka-study

## 목적 
"실전 카프카 개발부터 운영까지" 책을 읽고 실습을 위해 만든 프로젝트

---

## 실습 내용
* 3장 배치전송, 압축전송
* 4장 리플리케이션, 리더에포크와 복구
* 5장 중복 없는 전송, 정확히 한번 전송, 다양한..
* 6장 (멀티 인스턴스 환경을 가정하고) 컨슈머 파티션 전략, 예외발생, 케이스
* 10장 스키마 레지스터 - 코드적으로 어떻게

-- 

## 사용 방법 
- docker-compose 실행
    - docker-compose -f docker-compose.yml up -d
- docker-compose 실행 결과(인스턴스 확인용)
    - docker-compose ps
- 토픽 생성
    - docker-compose exec kafka-1 kafka-topics --create --topic my-topic --bootstrap-server kafka-1:9092 --replication-factor 3 --partitions 3
- 토픽 확인
    - docker-compose exec kafka-1 kafka-topics --describe --topic my-topic --bootstrap-server kafka-1:9092
- 컨슈머 실행하기
    - docker-compose exec kafka-1 bash
    - kafka-console-consumer --topic my-topic --bootstrap-server kafka-1:9092
- 프로듀서 실행하기
    - docker-compose exec kafka-1 bash
    - kafka-console-producer --topic my-topic --broker-list kafka-1:9092
  - 파티션 랜덤하게 메시지 전송하려면 아래 옵션 추가
      - kafka-console-producer --topic my-topic --broker-list kafka-1:9092 --property "partitioner=random"
