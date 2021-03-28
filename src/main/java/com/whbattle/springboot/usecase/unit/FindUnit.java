package com.whbattle.springboot.usecase.unit;

import com.whbattle.springboot.adapter.repository.unit.UnitRepositoryImpl;
import com.whbattle.springboot.domain.entity.unit.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindUnit {

    private final UnitRepositoryImpl unitRepository;

    @Autowired
    public FindUnit(UnitRepositoryImpl unitRepository) {
        this.unitRepository = unitRepository;
    }

    public Unit findByName(String name) {
        return unitRepository.readById(name);
    }
}
