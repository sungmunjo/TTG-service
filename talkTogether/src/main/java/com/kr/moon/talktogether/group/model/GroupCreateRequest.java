package com.kr.moon.talktogether.group.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class GroupCreateRequest {
    private String groupName;
    private String groupDescription;
    private MultipartFile imageFile;
}
