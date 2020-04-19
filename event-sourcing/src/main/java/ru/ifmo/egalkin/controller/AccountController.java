package ru.ifmo.egalkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.egalkin.system.AdminSystem;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class AccountController {
    private final AdminSystem adminSystem;

    @Autowired
    public AccountController(AdminSystem adminSystem) {
        this.adminSystem = adminSystem;
    }

    @PostMapping("/account/{id}")
    public void create(@PathVariable("id") long accountId) throws Exception {
        adminSystem.createAccount(accountId);
    }

    @PostMapping("/account/{id}/extend")
    public void extension(
            @PathVariable("id")
                    long accountId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam("extension_date")
                    LocalDateTime date,
            @RequestParam("days")
                    int days
    ) {
        adminSystem.extendAccount(accountId, date, days);
    }
}
