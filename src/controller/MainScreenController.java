package controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    public Button addCustomerButton;
    public Button modifyCustomerButton;
    public Button deleteCustomerButton;
    public Button addAppointmentButton;
    public Button deleteAppointmentButton;
    public Button modifyAppointmentButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Main screen has been initialized");
    }
}
