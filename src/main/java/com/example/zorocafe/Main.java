package com.example.zorocafe;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage)
    {
        Management management = new Management();
        LoginScreen loginScreen = new LoginScreen(management);
        loginScreen.show(primaryStage);
    }
}