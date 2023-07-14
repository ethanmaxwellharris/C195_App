package main;

import dao.DBConnection;
import dao.DBCustomers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 400, 500)); //width then height
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        //ResourceBundle rb = ResourceBundle.getBundle("main/Lang_fr_FR.properties", Locale.getDefault());
        //if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("pr"))
        //    System.out.println(rb.getString("hello") + " " + rb.getString("world"));
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        LocalDateTime nowDateTime = LocalDateTime.of(nowDate, nowTime);
        System.out.println("Welcome, it's currently " + nowDateTime + " where you're logged in.");

        //Locale.setDefault(new Locale("en"));

        DBConnection.openConnection();
        launch(args);
        DBConnection.closeConnection();
    }
}