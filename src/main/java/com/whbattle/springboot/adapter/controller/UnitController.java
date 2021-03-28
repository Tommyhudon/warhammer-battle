package com.whbattle.springboot.adapter.controller;

import com.whbattle.springboot.domain.entity.Unit;
import com.whbattle.springboot.usecase.unit.CreateUnit;
import com.whbattle.springboot.usecase.unit.DeleteUnit;
import com.whbattle.springboot.usecase.unit.FindUnit;
import com.whbattle.springboot.usecase.unit.UpdateUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UnitController {

    private final FindUnit findUnit;
    private final CreateUnit createUnit;
    private final UpdateUnit updateUnit;
    private final DeleteUnit deleteUnit;

    @Autowired
    public UnitController(FindUnit findUnit, CreateUnit createUnit, UpdateUnit updateUnit, DeleteUnit deleteUnit) {
        this.findUnit = findUnit;
        this.createUnit = createUnit;
        this.updateUnit = updateUnit;
        this.deleteUnit = deleteUnit;
    }

    @GetMapping("/unit/{name}")
    public ResponseEntity getUnit(@PathVariable String name){
        Unit res = findUnit.findByName(name);
        return new ResponseEntity<Unit>(res, HttpStatus.OK);
    }

    @PostMapping("/unit/create")
    public ResponseEntity createUnit(
            @RequestBody Unit unit) {

        createUnit.create(unit);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/unit/update")
    public ResponseEntity updateUnit(
            @RequestBody Unit unit) {
        updateUnit.update(unit);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/unit/delete/{name}")
    public ResponseEntity deleteUnit(@PathVariable String name){

        deleteUnit.delete(name);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
