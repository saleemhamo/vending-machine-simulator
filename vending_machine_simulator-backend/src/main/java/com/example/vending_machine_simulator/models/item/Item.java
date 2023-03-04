package com.example.vending_machine_simulator.models.item;

import com.example.vending_machine_simulator.models.BaseObject;
import com.example.vending_machine_simulator.models.enums.ItemType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@NoArgsConstructor
public class Item extends BaseObject {

    private ItemType type;
    private Long id;
    private String displayName;
    private double price;

    public Item(ItemType type) {
        this.type = type;
    }

    public Item(ItemType type, Long id, String displayName, double price) {
        this.type = type;
        this.id = id;
        this.displayName = displayName;
        this.price = price;
    }
}
