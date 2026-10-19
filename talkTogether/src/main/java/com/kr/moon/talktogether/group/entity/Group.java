package com.kr.moon.talktogether.group.entity;

import com.kr.moon.talktogether.user.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    // VARCHAR(100) — 그룹명
    @Column(nullable = false, length = 100)
    private String groupName;

    // VARCHAR(500) — 그룹 설명
    @Column(length = 500)
    private String description;

    // FK → users
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private Users createdBy;

    // TIMESTAMP — 생성일시 (LocalDateTime: @Temporal 불필요, DB는 TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // TIMESTAMP — 수정일시
    private LocalDateTime updatedAt;

    @Column(length = 500)
    private String groupImagePath;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private GroupStatus groupStatus = GroupStatus.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private Users updatedBy;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getGroupId() { return groupId; }
    public void setGroupId(Long groupId) { this.groupId = groupId; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Users getCreatedBy() { return createdBy; }
    public void setCreatedBy(Users createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public String getGroupImagePath() { return groupImagePath; }
    public void setGroupImagePath(String groupImagePath) { this.groupImagePath = groupImagePath; }

    public Users getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(Users updatedBy) { this.updatedBy = updatedBy; }

    public GroupStatus getGroupStatus() { return groupStatus; }
    public void setGroupStatus(GroupStatus groupStatus) { this.groupStatus = groupStatus; }
}
