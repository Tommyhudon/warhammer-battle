package com.whbattle.springboot.usecase;

import com.whbattle.springboot.adapter.repository.unit.UnitRepositoryImpl;
import com.whbattle.springboot.domain.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitService {

    private final UnitRepositoryImpl unitRepository;

    @Autowired
    public UnitService(UnitRepositoryImpl unitRepository) {
        this.unitRepository = unitRepository;
    }

    public Unit getUnitByName(String name) {
        return unitRepository.readById(name);
    }

    public void createUnit(Unit unit) {
        unitRepository.create(unit.name, unit);
    }

    public Unit updateUnit(Unit unit) {
        return unitRepository.update(unit.name, unit);
    }

    public Unit deleteUnit(String name) {
        return unitRepository.delete(name);
    }
}
