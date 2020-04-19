package ru.ifmo.egalkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.egalkin.system.ReporterSystem;
import ru.ifmo.egalkin.event.Statistic;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StatisticController {

    private final ReporterSystem reporterSystem;

    @Autowired
    public StatisticController(ReporterSystem reporterSystem) {
        this.reporterSystem = reporterSystem;
    }

    @GetMapping("/daily/info")
    public List<Statistic> getDailyInfo(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam("from") LocalDate from,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam("to") LocalDate to) {
        return reporterSystem.getDailyStat(from, to);
    }
}
