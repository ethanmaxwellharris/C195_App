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
import model.Users;

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

//        //ResourceBundle resourceBundle = ResourceBundle.getBundle("src/main/Lang", Locale.getDefault());
//        String userName = usernameTextField.getText();
//        String password = passwordPasswordField.getText();
//        int userId = DBUsers.confirmUser(userName, password);
        //if (userName && password
//        if (userId > 0) {
//            outputFile.println("User " + "'" + userName + "'" + " successfully logged in at: " + nowDateTime + "\n");
//        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
//        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setTitle("Main Menu");
//        stage.setScene(scene);
//        stage.show();
//        } else if (userId < 0) {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Incorrect Username or Password - please try again.");
//            outputFile.println("User " + "'" + userName + "'" + " failed logged in at: " + nowDateTime + "\n");
//        }
//        outputFile.close();
//    }\

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

            String loginAttempt = "Login Attempt " + (existingLoginCount + 1) + " || Login Successful " + " || Username: " + userName + " || Password: " + password + " || Timestamp: " + nowDateTime + "\n";


            FileWriter fw = new FileWriter(file, true);

            fw.write(loginAttempt);
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
                LocalDate nowDate = LocalDate.now();
                LocalTime nowTime = LocalTime.now();
                LocalDateTime nowDateTime = LocalDateTime.of(nowDate, nowTime);
                System.out.println("Welcome " + usernameTextField.getText() + "." + "\n" + "It's currently " + nowDateTime + " where you're logged in.");
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