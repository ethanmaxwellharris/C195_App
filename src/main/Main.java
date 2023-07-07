package main;

import dao.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 400, 500)); //width then height
        stage.show();
    }

    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("main/Lang_fr_FR.properties", Locale.getDefault());
        if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("pr"))
            System.out.println(rb.getString("hello") + " " + rb.getString("world"));
        DBConnection.openConnection();
        launch(args);
        DBConnection.closeConnection();
    }
}