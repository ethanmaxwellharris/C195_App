package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
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
    public TableView appointmentsTableView;
    public TableView customersTableView;
    public Button userLogButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Main screen has been initialized");
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

    public void onActionModifyCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("The modify customer button has been clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyCustomer.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Customer Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionAddAppointment(ActionEvent actionEvent) throws IOException {
        System.out.println("The add appointment button has been clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointment Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionModifyAppointment(ActionEvent actionEvent) throws IOException {
        System.out.println("The modify appointment button has been clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyAppointment.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Appointment Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionReports(ActionEvent actionEvent) throws IOException {
        System.out.println("The reports button has been clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("/view/Reports.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Reports Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionUserLog(ActionEvent actionEvent) throws IOException {
        System.out.println("The user log button has been clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("/view/UserLog.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("User Log Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onActionLogout(ActionEvent actionEvent) {
        System.out.println("Ending Program...");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thanks for checking my project out, have a great day!");
        alert.setTitle("Give Me A 100%!");
        alert.showAndWait();
        System.exit(0);
    }

}
