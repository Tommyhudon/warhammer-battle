package com.whbattle.springboot.domain.port;

import com.whbattle.springboot.domain.entity.unit.Unit;
import com.whbattle.springboot.adapter.repository.Repository;

import java.util.List;

public interface UnitRepository extends Repository<Unit, String> {

    List<Unit> read();

    Unit readById(String name);

    Unit create(String name, Unit unit);

    Unit update(String name, Unit unit);

    Unit delete(String name);

    List<Unit> readByTags(String...faction);
}
