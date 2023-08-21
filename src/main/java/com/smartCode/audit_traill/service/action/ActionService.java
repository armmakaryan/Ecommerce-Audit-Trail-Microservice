package com.smartCode.audit_traill.service.action;

import com.smartCode.audit_traill.model.dto.CreateActionDto;

public interface ActionService {
    void create(CreateActionDto actionDto);
}