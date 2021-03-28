package com.whbattle.springboot.usecase.unit;

import com.whbattle.springboot.adapter.repository.unit.UnitRepositoryImpl;
import com.whbattle.springboot.domain.entity.unit.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteUnit {

    private final UnitRepositoryImpl unitRepository;

    @Autowired
    public DeleteUnit(UnitRepositoryImpl unitRepository) {
        this.unitRepository = unitRepository;
    }

    public Unit delete(String name) {
        return unitRepository.delete(name);
    }
}
