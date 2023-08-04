package controller;

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
import model.Users;

import java.io.*;
import java.net.URL;
import java.time.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoginController implements Initializable {
    @FXML public Label welcomeBackLabel;
    @FXML public Label welcomeBackSubTextLabel;
    @FXML public Label usernameLabel;
    @FXML public Label passwordLabel;
    @FXML public Button signInButtonLabel;
    @FXML public Button exitButton;
    @FXML public Label userLocationLabel;
    @FXML public TextField usernameTextField;
    @FXML public PasswordField passwordPasswordField;
    @FXML public Label userLocationLabel2;
    ResourceBundle rb;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Login is Initialized!");
        userLocationLabel2.setText(String.valueOf(ZoneId.systemDefault()));
        try{
        Locale user = Locale.getDefault();
        if (user.getLanguage().equals("fr")) {
            rb = ResourceBundle.getBundle("Lang_fr_FR");
        } else {
            rb = ResourceBundle.getBundle("Lang_en_EN");
        }
            userLocationLabel.setText(rb.getString("label.languagePreferenceLabel"));
            welcomeBackLabel.setText(rb.getString("label.welcomeBackLabel"));
            welcomeBackSubTextLabel.setText(rb.getString("label.welcomeBackSubTextLabel"));
            usernameLabel.setText(rb.getString("label.usernameLabel"));
            passwordLabel.setText(rb.getString("label.passwordLabel"));
            signInButtonLabel.setText(rb.getString("button.signInButtonLabel"));
            exitButton.setText(rb.getString("button.exitButton"));

        }catch(Exception e) {
            System.out.println(e + " is missing");
        }
    }

    @FXML
    public void onActionSignIn(ActionEvent actionEvent) throws IOException {
//        try {
//            LocalDate nowDate = LocalDate.now();
//            LocalTime nowTime = LocalTime.now();
//            LocalDateTime nowDateTime = LocalDateTime.of(nowDate, nowTime);
//            File file = new File("activity_log.txt");
//            Scanner scanner = new Scanner(file);
//            StringBuilder content = new StringBuilder();
//            while (scanner.hasNext()) {
//                content.append(scanner.nextLine()).append("\n");
//            }
//            scanner.close();
//            int existingLoginCount = (int) content.chars().filter(ch -> ch == '\n').count();
//            String userName = usernameTextField.getText();
//            String password = passwordPasswordField.getText();
//
//            String loginAttempt = "Login Attempt " + (existingLoginCount + 1) + " || Username: " + userName + " || Password: " + password + " || Timestamp: " + nowDateTime + "\n";
//
//            FileWriter fw = new FileWriter(file, true);
//
//            fw.write(loginAttempt);
//            fw.flush();
//            fw.close();
//        }catch (Exception x) {
//            x.printStackTrace();
//        }


        try {
            LocalDate nowDate = LocalDate.now();
            LocalTime nowTime = LocalTime.now();
            LocalDateTime nowDateTime = LocalDateTime.of(nowDate, nowTime);
            File file = new File("activity_log.txt");
            Scanner scanner = new Scanner(file);
            StringBuilder content = new StringBuilder();
            while (scanner.hasNext()) {
                content.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
            int existingLoginCount = (int) content.chars().filter(ch -> ch == '\n').count();
            String userName = usernameTextField.getText();
            String password = passwordPasswordField.getText();

            String successfulLoginAttempt = "Login Attempt " + (existingLoginCount + 1) + " || Username: " + userName + " || Password: " + password + " || Timestamp: " + nowDateTime + "\n";
            String failedLoginAttempt = "Login Attempt " + (existingLoginCount + 1)  + " || Username: " + userName + " || Password: " + password + " || Timestamp: " + nowDateTime + "\n";

            FileWriter fw = new FileWriter(file, true);
            ObservableList<Users> validUsers = DBUsers.getAllUsers();
            boolean isUsernameValid = false;
            boolean isPasswordValid = false;
            for (Users user : validUsers) {
                if (userName.equals(user.getUserName())) {
                    isUsernameValid = true;
                    if (password.equals(user.getPassword())) {
                        isPasswordValid = true;
                    }
                    break;
                }
            }

            if (isUsernameValid && isPasswordValid) {
                fw.write("Login Successful || " + successfulLoginAttempt);
            } else {
                fw.write("Login Failed || " + failedLoginAttempt);
            }
            fw.flush();
            fw.close();
        }catch (Exception x) {
            x.printStackTrace();
        }

        try {
            ObservableList<Users> validUsers = DBUsers.getAllUsers();
            boolean isUsernameValid = false;
            boolean isPasswordValid = false;
            for (Users user : validUsers) {
                String userName = user.getUserName();
                if (usernameTextField.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please enter a username");
                    alert.showAndWait();
                    return;
                } else if (usernameTextField.getText().equals(userName)) {
                    isUsernameValid = true;
                    break;
                }
            }
            if (!isUsernameValid) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Incorrect username");
                alert.showAndWait();
                return;
            }
            for (Users user : validUsers) {
                String userName = user.getUserName();
                String password = user.getPassword();
                if (passwordPasswordField.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please enter a password");
                    alert.showAndWait();
                    return;
                } else if (usernameTextField.getText().equals(userName) && passwordPasswordField.getText().equals(password)) {
                    isPasswordValid = true;
                    break;
                }
            }
            if (!isPasswordValid) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Incorrect password");
                alert.showAndWait();
                return;
            }
            if (isUsernameValid && isPasswordValid) {
                Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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