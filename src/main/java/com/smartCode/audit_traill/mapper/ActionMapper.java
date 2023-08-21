package com.smartCode.audit_traill.mapper;

import com.smartCode.audit_traill.model.dto.CreateActionDto;
import com.smartCode.audit_traill.model.entity.ActionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActionMapper {
    ActionEntity toEntity(CreateActionDto actionDto);
}