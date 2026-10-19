package com.kr.moon.talktogether.group.repository;

import com.kr.moon.talktogether.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findGroupByGroupName(String groupName);
}
