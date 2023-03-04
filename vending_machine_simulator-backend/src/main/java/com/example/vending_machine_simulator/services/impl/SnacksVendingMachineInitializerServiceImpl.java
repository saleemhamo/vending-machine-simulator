package com.example.vending_machine_simulator.services.impl;

import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachine;
import com.example.vending_machine_simulator.services.SnacksVendingMachineInitializerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SnacksVendingMachineInitializerServiceImpl implements SnacksVendingMachineInitializerService {

    @Autowired
    private Environment environment;

    @Override
    public void initializeSnacksVendingMachine() {
        log.info("Initializing Snacks Vending Machine");

        SnackVendingMachine snackVendingMachine = SnackVendingMachine.getInstance();
        snackVendingMachine.setNumberOfRows(Integer.parseInt(environment.getProperty("vending_machine.rows.count", "5")));
        snackVendingMachine.setNumberOdCols(Integer.parseInt(environment.getProperty("vending_machine.cols.count", "5")));

    }
}
