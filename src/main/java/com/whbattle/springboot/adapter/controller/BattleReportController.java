package com.whbattle.springboot.adapter.controller;

import com.whbattle.springboot.domain.entity.battleReport.BattleReport;
import com.whbattle.springboot.usecase.battleReport.CreateBattleReport;
import com.whbattle.springboot.usecase.unit.dto.UnitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BattleReportController {

    private final CreateBattleReport createBattleReport;

    @Autowired
    public BattleReportController(CreateBattleReport createBattleReport) {
        this.createBattleReport = createBattleReport;
    }

    @PostMapping("/battle_report")
    public ResponseEntity<String> createBattleRapport(
            @RequestBody UnitDto[] units) {

        BattleReport battleReport = createBattleReport.create(units);
        return new ResponseEntity<>(battleReport.battleReportMessage(), HttpStatus.OK);
    }
}
