package com.example.zorocafe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class InventorySettingsScreen {

    private Management management;
    private VBox itemListBox;

    public InventorySettingsScreen(Management management) {
        this.management = management;
    }

    public void show(Stage primaryStage) {
        Label title = new Label("INVENTORY SETTINGS");

        itemListBox = new VBox(5);
        refreshItemList();

        ScrollPane scrollPane = new ScrollPane(itemListBox);
        scrollPane.setPrefHeight(200);
        scrollPane.setFitToWidth(true);

        Label displayLabel = new Label("All Items:");
        Label addLabel = new Label("Add/Delete Item:");

        TextField nameField = new TextField();
        nameField.setPromptText("Item Name");
        nameField.setMaxWidth(150);

        TextField priceField = new TextField();
        priceField.setPromptText("Price");
        priceField.setMaxWidth(150);

        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("DRINK", "FOOD");
        categoryBox.setPromptText("Select Category");
        categoryBox.setMaxWidth(150);

        HBox inputBox = new HBox(10, nameField, priceField, categoryBox);
        inputBox.setAlignment(Pos.CENTER);

        TextField idField = new TextField();
        idField.setPromptText("Item ID");
        idField.setMaxWidth(100);

        TextField newNameField = new TextField();
        newNameField.setPromptText("New Name");
        newNameField.setMaxWidth(150);

        TextField newPriceField = new TextField();
        newPriceField.setPromptText("New Price");
        newPriceField.setMaxWidth(150);

        HBox updateInputBox = new HBox(10, idField, newNameField, newPriceField);
        updateInputBox.setAlignment(Pos.CENTER);

        Label updateLabel = new Label("Update Item (by ID):");

        Button addButton = new Button("Add Item");
        Button deleteButton = new Button("Delete Item");
        Button updateButton = new Button("Update Item");
        Button displayButton = new Button("Display All Items");
        Button backButton = new Button("Back to Dashboard");

        addButton.setMinWidth(120);
        deleteButton.setMinWidth(120);
        updateButton.setMinWidth(120);
        displayButton.setMinWidth(120);

        HBox buttonBox1 = new HBox(10, addButton, deleteButton);
        buttonBox1.setAlignment(Pos.CENTER);

        HBox buttonBox2 = new HBox(10, updateButton, displayButton);
        buttonBox2.setAlignment(Pos.CENTER);

        Label messageLabel = new Label();

        VBox layout = new VBox(15, title, displayLabel, scrollPane,addLabel, inputBox,
                updateLabel, updateInputBox,
                buttonBox1, buttonBox2, messageLabel, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        addButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String priceStr = priceField.getText().trim();
            String category = categoryBox.getValue();

            if(name.isEmpty() || priceStr.isEmpty() || category == null) {
                messageLabel.setText("Error: Please fill all fields");
                return;
            }

            try {
                double price = Double.parseDouble(priceStr);
                if(price <= 0) {
                    messageLabel.setText("Error: Price must be positive");
                    return;
                }

                Category cat = Category.valueOf(category);
                management.inventory.addItem(name, price, cat);

                messageLabel.setText("Item added successfully");
                refreshItemList();
                clearAddFields(nameField, priceField, categoryBox);
            } catch (NumberFormatException ex) {
                messageLabel.setText("Error: Invalid price format");
            } catch (IllegalArgumentException ex) {
                messageLabel.setText("Error: Invalid category");
            }
        });

        deleteButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            if(name.isEmpty()) {
                messageLabel.setText("Error: Enter item name to delete");
                return;
            }

            boolean success = management.inventory.removeItem(name);
            if(success) {
                messageLabel.setText("Success: Item deleted");
                refreshItemList();
                clearAddFields(nameField, priceField, categoryBox);
            } else {
                messageLabel.setText("Error: Item not found");
            }
        });

        updateButton.setOnAction(e -> {
            String idStr = idField.getText().trim();
            String newName = newNameField.getText().trim();
            String newPriceStr = newPriceField.getText().trim();

            if(idStr.isEmpty() || newName.isEmpty() || newPriceStr.isEmpty()) {
                messageLabel.setText("Error: Fill all update fields");
                return;
            }

            try {
                int id = Integer.parseInt(idStr);
                double newPrice = Double.parseDouble(newPriceStr);

                if(newPrice <= 0) {
                    messageLabel.setText("Error: Price must be positive");
                    return;
                }

                boolean success = management.inventory.updateItem(id, newName, newPrice);

                if(success) {
                    messageLabel.setText("Success: Item updated");
                    refreshItemList();
                    clearUpdateFields(idField, newNameField, newPriceField);
                } else {
                    messageLabel.setText("Error: Item ID not found");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Error: Invalid ID or price format");
            }
        });

        displayButton.setOnAction(e -> {
            refreshItemList();
            messageLabel.setText("Item list refreshed");
        });

        backButton.setOnAction(e -> {
            AdminDashboard adminDashboard = new AdminDashboard(management,
                    management.printAllUser()[0]);
            adminDashboard.show(primaryStage);
        });

        Scene scene = new Scene(layout, 700, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zoro's Cafe - Inventory Settings");
        primaryStage.show();
    }

    public void refreshItemList() {
        itemListBox.getChildren().clear();
        Item[] items = management.inventory.viewItems();

        if(items.length == 0) {
            Label emptyLabel = new Label("No items found");
            itemListBox.getChildren().add(emptyLabel);
            return;
        }

        for(Item item : items) {
            Label itemInfo = new Label("ID: " + item.getId() +
                    " | Name: " + item.getName() +
                    " | Price: PKR " + String.format("%.2f", item.getPrice()) +
                    " | Category: " + item.getCategory());
            itemListBox.getChildren().add(itemInfo);
        }
    }

    public void clearAddFields(TextField nameField, TextField priceField, ComboBox<String> categoryBox) {
        nameField.clear();
        priceField.clear();
        categoryBox.setValue(null);
    }

    public void clearUpdateFields(TextField idField, TextField newNameField, TextField newPriceField) {
        idField.clear();
        newNameField.clear();
        newPriceField.clear();
    }
}