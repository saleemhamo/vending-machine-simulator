package com.example.vending_machine_simulator.models.item;

import com.example.vending_machine_simulator.models.enums.ItemType;
import lombok.Data;

@Data
public class SnackItem extends Item {

    public SnackItem(Long id, String displayName, double price) {
        super(ItemType.SNACKS);
        setId(id);
        setDisplayName(displayName);
        setPrice(price);
    }


}
