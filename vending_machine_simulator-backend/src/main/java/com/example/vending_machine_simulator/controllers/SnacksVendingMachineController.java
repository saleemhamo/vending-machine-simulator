package com.example.vending_machine_simulator.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v0/machine")
@RestController
public class SnacksVendingMachineController {

    @GetMapping
    public Object get() {
        return "Here";
    }
}
