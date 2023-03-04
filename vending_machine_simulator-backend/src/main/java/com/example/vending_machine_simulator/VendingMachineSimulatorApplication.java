package com.example.vending_machine_simulator;

import com.example.vending_machine_simulator.services.SnacksVendingMachineInitializerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@RequiredArgsConstructor
public class VendingMachineSimulatorApplication {

    private final SnacksVendingMachineInitializerService snacksVendingMachineInitializerService;


    public static void main(String[] args) {
        SpringApplication.run(VendingMachineSimulatorApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    private void initializeSnackVendingMachine() {
        snacksVendingMachineInitializerService.initializeSnacksVendingMachine();
    }
}
