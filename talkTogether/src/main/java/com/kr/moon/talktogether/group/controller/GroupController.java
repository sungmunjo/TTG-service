package com.kr.moon.talktogether.group.controller;

import com.kr.moon.talktogether.group.model.GroupCreateRequest;
import com.kr.moon.talktogether.group.model.GroupCreateResponse;
import com.kr.moon.talktogether.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GroupCreateResponse> createGroup(@ModelAttribute GroupCreateRequest request) throws IOException {
        return ResponseEntity.ok(groupService.createGroup(request));
    }
}
