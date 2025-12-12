package com.example.zorocafe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class UserSettingsScreen {

    private Management management;
    private VBox userListBox;

    public UserSettingsScreen(Management management) {
        this.management = management;
    }

    public void show(Stage primaryStage) {
        Label title = new Label("USER SETTINGS");

        userListBox = new VBox(5);
        refreshUserList();

        ScrollPane scrollPane = new ScrollPane(userListBox);
        scrollPane.setPrefHeight(200);
        scrollPane.setFitToWidth(true);

        Label displayLabel = new Label("All Users:");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(150);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(150);

        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("ADMIN", "STAFF");
        roleBox.setPromptText("Select Role");
        roleBox.setMaxWidth(150);

        HBox inputBox = new HBox(10, usernameField, passwordField, roleBox);
        inputBox.setAlignment(Pos.CENTER);

        Button addButton = new Button("Add User");
        Button deleteButton = new Button("Delete User");
        Button updateButton = new Button("Update User");
        Button displayButton = new Button("Display All Users");
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

        VBox layout = new VBox(15, title, displayLabel, scrollPane, inputBox,
                buttonBox1, buttonBox2, messageLabel, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        addButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String role = roleBox.getValue();

            if(username.isEmpty() || password.isEmpty() || role == null) {
                messageLabel.setText("Error: Please fill all fields");
                return;
            }

            boolean success = management.addUser(username, password,
                    role.equals("ADMIN") ? UserType.ADMIN : UserType.STAFF);

            if(success) {
                messageLabel.setText("Success: User added successfully");
                refreshUserList();
                clearFields(usernameField, passwordField, roleBox);
            } else {
                messageLabel.setText("Error: Username already exists");
            }
        });

        deleteButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            if(username.isEmpty()) {
                messageLabel.setText("Error: Enter username to delete");
                return;
            }

            boolean success = management.deleteRecords(username);
            if(success) {
                messageLabel.setText("Success: User deleted");
                refreshUserList();
                clearFields(usernameField, passwordField, roleBox);
            } else {
                messageLabel.setText("Error: User not found");
            }
        });

        updateButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String role = roleBox.getValue();

            if(username.isEmpty() || password.isEmpty() || role == null) {
                messageLabel.setText("Error: Fill all fields to update");
                return;
            }

            boolean userExists = false;
            for(User u : management.printAllUser()) {
                if(u.getUsername().equals(username)) {
                    userExists = true;
                    break;
                }
            }

            if(!userExists) {
                messageLabel.setText("Error: User not found");
                return;
            }

            management.updateRecords(username, username, password);
            UserType newRole = role.equals("ADMIN") ? UserType.ADMIN : UserType.STAFF;

            for(User u : management.printAllUser()) {
                if(u.getUsername().equals(username)) {
                    u.setRole(newRole);
                }
            }
            management.rewriteFile();

            messageLabel.setText("Success: User updated");
            refreshUserList();
            clearFields(usernameField, passwordField, roleBox);
        });

        displayButton.setOnAction(e -> {
            refreshUserList();
            messageLabel.setText("User list refreshed");
        });

        backButton.setOnAction(e -> {
            AdminDashboard adminDashboard = new AdminDashboard(management,
                    management.printAllUser()[0]);
            adminDashboard.show(primaryStage);
        });

        Scene scene = new Scene(layout, 650, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zoro's Cafe - User Settings");
        primaryStage.show();
    }

    public void refreshUserList() {
        userListBox.getChildren().clear();
        User[] users = management.printAllUser();

        if(users.length == 0) {
            Label emptyLabel = new Label("No users found");
            userListBox.getChildren().add(emptyLabel);
            return;
        }

        for(User u : users) {
            Label userInfo = new Label("Username: " + u.getUsername() +
                    " | Password: " + u.getPassword() +
                    " | Role: " + u.getRole());
            userListBox.getChildren().add(userInfo);
        }
    }

    public void clearFields(TextField usernameField, PasswordField passwordField, ComboBox<String> roleBox) {
        usernameField.clear();
        passwordField.clear();
        roleBox.setValue(null);
    }
}