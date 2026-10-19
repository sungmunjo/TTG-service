package com.kr.moon.talktogether.session.entity;

public enum SessionStatus {
    WAITING,  // 생성됨, 아직 아무도 입장 안 함
    ACTIVE,   // 통화 진행 중
    ENDED     // 통화 종료
}
