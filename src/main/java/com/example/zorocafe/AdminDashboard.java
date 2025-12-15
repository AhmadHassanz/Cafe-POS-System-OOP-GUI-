package com.example.zorocafe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        layout.setStyle("""
                -fx-background-color: rgba(90,140,120,0.85);
                -fx-background-radius: 20;
                -fx-border-color: rgba(90,140,120,0.4);
                -fx-border-radius: 20;
                -fx-border-width: 10;
                """);
        title.setStyle("""
                -fx-text-fill: white;
                -fx-font-size: 16px;
                -fx-font-weight: bold;
                -fx-font-family: 'Times New Roman';
                """);
        welcomeLabel.setStyle("""
                -fx-text-fill: white;
                -fx-font-size: 16px;
                -fx-font-weight: bold;
                -fx-font-family: 'Times New Roman';
                """);
        inventorySettingsBtn.setStyle("""
                -fx-background-color: white;
                -fx-text-fill: rgb(90,140,120);
                -fx-font-weight: bold;
                -fx-font-size: 14px;        
                -fx-font-family: 'Times New Roman';
                """);
        userSettingsBtn.setStyle("""
                -fx-background-color: white;
                -fx-text-fill: rgb(90,140,120);
                -fx-font-weight: bold;
                -fx-font-size: 14px;        
                -fx-font-family: 'Times New Roman';
                """);
        logoutBtn.setStyle("""
                -fx-background-color: white;
                -fx-text-fill: rgb(90,140,120);
                -fx-font-weight: bold;
                -fx-font-size: 14px;        
                -fx-font-family: 'Times New Roman';
                """);

        layout.setMaxHeight(400);
        layout.setMaxWidth(350);
        Image image = new Image("File:D:\\OOP code\\IDE code\\Zoro Cafe\\src\\main\\resources\\com\\example\\zorocafe\\projectBackground.png");
        ImageView bgImage = new ImageView(image);
        bgImage.setPreserveRatio(false);

        StackPane root = new StackPane();
        bgImage.fitWidthProperty().bind(root.widthProperty());
        bgImage.fitHeightProperty().bind(root.heightProperty());
        root.getChildren().addAll(bgImage,layout);
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zoro's Cafe - Admin Dashboard");
        primaryStage.show();
    }
}