package main;

import dao.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The main class responsible for launching the application and displaying the initial login interface.
 *
 * @author Ethan Harris
 */
public class Main extends Application {

    /**
     * The main entry point for the JavaFX application.
     *
     * @param stage The primary stage for the application.
     * @throws Exception If an exception occurs during application startup.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 400, 500)); //width then height
        stage.show();
    }

    /**
     * The main method to launch the application.
     *
     * @param args The command-line arguments.
     * @throws IOException If an I/O exception occurs during application startup.
     */
    public static void main(String[] args) throws IOException {
        DBConnection.openConnection();
        launch(args);
        DBConnection.closeConnection();
    }
}