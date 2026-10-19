package com.kr.moon.talktogether.session.entity;

import com.kr.moon.talktogether.group.entity.Group;
import com.kr.moon.talktogether.user.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sessions")
public class Session {

    // UUID — WebRTC room ID로도 활용 가능
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID sessionId;

    // VARCHAR(100) — 세션 이름 (즉석 생성 시 null 허용)
    @Column(length = 100)
    private String sessionName;

    // FK → groups (nullable: 그룹 없는 즉석 세션)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    // FK → users — 세션 개설자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id", nullable = false)
    private Users host;

    // VARCHAR(20) — 초대 코드 (예: TTG-A3XK2F), unique
    @Column(nullable = false, unique = true, length = 20)
    private String inviteCode;

    // VARCHAR(20) — INSTANT / SCHEDULED
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SessionType sessionType;

    // VARCHAR(20) — WAITING / ACTIVE / ENDED
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SessionStatus status;

    // TIMESTAMP — 실제 통화 시작 시각 (참여자 첫 입장 시 기록)
    private LocalDateTime startedAt;

    // TIMESTAMP — 통화 종료 시각
    private LocalDateTime endedAt;

    // TIMESTAMP — 세션 레코드 생성 시각
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) this.status = SessionStatus.WAITING;
    }

    public UUID getSessionId() { return sessionId; }

    public String getSessionName() { return sessionName; }
    public void setSessionName(String sessionName) { this.sessionName = sessionName; }

    public Group getGroup() { return group; }
    public void setGroup(Group group) { this.group = group; }

    public Users getHost() { return host; }
    public void setHost(Users host) { this.host = host; }

    public String getInviteCode() { return inviteCode; }
    public void setInviteCode(String inviteCode) { this.inviteCode = inviteCode; }

    public SessionType getSessionType() { return sessionType; }
    public void setSessionType(SessionType sessionType) { this.sessionType = sessionType; }

    public SessionStatus getStatus() { return status; }
    public void setStatus(SessionStatus status) { this.status = status; }

    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }

    public LocalDateTime getEndedAt() { return endedAt; }
    public void setEndedAt(LocalDateTime endedAt) { this.endedAt = endedAt; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
