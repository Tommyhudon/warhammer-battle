package com.whbattle.springboot.usecase.unit;

import com.whbattle.springboot.adapter.repository.unit.UnitRepositoryImpl;
import com.whbattle.springboot.domain.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUnit {

    private final UnitRepositoryImpl unitRepository;

    @Autowired
    public CreateUnit(UnitRepositoryImpl unitRepository) {
        this.unitRepository = unitRepository;
    }

    public void create(Unit unit) {
        unitRepository.create(unit.name, unit);
    }
}
