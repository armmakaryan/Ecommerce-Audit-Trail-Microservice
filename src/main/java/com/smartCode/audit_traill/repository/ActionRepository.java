package com.smartCode.audit_traill.repository;

import com.smartCode.audit_traill.model.entity.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<ActionEntity, Integer> {
}