package com.kr.moon.talktogether.session.entity;

import com.kr.moon.talktogether.group.entity.Group;
import com.kr.moon.talktogether.user.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meetings")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingId;

    // FK → groups — 그룹 기반 회의만 지원
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    // VARCHAR(200) — 회의 제목
    @Column(nullable = false, length = 200)
    private String title;

    // VARCHAR(1000) — 회의 설명/안건
    @Column(length = 1000)
    private String description;

    // TIMESTAMP — 예정 회의 시각
    @Column(nullable = false)
    private LocalDateTime scheduledAt;

    // FK → sessions (nullable: 실제 세션 시작 전까지 null)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Session session;

    // VARCHAR(20) — NONE / DAILY / WEEKLY / MONTHLY
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RecurrenceType recurrenceType;

    // TIMESTAMP — 반복 종료일 (recurrenceType = NONE 이면 null)
    private LocalDateTime recurrenceEndDate;

    // FK → users
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private Users createdBy;

    // TIMESTAMP — 생성일시
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.recurrenceType == null) this.recurrenceType = RecurrenceType.NONE;
    }

    public Long getMeetingId() { return meetingId; }

    public Group getGroup() { return group; }
    public void setGroup(Group group) { this.group = group; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }

    public Session getSession() { return session; }
    public void setSession(Session session) { this.session = session; }

    public RecurrenceType getRecurrenceType() { return recurrenceType; }
    public void setRecurrenceType(RecurrenceType recurrenceType) { this.recurrenceType = recurrenceType; }

    public LocalDateTime getRecurrenceEndDate() { return recurrenceEndDate; }
    public void setRecurrenceEndDate(LocalDateTime recurrenceEndDate) { this.recurrenceEndDate = recurrenceEndDate; }

    public Users getCreatedBy() { return createdBy; }
    public void setCreatedBy(Users createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
