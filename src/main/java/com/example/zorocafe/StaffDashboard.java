package com.example.zorocafe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StaffDashboard {

    private Management management;
    private User currentUser;
    private VBox menuListBox;
    private VBox cartListBox;
    private Label messageLabel;

    public StaffDashboard(Management management, User currentUser) {
        this.management = management;
        this.currentUser = currentUser;
    }

    public void show(Stage primaryStage) {
        Label title = new Label("STAFF DASHBOARD");
        Label welcomeLabel = new Label("Welcome, " + currentUser.getUsername() + "!");

        Label menuTitle = new Label("Menu:");
        menuListBox = new VBox(5);
        menuListBox.setMinHeight(200);
        refreshMenu();

        ScrollPane menuScrollPane = new ScrollPane(menuListBox);
        menuScrollPane.setPrefHeight(200);
        menuScrollPane.setFitToWidth(true);

        Label cartTitle = new Label("Cart:");
        cartListBox = new VBox(5);
        cartListBox.setMinHeight(200);
        refreshCart();

        ScrollPane cartScrollPane = new ScrollPane(cartListBox);
        cartScrollPane.setPrefHeight(350);
        cartScrollPane.setFitToWidth(true);

        // Input fields for Add to Cart
        TextField itemNameField = new TextField();
        itemNameField.setPromptText("Item Name");
        itemNameField.setMaxWidth(150);

        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        quantityField.setMaxWidth(100);

        HBox addInputBox = new HBox(10, itemNameField, quantityField);
        addInputBox.setAlignment(Pos.CENTER);

        TextField updateIdField = new TextField();
        updateIdField.setPromptText("Item ID");
        updateIdField.setMaxWidth(100);

        TextField newQuantityField = new TextField();
        newQuantityField.setPromptText("New Quantity");
        newQuantityField.setMaxWidth(100);

        HBox updateInputBox = new HBox(10, updateIdField, newQuantityField);
        updateInputBox.setAlignment(Pos.CENTER);

        Button addToCartBtn = new Button("Add to Cart");
        Button updateCartBtn = new Button("Update Cart Item");
        Button deleteCartBtn = new Button("Delete Cart Item");
        Button doneBtn = new Button("Done - Generate Bill");
        Button logoutBtn = new Button("Logout");

        addToCartBtn.setMinWidth(120);
        updateCartBtn.setMinWidth(120);
        deleteCartBtn.setMinWidth(120);
        doneBtn.setMinWidth(150);

        HBox buttonBox1 = new HBox(10, addToCartBtn, deleteCartBtn);
        buttonBox1.setAlignment(Pos.CENTER);

        HBox buttonBox2 = new HBox(10, updateCartBtn);
        buttonBox2.setAlignment(Pos.CENTER);

        messageLabel = new Label();

        VBox layout = new VBox(15,
                title, welcomeLabel, menuTitle, menuScrollPane,
                new Label("Add to Cart:"), addInputBox, buttonBox1,
                new Label("Update Cart (by Item ID):"), updateInputBox, buttonBox2,
                cartTitle, cartScrollPane, doneBtn, messageLabel, logoutBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setMaxWidth(700);
        layout.setMaxHeight(800);
        layout.setStyle("""
                -fx-background-color: rgba(90,140,120,0.85);
                -fx-background-radius: 20;
                -fx-border-color: rgba(90,140,120,0.4);
                -fx-border-radius: 20;
                -fx-border-width: 10;
                """);
        menuScrollPane.setStyle("""
                -fx-background-color: transparent;
                -fx-background:transparent;
                """);
//        menuListBox.setStyle("""
//                -fx-font-family: 'Times New Roman';
//                -fx-font-size: 14px;
//                -fx-text-fill: white;
//                -fx-font-weight: bold;
//                -fx-background-radius: 20;
//                -fx-border-radius: 20;
//                -fx-border-width: 10;
//                """);
        cartScrollPane.setStyle("""
                -fx-background-color: transparent;
                -fx-background:transparent;
                """);
        cartListBox.setStyle("""
                -fx-font-family:'Times New Roman';
                -fx-font-size: 16px;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-background-radius: 20;
                -fx-border-radius: 20;
                -fx-border-width: 10;
              
                """);
        addToCartBtn.setStyle("""
                -fx-background-color: white;
                -fx-text-fill: rgb(90,140,120);
                -fx-font-weight: bold;
                -fx-font-size: 14px;        
                -fx-font-family: 'Times New Roman';
                """);
        deleteCartBtn.setStyle("""
                -fx-background-color: white;
                -fx-text-fill: rgb(90,140,120);
                -fx-font-weight: bold;
                -fx-font-size: 14px;        
                -fx-font-family: 'Times New Roman';
                """);
        updateCartBtn.setStyle("""
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
        welcomeLabel.setStyle("""
                -fx-background-color: transparent;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-font-size: 16px;        
                -fx-font-family: 'Times New Roman';
                """);
        cartTitle.setStyle("""
                -fx-background-color: transparent;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-font-size: 14px;        
                -fx-font-family: 'Times New Roman';
                """);
        menuTitle.setStyle("""
                -fx-background-color: transparent;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-font-size: 14px;        
                -fx-font-family: 'Times New Roman';
                """);
        title.setStyle("""
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-font-size: 20px;        
                -fx-font-family: 'Times New Roman';
                """);
        doneBtn.setStyle("""
                -fx-background-color: white;
                -fx-text-fill: rgb(90,140,120);
                -fx-font-weight: bold;
                -fx-font-size: 14px;        
                -fx-font-family: 'Times New Roman';
                """);


        addToCartBtn.setOnAction(e -> {
            String itemName = itemNameField.getText().trim();
            String qtyStr = quantityField.getText().trim();

            if(itemName.isEmpty() || qtyStr.isEmpty()) {
                messageLabel.setText("Error: Enter item name and quantity");
                return;
            }

            try {
                int quantity = Integer.parseInt(qtyStr);
                if(quantity <= 0) {
                    messageLabel.setText("Error: Quantity must be positive");
                    return;
                }

                boolean success = management.cart.addCartItem(itemName, quantity);
                if(success) {
                    messageLabel.setText("Item added to cart");
                    refreshCart();
                    itemNameField.clear();
                    quantityField.clear();
                } else {
                    messageLabel.setText("Error: Item not found in menu");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Error: Invalid quantity format");
            }
        });

        deleteCartBtn.setOnAction(e -> {
            String itemName = itemNameField.getText().trim();
            if(itemName.isEmpty()) {
                messageLabel.setText("Error: Enter item name to delete");
                return;
            }

            boolean success = management.cart.removeCartItem(itemName);
            if(success) {
                messageLabel.setText("Item removed from cart");
                refreshCart();
                itemNameField.clear();
            } else {
                messageLabel.setText("Error: Item not found in cart");
            }
        });

        updateCartBtn.setOnAction(e -> {
            String idStr = updateIdField.getText().trim();
            String qtyStr = newQuantityField.getText().trim();

            if(idStr.isEmpty() || qtyStr.isEmpty()) {
                messageLabel.setText("Error: Enter item ID and new quantity");
                return;
            }

            try {
                int id = Integer.parseInt(idStr);
                int newQty = Integer.parseInt(qtyStr);

                if(newQty <= 0) {
                    messageLabel.setText("Error: Quantity must be positive");
                    return;
                }

                boolean success = management.cart.updateCartItem(id, newQty);
                if(success) {
                    messageLabel.setText("Cart item updated");
                    refreshCart();
                    updateIdField.clear();
                    newQuantityField.clear();
                } else {
                    messageLabel.setText("Error: Item ID not found in cart");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Error: Invalid ID or quantity format");
            }
        });

        doneBtn.setOnAction(e -> {
            System.out.println("Done button clicked");
            System.out.println("Cart count: " + management.cart.getCount());

            if(management.cart.getCount() == 0) {
                messageLabel.setText("Error: Cart is empty");
                return;
            }

            try {
                System.out.println("Creating billing...");
                Billing billing = new Billing(management.cart);

                System.out.println("Making bill...");
                Bill bill = billing.makeBill();

                System.out.println("Bill created: " + (bill != null));

                if(bill != null) {
                    System.out.println("Opening bill screen...");
                    BillScreen billScreen = new BillScreen(management, currentUser, bill);
                    billScreen.show(primaryStage);
                } else {
                    messageLabel.setText("Error: Unable to generate bill");
                }
            } catch (Exception ex) {
                messageLabel.setText("Error: " + ex.getMessage());
                System.out.println("Exception occurred: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        logoutBtn.setOnAction(e -> {
            LoginScreen loginScreen = new LoginScreen(management);
            loginScreen.show(primaryStage);
        });
        Image image = new Image("File:D:\\OOP code\\IDE code\\Zoro Cafe\\src\\main\\resources\\com\\example\\zorocafe\\projectBackground.png");
        ImageView bgImage = new ImageView(image);
        bgImage.setPreserveRatio(false);

        StackPane root = new StackPane();
        bgImage.fitHeightProperty().bind(root.heightProperty());
        bgImage.fitWidthProperty().bind(root.widthProperty());
        root.getChildren().addAll(bgImage,layout);
        Scene scene = new Scene(root, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zoro's Cafe - Staff Dashboard");
        primaryStage.show();
    }

    private void refreshMenu() {
        menuListBox.getChildren().clear();
        Item[] items = management.inventory.viewItems();

        if(items.length == 0) {
            Label emptyLabel = new Label("No items in menu");
            menuListBox.getChildren().add(emptyLabel);
            return;
        }

        for(Item item : items) {
            Label itemInfo = new Label("ID: " + item.getId() +
                    " | Name: " + item.getName() +
                    " | Price: PKR " + String.format("%.2f", item.getPrice()) +
                    " | Category: " + item.getCategory());
            menuListBox.getChildren().add(itemInfo);

            itemInfo.setWrapText(true);
            itemInfo.setMaxWidth(Double.MAX_VALUE);
            itemInfo.setStyle("""
                    -fx-text-fill: white;
                    -fx-font-family: 'Times New Roman';
                    -fx-font-size: 14px;
                    -fx-font-weight: bold;
                    """);
        }
    }

    private void refreshCart() {
        cartListBox.getChildren().clear();
        CartItem[] cartItems = management.cart.displayCart();

        if(cartItems.length == 0) {
            Label emptyLabel = new Label("Cart is empty");
            cartListBox.getChildren().add(emptyLabel);
            return;
        }

        for(CartItem cartItem : cartItems) {
            Label cartInfo = new Label("ID: " + cartItem.getItem().getId() +
                    " | Name: " + cartItem.getItem().getName() +
                    " | Price: PKR " + String.format("%.2f", cartItem.getItem().getPrice()) +
                    " | Qty: " + cartItem.getQuantity() +
                    " | Total: PKR " + String.format("%.2f", cartItem.getTotalPrice()));
            cartListBox.getChildren().add(cartInfo);
        }
    }
}