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

/**
 * The controller class for the login interface.
 *
 * This class handles user interactions and authentication for the login interface.
 * It manages the user's sign-in attempt, validates credentials, logs activity, and
 * controls navigation to the main menu or exiting the application.
 *
 * @author      Ethan Harris
 * @version     %I%
 * @since       1.0
 */
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

    /**
     * Initializes the login interface.
     *
     * This method is invoked when the Login interface is loaded. It sets up the user interface,
     * localizes text, and prepares resource bundles based on user language preferences.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Login is Initialized!");
        userLocationLabel2.setText(String.valueOf(ZoneId.systemDefault()));

        ResourceBundle rb = ResourceBundle.getBundle("resources/Lang", Locale.getDefault());
        try {
            if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("es")) {
                userLocationLabel.setText(rb.getString("label.languagePreferenceLabel"));
                welcomeBackLabel.setText(rb.getString("label.welcomeBackLabel"));
                welcomeBackSubTextLabel.setText(rb.getString("label.welcomeBackSubTextLabel"));
                usernameLabel.setText(rb.getString("label.usernameLabel"));
                passwordLabel.setText(rb.getString("label.passwordLabel"));
                signInButtonLabel.setText(rb.getString("button.signInButtonLabel"));
                exitButton.setText(rb.getString("button.exitButton"));
            }
            userLocationLabel.setText(rb.getString("label.languagePreferenceLabel"));
            welcomeBackLabel.setText(rb.getString("label.welcomeBackLabel"));
            welcomeBackSubTextLabel.setText(rb.getString("label.welcomeBackSubTextLabel"));
            usernameLabel.setText(rb.getString("label.usernameLabel"));
            passwordLabel.setText(rb.getString("label.passwordLabel"));
            signInButtonLabel.setText(rb.getString("button.signInButtonLabel"));
            exitButton.setText(rb.getString("button.exitButton"));
        } catch (Exception e) {
            System.out.println(e + " is missing");
        }
    }

    /**
     * Handles the sign-in action when the sign-in button is clicked.
     * Validates the user against the DBUser database, appends a message to activity_log.txt
     *
     * @param actionEvent The ActionEvent triggered by clicking the sign-in button.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void onActionSignIn(ActionEvent actionEvent) throws IOException {
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
            String failedLoginAttempt = "Login Attempt " + (existingLoginCount + 1) + " || Username: " + userName + " || Password: " + password + " || Timestamp: " + nowDateTime + "\n";
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
        } catch (Exception x) {
            x.printStackTrace();
        }
        try {
            ObservableList<Users> validUsers = DBUsers.getAllUsers();
            boolean isUsernameValid = false;
            boolean isPasswordValid = false;
            for (Users user : validUsers) {
                String userName = user.getUserName();
                if (usernameTextField.getText().isEmpty()) {
                    ResourceBundle rb = ResourceBundle.getBundle("resources/Lang", Locale.getDefault());
                    String blankUsername = rb.getString("information.usernameBlank");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(rb.getString("information.title"));
                    alert.setHeaderText(rb.getString("information.header"));
                    alert.setContentText(blankUsername);
                    alert.showAndWait();
                    return;
                } else if (usernameTextField.getText().equals(userName)) {
                    isUsernameValid = true;
                    break;
                }
            }
            if (!isUsernameValid) {
                ResourceBundle rb = ResourceBundle.getBundle("resources/Lang", Locale.getDefault());
                String invalidUserName = rb.getString("error.invalidUserName");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("error.title"));
                alert.setHeaderText(rb.getString("error.header"));
                alert.setContentText(invalidUserName);
                alert.showAndWait();
                return;
            }
            for (Users user : validUsers) {
                String userName = user.getUserName();
                String password = user.getPassword();
                if (passwordPasswordField.getText().isEmpty()) {
                    ResourceBundle rb = ResourceBundle.getBundle("resources/Lang", Locale.getDefault());
                    String blankPassword = rb.getString("information.passwordBlank");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(rb.getString("information.title"));
                    alert.setHeaderText(rb.getString("information.header"));
                    alert.setContentText(blankPassword);
                    alert.showAndWait();
                    return;
                } else if (usernameTextField.getText().equals(userName) && passwordPasswordField.getText().equals(password)) {
                    isPasswordValid = true;
                    break;
                }
            }
            if (!isPasswordValid) {
                ResourceBundle rb = ResourceBundle.getBundle("resources/Lang", Locale.getDefault());
                String invalidPassword = rb.getString("error.invalidPassword");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("error.title"));
                alert.setHeaderText(rb.getString("error.header"));
                alert.setContentText(invalidPassword);
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

    /**
     * Handles the exit action when the exit button is clicked.
     *
     * This method displays a thank-you message and exits the application when the exit button is clicked.
     *
     * @param actionEvent The ActionEvent triggered by clicking the exit button.
     */
    @FXML
    public void onActionExit(ActionEvent actionEvent) {
        System.out.println("Ending Program...");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thanks for checking my project out, have a great day!");
        alert.setTitle("Give Me A 100%!");
        alert.showAndWait();
        System.exit(0);
    }
}