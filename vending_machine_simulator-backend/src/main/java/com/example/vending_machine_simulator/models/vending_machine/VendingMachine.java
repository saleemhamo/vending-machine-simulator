package com.example.vending_machine_simulator.models.vending_machine;

import com.example.vending_machine_simulator.models.BaseObject;
import com.example.vending_machine_simulator.models.item.Item;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class VendingMachine<T extends VendingMachineItem> extends BaseObject {

    private int numberOfRows;
    private int numberOfCols;

    private List<T> items;

    public void addItem(T item) {
        if (CollectionUtils.isEmpty(items)) {
            items = new ArrayList<>();
        }
        items.add(item);
    }

    public int getTotalNumberOfSlots() {
        return numberOfRows * numberOfCols;
    }
}
