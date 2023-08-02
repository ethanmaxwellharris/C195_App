package controller;

import dao.DBAppointments;
import dao.DBConnection;
import dao.DBUsers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;

import javax.naming.spi.ResolveResult;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TimeZone;

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
    public void initialize(URL url, ResourceBundle lang) {
        System.out.println("Login is Initialized!");
//        try{
        ZoneId zoneId = ZoneId.systemDefault();
        userLocationLabel2.setText(zoneId.toString());
//
//        ResourceBundle rb = ResourceBundle.getBundle("Lang_en_US.properties", Locale.getDefault());
//            userLocationLabel2.setText(rb.getString("America/Denver"));
//            welcomeBackLabel.setText(rb.getString("Welcome Back"));
//            welcomeBackSubTextLabel.setText(rb.getString("Sign in to continue"));
//            usernameLabel.setText(rb.getString("Username"));
//            passwordLabel.setText(rb.getString("Password"));
//        }catch(Exception e) {
//            e.printStackTrace();
//            System.out.println(e + " is missing");
//        }
    }

    @FXML
    public void onActionSignIn(ActionEvent actionEvent) throws IOException {
//        LocalDate nowDate = LocalDate.now();
//        LocalTime nowTime = LocalTime.now();
//        LocalDateTime nowDateTime = LocalDateTime.of(nowDate, nowTime);
//
//        FileWriter fwriter = new FileWriter("src/files/activity_log.txt", true);
//        PrintWriter outputFile = new PrintWriter(fwriter);
//        //ResourceBundle resourceBundle = ResourceBundle.getBundle("src/main/Lang", Locale.getDefault());
//        String userName = usernameTextField.getText();
//        String password = passwordPasswordField.getText();
//        int userId = DBUsers.confirmUser(userName, password);
//        if (userId > 0) {
//            outputFile.println("User " + "'" + userName + "'" + " successfully logged in at: " + nowDateTime + "\n");
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
//        } else if (userId < 0) {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Incorrect Username or Password - please try again.");
//            outputFile.println("User " + "'" + userName + "'" + " failed logged in at: " + nowDateTime + "\n");
//        }
//        outputFile.close();
//    }
//Begin Copy Paste from Reddit
//    @FXML
//    public void login(ActionEvent event) throws SQLException {
//
//        Window owner = submitButton.getScene().getWindow();
//
//        System.out.println(usernameTextField.getText());
//        System.out.println(passwordPasswordField.getText());
//
//        if (usernameTextField.getText().isEmpty()) {
//            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
//                    "Please enter your email id");
//            return;
//        }
//        if (passwordPasswordField.getText().isEmpty()) {
//            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
//                    "Please enter a password");
//            return;
//        }
//
//        String emailId = usernameTextField.getText();
//        String password = passwordPasswordField.getText();
//
//        DBConnection dao = new dao();
//        boolean flag = DBConnection.getConnection(jdbcUrl, userName, password);
//
//        if (!flag) {
//            infoBox("Please enter correct Email and Password", null, "Failed");
//        } else {
//            infoBox("Login Successful!", null, "Failed");
//        }
//    }
//
//    public static void infoBox(String infoMessage, String headerText, String title) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setContentText(infoMessage);
//        alert.setTitle(title);
//        alert.setHeaderText(headerText);
//        alert.showAndWait();
//    }
//
//    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
//        Alert alert = new Alert(alertType);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.initOwner(owner);
//        alert.show();
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