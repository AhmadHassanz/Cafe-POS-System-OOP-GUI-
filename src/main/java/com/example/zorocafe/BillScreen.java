package com.example.zorocafe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BillScreen {

    private Management management;
    private User currentUser;
    private Bill bill;

    public BillScreen(Management management, User currentUser, Bill bill) {
        this.management = management;
        this.currentUser = currentUser;
        this.bill = bill;
    }

    public void show(Stage primaryStage) {
        Label title = new Label("BILL");

        VBox billItemsBox = new VBox(5);
        CartItem[] items = bill.getItem();

        Label itemsHeader = new Label("Items:");
        billItemsBox.getChildren().add(itemsHeader);

        for(CartItem cartItem : items) {
            if(cartItem != null) {
                Label itemInfo = new Label(
                        cartItem.getItem().getName() +
                                " | Price: PKR " + String.format("%.2f", cartItem.getItem().getPrice()) +
                                " | Qty: " + cartItem.getQuantity() +
                                " | Total: PKR " + String.format("%.2f", cartItem.getTotalPrice())
                );
                billItemsBox.getChildren().add(itemInfo);
            }
        }

        Label separator1 = new Label("--------------------------------------------------");
        Label separator2 = new Label("--------------------------------------------------");

        Label subTotalLabel = new Label("SubTotal: PKR " + String.format("%.2f", bill.getSubTotal()));
        Label taxLabel = new Label("Tax (" + (bill.getTax() * 100) + "%): PKR " +
                String.format("%.2f", bill.getSubTotal() * bill.getTax()));
        Label totalLabel = new Label("Total: PKR " + String.format("%.2f", bill.getTotal()));

        Button newOrderBtn = new Button("New Order");
        Button logoutBtn = new Button("Logout");

        newOrderBtn.setMinWidth(120);
        logoutBtn.setMinWidth(120);

        HBox buttonBox = new HBox(10, newOrderBtn, logoutBtn);
        buttonBox.setAlignment(Pos.CENTER);

        newOrderBtn.setOnAction(e -> {
            management.cart = new Cart(management.inventory);
            StaffDashboard staffDashboard = new StaffDashboard(management, currentUser);
            staffDashboard.show(primaryStage);
        });

        logoutBtn.setOnAction(e -> {
            management.cart = new Cart(management.inventory);
            LoginScreen loginScreen = new LoginScreen(management);
            loginScreen.show(primaryStage);
        });

        ScrollPane scrollPane = new ScrollPane(billItemsBox);
        scrollPane.setPrefHeight(250);
        scrollPane.setFitToWidth(true);

        VBox layout = new VBox(15,
                title, scrollPane, separator1, subTotalLabel,
                taxLabel, totalLabel, separator2, buttonBox);

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        Scene scene = new Scene(layout, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zoro's Cafe - Bill");
        primaryStage.show();
    }
}