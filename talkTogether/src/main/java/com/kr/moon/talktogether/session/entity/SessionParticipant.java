package com.kr.moon.talktogether.session.entity;

import com.kr.moon.talktogether.user.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "session_participants")
public class SessionParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK → sessions
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    // FK → users (nullable: 향후 비로그인 게스트 지원 확장 가능)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    // VARCHAR(20) — HOST / MEMBER
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ParticipantRole role;

    // TIMESTAMP — 입장 시각
    @Column(nullable = false, updatable = false)
    private LocalDateTime joinedAt;

    // TIMESTAMP — 퇴장 시각 (null = 아직 통화 중)
    private LocalDateTime leftAt;

    @PrePersist
    protected void onCreate() {
        this.joinedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public Session getSession() { return session; }
    public void setSession(Session session) { this.session = session; }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }

    public ParticipantRole getRole() { return role; }
    public void setRole(ParticipantRole role) { this.role = role; }

    public LocalDateTime getJoinedAt() { return joinedAt; }

    public LocalDateTime getLeftAt() { return leftAt; }
    public void setLeftAt(LocalDateTime leftAt) { this.leftAt = leftAt; }
}
