package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Label welcomeBackLabel;
    public Label welcomeBackSubTextLabel;
    public Label emailLabel;
    public Label passwordLabel;
    public Button signInButtonLabel;
    public Label languagePreferenceLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         System.out.println("LoginController is Initialized!");

    }

    public void onButtonAction(ActionEvent actionEvent) {
        System.out.println("The sign-on button has been clicked!");
    }
}
