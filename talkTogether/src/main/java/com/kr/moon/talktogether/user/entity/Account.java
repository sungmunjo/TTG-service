package com.kr.moon.talktogether.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userEmail;

    private String password;

    @Column(length = 11)
    private String phoneNumber;

    private String userName;

    private String role;

    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;


}
