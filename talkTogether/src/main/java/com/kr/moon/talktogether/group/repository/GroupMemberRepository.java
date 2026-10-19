package com.kr.moon.talktogether.group.repository;

import com.kr.moon.talktogether.group.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
}
