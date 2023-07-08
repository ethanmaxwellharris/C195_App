package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Label welcomeBackLabel;
    public Label welcomeBackSubTextLabel;
    public Label usernameLabel;
    public Label passwordLabel;
    public Button signInButtonLabel;
    public Button exitButton;
    public Label userLocationLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Login is Initialized!");
    }

    @FXML
    public void onActionSignIn(ActionEvent actionEvent) throws IOException {
        System.out.println("The sign-on button has been clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onActionExit(ActionEvent actionEvent) {
        System.out.println("Ending Program...");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thanks for checking my project out, have a great day!");
        alert.setTitle("Give Me A 100%!");
        alert.showAndWait();
        System.exit(0);
    }
}