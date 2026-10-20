-- JPA가 default_schema=ttg_service 를 쓰므로 DB 최초 생성 시 스키마를 만든다
-- init-db/ 는 RDS 로 옮기기 전 컨테이너 DB 의 초기 스키마다. 지금은 참고용으로만 남겨둔다.
CREATE SCHEMA IF NOT EXISTS ttg_service;
