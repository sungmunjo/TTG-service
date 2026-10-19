package com.kr.moon.talktogether.group.service;

import com.kr.moon.talktogether.group.model.GroupCreateRequest;
import com.kr.moon.talktogether.group.model.GroupCreateResponse;
import com.kr.moon.talktogether.group.repository.GroupMemberRepository;
import com.kr.moon.talktogether.group.repository.GroupRepository;
import com.kr.moon.talktogether.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final StorageService storageService;

    public GroupCreateResponse createGroup(GroupCreateRequest request) throws IOException {
        // TODO
        return null;
    }
}
