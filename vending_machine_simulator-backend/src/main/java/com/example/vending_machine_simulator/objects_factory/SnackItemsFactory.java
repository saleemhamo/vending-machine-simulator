package com.example.vending_machine_simulator.objects_factory;

import com.example.vending_machine_simulator.models.item.SnackItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class SnackItemsFactory {

    private static final String[] SNACK_NAMES = {"Chips", "Popcorn", "Pretzels", "Chocolate", "Candy", "Granola Bar", "Trail Mix", "Beef Jerky", "Cookies", "Crackers",
            "Raisins", "Nuts", "Gum", "Mints", "Fruit Snacks", "Peanut Butter Cups", "Yogurt", "Cheese Sticks", "Protein Bar", "Rice Cakes",
            "Pop Rocks", "Tootsie Rolls", "Sour Patch Kids", "Swedish Fish", "Fruit Leather", "Pudding", "Fig Newtons", "Licorice", "Animal Crackers",
            "Goldfish", "Teddy Grahams", "Fruit Cups", "Nutella", "Hummus", "Tortilla Chips", "Salsa", "Dried Fruit", "Pita Chips", "Sunflower Seeds",
            "Apple Sauce", "Rice Krispies Treats"};

    public static List<SnackItem> createSnackItems(int number) {
        List<SnackItem> snacks = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            snacks.add(createSnackItem(i + 1L, SNACK_NAMES[i]));
        }
        return snacks;
    }

    public static SnackItem createSnackItem(Long id, String name) {
        Random random = new Random();
        double price = (double) (random.nextInt(490) + 10) / 100;
        return new SnackItem(id, name, price);
    }
}
