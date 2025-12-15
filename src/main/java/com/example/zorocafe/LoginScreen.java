package com.example.zorocafe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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
        Label emptyLabel = new Label();
        Label userLabel = new Label("Username:");
        TextField userField = new TextField();
        userField.setPromptText("Enter username here");

        Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password here");
        userLabel.setMinWidth(80);
        passLabel.setMinWidth(80);
        Button loginButton = new Button("Login");
        loginButton.setMinWidth(75);
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().add(loginButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setPadding(new Insets(0,25,0,0));

       //Event Handling
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user = userField.getText();
                String pass = passwordField.getText();

                User u = management.login(user,pass);

                if(u == null)
                {
                    showAlert("Invalid","Kindly enter correct credentials");
                }else
                {
                    showAlert("Sucess","Welcome " + user + " to page");
                    DashboardScreen d = new DashboardScreen(management,u);
                    d.show(primaryStage);
                }
            }
        });

        HBox userBox = new HBox(10,userLabel,userField);
        userBox.setAlignment(Pos.CENTER);
        HBox passBox = new HBox(10,passLabel,passwordField);
        passBox.setAlignment(Pos.CENTER);
        VBox loginBox = new VBox(15);
        loginBox.getChildren().addAll(title,emptyLabel,userBox,passBox,buttonBox);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setPadding(new Insets(20));
        loginBox.setStyle("""
                -fx-background-color: rgba(90,140,120,0.85);
                -fx-background-radius: 20;
                -fx-border-color: rgba(90,140,120,0.4);
                -fx-border-radius:20;
                -fx-border-width: 2;
                """);
        loginBox.setMaxWidth(330);
        loginBox.setMaxHeight(300);

        title.setStyle("""
                -fx-text-fill: white;
                -fx-font-size: 16px;
                -fx-font-weight: bold;
                -fx-font-family: 'Times New Roman';
                """);
        userLabel.setStyle("""
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-font-size: 14px;        
                -fx-font-family: 'Times New Roman';
                """);
        passLabel.setStyle("""
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-font-size: 14px;        
                -fx-font-family: 'Times New Roman';
                """);
        loginButton.setStyle("""
                -fx-background-color: white;
                -fx-text-fill: rgb(90,140,120);
                -fx-font-weight: bold;
                -fx-font-size: 14px;        
                -fx-font-family: 'Times New Roman';
                """);

        Image image = new Image("File:D:\\OOP code\\IDE code\\Zoro Cafe\\src\\main\\resources\\com\\example\\zorocafe\\projectBackground.png");
        ImageView bgImage = new ImageView(image);
        bgImage.setPreserveRatio(false);

        StackPane root = new StackPane();
        bgImage.fitHeightProperty().bind(root.heightProperty());
        bgImage.fitWidthProperty().bind(root.widthProperty());
        root.getChildren().addAll(bgImage,loginBox);
        Scene sc = new Scene(root,600,400);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Zoro's Cafe Login");
        primaryStage.show();
    }
    private void showAlert(String title,String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}
