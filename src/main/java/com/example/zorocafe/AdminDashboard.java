package com.example.zorocafe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AdminDashboard {

    private Management management;
    private User currentUser;

    public AdminDashboard(Management management, User currentUser) {
        this.management = management;
        this.currentUser = currentUser;
    }

    public void show(Stage primaryStage) {
        Label title = new Label("ADMIN DASHBOARD");
        Label welcomeLabel = new Label("Welcome, " + currentUser.getUsername() + "!");

        Button userSettingsBtn = new Button("User Settings");
        userSettingsBtn.setMinWidth(200);
        userSettingsBtn.setMinHeight(50);

        Button inventorySettingsBtn = new Button("Inventory Settings");
        inventorySettingsBtn.setMinWidth(200);
        inventorySettingsBtn.setMinHeight(50);

        Button logoutBtn = new Button("Logout");

        userSettingsBtn.setOnAction(e -> {
            UserSettingsScreen userSettings = new UserSettingsScreen(management);
            userSettings.show(primaryStage);
        });

        inventorySettingsBtn.setOnAction(e -> {
            InventorySettingsScreen inventorySettings = new InventorySettingsScreen(management);
            inventorySettings.show(primaryStage);
        });

        logoutBtn.setOnAction(e -> {
            LoginScreen loginScreen = new LoginScreen(management);
            loginScreen.show(primaryStage);
        });

        VBox layout = new VBox(20, title, welcomeLabel, userSettingsBtn, inventorySettingsBtn, logoutBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        Scene scene = new Scene(layout, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zoro's Cafe - Admin Dashboard");
        primaryStage.show();
    }
}