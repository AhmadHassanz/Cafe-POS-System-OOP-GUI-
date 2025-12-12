package com.example.zorocafe;

import javafx.stage.Stage;

public class DashboardScreen {

    private Management management;
    private User currentUser;

    public DashboardScreen(Management management, User currentUser) {
        this.management = management;
        this.currentUser = currentUser;
    }

    public void show(Stage primaryStage) {
        if(currentUser.getRole() == UserType.ADMIN) {
            AdminDashboard adminDashboard = new AdminDashboard(management, currentUser);
            adminDashboard.show(primaryStage);
        } else {
            StaffDashboard staffDashboard = new StaffDashboard(management, currentUser);
            staffDashboard.show(primaryStage);
        }
    }
}