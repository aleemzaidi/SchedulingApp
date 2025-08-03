module azaidi6.schedulingapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;


    opens azaidi6.schedulingapp to javafx.fxml;
    exports azaidi6.schedulingapp;
    exports azaidi6.schedulingapp.controller;
    exports azaidi6.schedulingapp.model;
    opens azaidi6.schedulingapp.model to javafx.base;
    opens azaidi6.schedulingapp.controller to javafx.fxml;
}