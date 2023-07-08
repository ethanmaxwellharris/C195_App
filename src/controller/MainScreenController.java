package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    public Button addCustomerButton;
    public Button modifyCustomerButton;
    public Button deleteCustomerButton;
    public Button addAppointmentButton;
    public Button deleteAppointmentButton;
    public Button modifyAppointmentButton;
    public Button logoutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Main screen has been initialized");
    }

    public void onActionLogout(ActionEvent actionEvent) {
        System.out.println("Ending Program...");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thanks for checking my project out, have a great day!");
        alert.setTitle("Give Me A 100%!");
        alert.showAndWait();
        System.exit(0);
    }

    public void onActionAddCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("The add customer button has been clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Customer Menu");
        stage.setScene(scene);
        stage.show();
    }
}
