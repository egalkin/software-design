package ru.ifmo.egalkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.egalkin.system.EntrySystem;

@RestController
@RequestMapping("/api")
public class EntryController {
    private final EntrySystem entrySystem;

    @Autowired
    public EntryController(EntrySystem entrySystem) {
        this.entrySystem = entrySystem;
    }

    @PostMapping("/account/{id}/enter")
    public void enter(@PathVariable("id") long accountId) {
        entrySystem.enter(accountId);
    }

    @PostMapping("/account/{id}/leave")
    public void leave(@PathVariable("id") long accountId) {
        entrySystem.leave(accountId);
    }
}
