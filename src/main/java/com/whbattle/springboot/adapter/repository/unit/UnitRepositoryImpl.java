package com.whbattle.springboot.adapter.repository.unit;

import com.whbattle.springboot.domain.entity.Unit;
import com.whbattle.springboot.domain.port.UnitRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class UnitRepositoryImpl implements UnitRepository {
    private HashMap<String, Unit> tempDB = new HashMap<>();

    @Override
    public List<Unit> read() {
        return null;
    }

    @Override
    public Unit readById(String name) {
        return tempDB.get(name);
    }

    @Override
    public Unit create(String name, Unit unit) {
        return tempDB.put(name, unit);
    }

    @Override
    public Unit update(String name, Unit unit) {
        return tempDB.replace(name, unit);
    }

    @Override
    public Unit delete(String name) {
        return tempDB.remove(name);
    }

    @Override
    public List<Unit> readByTags(String... faction) {
        return null;
    }
}
