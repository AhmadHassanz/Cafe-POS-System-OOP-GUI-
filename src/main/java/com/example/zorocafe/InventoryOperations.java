package com.example.zorocafe;
public interface InventoryOperations {
    void addItem(String name,double price,Category category);
    boolean removeItem(String name);

    Item[] viewItems();

    Item findItemByName(String name);
}
