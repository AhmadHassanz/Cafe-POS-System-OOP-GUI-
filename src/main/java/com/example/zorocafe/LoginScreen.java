package com.example.zorocafe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class LoginScreen {
    Management management;

    LoginScreen(Management management)
    {
        this.management = management;
    }

    public void show(Stage primaryStage)
    {
        Label title = new Label("LOGIN ACCOUNT");
        Label userLabel = new Label("Username:");
        TextField userField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        userLabel.setMinWidth(80);
        passLabel.setMinWidth(80);
        Button loginButton = new Button("Login");
        loginButton.setMinWidth(100);

        Label message = new Label();
       //Event Handling
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user = userField.getText();
                String pass = passwordField.getText();

                User u = management.login(user,pass);

                if(u == null)
                {
                    message.setText("Invalid username or password!");
                }else
                {
                    message.setText("Login successful!");
                    DashboardScreen d = new DashboardScreen(management,u);
                    d.show(primaryStage);
                }
            }
        });

        HBox userBox = new HBox(10,userLabel,userField);
        userBox.setAlignment(Pos.CENTER);
        HBox passBox = new HBox(10,passLabel,passwordField);
        passBox.setAlignment(Pos.CENTER);
        VBox vb = new VBox(15);
        vb.getChildren().addAll(title,userBox,passBox,loginButton,message);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(20));

        Scene sc = new Scene(vb,600,400);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Zoro's Cafe Login");
        primaryStage.show();
    }
}
