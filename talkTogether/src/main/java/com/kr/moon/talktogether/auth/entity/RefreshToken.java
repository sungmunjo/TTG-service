package com.kr.moon.talktogether.auth.entity;

import com.kr.moon.talktogether.user.entity.Users;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Entity
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(nullable = false, length = 512)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date expiresAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    public RefreshToken(Users user, String token, Date expiresAt) {
        this.user = user;
        this.token = token;
        this.expiresAt = expiresAt;
        this.createdAt = new Date();
    }

    public Long getId() { return id; }

    public Users getUser() { return user; }

    public String getToken() { return token; }

    public Date getExpiresAt() { return expiresAt; }

    public void setToken(String token) { this.token = token; }

    public void setExpiresAt(Date expiresAt) { this.expiresAt = expiresAt; }
}
