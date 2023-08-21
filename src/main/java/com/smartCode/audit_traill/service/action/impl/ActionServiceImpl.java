package com.smartCode.audit_traill.service.action.impl;

import com.smartCode.audit_traill.mapper.ActionMapper;
import com.smartCode.audit_traill.model.dto.CreateActionDto;
import com.smartCode.audit_traill.model.entity.ActionEntity;
import com.smartCode.audit_traill.repository.ActionRepository;
import com.smartCode.audit_traill.service.action.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionMapper actionMapper;
    private final ActionRepository actionRepository;

    @Override
    @Transactional
    public void create(CreateActionDto actionDto) {
        ActionEntity entity = actionMapper.toEntity(actionDto);
        actionRepository.save(entity);
    }
}