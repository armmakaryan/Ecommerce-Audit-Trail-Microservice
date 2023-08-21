package com.smartCode.audit_traill.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateActionDto {

    private Integer userId;

    private String actionType;

    private String entityType;

    private LocalDateTime actionDate;

}