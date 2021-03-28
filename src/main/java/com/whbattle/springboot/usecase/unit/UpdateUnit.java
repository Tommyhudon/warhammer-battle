package com.whbattle.springboot.usecase.unit;

import com.whbattle.springboot.adapter.repository.unit.UnitRepositoryImpl;
import com.whbattle.springboot.domain.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUnit {
    private final UnitRepositoryImpl unitRepository;

    @Autowired
    public UpdateUnit(UnitRepositoryImpl unitRepository) {
        this.unitRepository = unitRepository;
    }

    public Unit update(Unit unit) {
        return unitRepository.update(unit.name, unit);
    }
}
