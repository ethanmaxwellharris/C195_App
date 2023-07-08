package controller;

import dao.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Label welcomeBackLabel;
    public Label welcomeBackSubTextLabel;
    public Label usernameLabel; //incorrect error needs to display
    public Label passwordLabel; //incorrect error needs to display
    public Button signInButtonLabel;
    public Button exitButton;
    public Label userLocationLabel; //Static Text
    public TextField usernameTextField;
    public PasswordField passwordPasswordField;
    public Label userLocationLabel2; //Text which changes depending on what the user's region is


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
//Begin Copy Paste from Reddit
    @FXML
    public void login(ActionEvent event) throws SQLException {

        Window owner = submitButton.getScene().getWindow();

        System.out.println(usernameTextField.getText());
        System.out.println(passwordPasswordField.getText());

        if (usernameTextField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if (passwordPasswordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String emailId = usernameTextField.getText();
        String password = passwordPasswordField.getText();

        DBConnection dao = new dao();
        boolean flag = DBConnection.getConnection(jdbcUrl, userName, password);

        if (!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {
            infoBox("Login Successful!", null, "Failed");
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
//End CopyPaste from Reddit
    @FXML
    public void onActionExit(ActionEvent actionEvent) {
        System.out.println("Ending Program...");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thanks for checking my project out, have a great day!");
        alert.setTitle("Give Me A 100%!");
        alert.showAndWait();
        System.exit(0);
    }
}