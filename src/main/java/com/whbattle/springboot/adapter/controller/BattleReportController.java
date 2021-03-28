package com.whbattle.springboot.adapter.controller;

import com.whbattle.springboot.domain.entity.BattleReport;
import com.whbattle.springboot.domain.entity.Unit;
import com.whbattle.springboot.usecase.battlereport.CreateBattleReport;
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

    @PostMapping("/battle")
    public ResponseEntity battleUnit(
            @RequestBody Unit[] unit) {

        BattleReport battleReport = createBattleReport.create(unit);
        return new ResponseEntity<String>(battleReport.battleReportMessage(), HttpStatus.OK);
    }
}
