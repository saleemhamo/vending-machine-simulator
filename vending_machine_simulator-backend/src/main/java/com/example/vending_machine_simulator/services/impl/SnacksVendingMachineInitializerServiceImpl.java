package com.example.vending_machine_simulator.services.impl;

import com.example.vending_machine_simulator.models.item.SnackItem;
import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachine;
import com.example.vending_machine_simulator.models.vending_machine.SnackVendingMachineItem;
import com.example.vending_machine_simulator.models.vending_machine.VendingMachineItem;
import com.example.vending_machine_simulator.objects_factory.SnackItemsFactory;
import com.example.vending_machine_simulator.services.SnacksVendingMachineInitializerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
        snackVendingMachine.setNumberOfCols(Integer.parseInt(environment.getProperty("vending_machine.cols.count", "5")));

        List<SnackItem> snackItems = SnackItemsFactory.createSnackItems(snackVendingMachine.getTotalNumberOfSlots());
        addItemsToVendingMachine(snackItems);

    }

    private void addItemsToVendingMachine(List<SnackItem> snackItems) {
        SnackVendingMachine snackVendingMachine = SnackVendingMachine.getInstance();
        for (int row = 0; row < snackVendingMachine.getNumberOfRows(); row++) {
            for (int col = 0; col < snackVendingMachine.getNumberOfCols(); col++) {
                int randomStock = ThreadLocalRandom.current().nextInt(1, 11);
                SnackItem snackItem = snackItems.get(getItemIndexInOriginalList(row, col));
                snackVendingMachine.addItem(
                        new SnackVendingMachineItem(snackVendingMachine, snackItem, row, col, randomStock)
                );
            }
        }

    }

    private int getItemIndexInOriginalList(int row, int col) {
        return row * SnackVendingMachine.getInstance().getNumberOfCols() + col;
    }
}
